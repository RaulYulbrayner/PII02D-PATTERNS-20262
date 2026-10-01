package co.edu.uniquindio.poo.pismartgym.viewController;

import co.edu.uniquindio.poo.pismartgym.App;
import co.edu.uniquindio.poo.pismartgym.controller.InscripcionController;
import co.edu.uniquindio.poo.pismartgym.model.*;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class InscripcionViewController {

    private App app;
    private InscripcionController controller;

    private Inscripcion inscripcionSeleccionada;

    @FXML private TextField txtCodigo;
    @FXML private DatePicker dpFecha;

    @FXML private ComboBox<Cliente> cmbCliente;
    @FXML private ComboBox<PlanEntrenamiento> cmbPlan;
    @FXML private ComboBox<Entrenador> cmbEntrenador;

    @FXML private TextField txtDescuento;

    @FXML private ComboBox<ServicioAdicional> cmbServicio;
    @FXML private TextField txtCantidad;

    @FXML private TableView<Inscripcion> tblInscripciones;

    @FXML private TableColumn<Inscripcion, String> colCodigo;
    @FXML private TableColumn<Inscripcion, LocalDate> colFecha;
    @FXML private TableColumn<Inscripcion, String> colCliente;
    @FXML private TableColumn<Inscripcion, String> colPlan;
    @FXML private TableColumn<Inscripcion, Double> colDescuento;
    @FXML private TableColumn<Inscripcion, Double> colValor;

    @FXML
    void initialize() {

        dpFecha.setValue(LocalDate.now());

        colCodigo.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getCodigo()
                )
        );

        colFecha.setCellValueFactory(
                data -> new SimpleObjectProperty<>(
                        data.getValue()
                                .getFechaInscripcion()
                )
        );

        colCliente.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue()
                                .getCliente()
                                .getNombreCompleto()
                )
        );

        colPlan.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue()
                                .getPlan()
                                .getNombre()
                )
        );

        colDescuento.setCellValueFactory(
                data -> new SimpleDoubleProperty(
                        data.getValue()
                                .getPorcentajeDescuento()
                ).asObject()
        );

        colValor.setCellValueFactory(
                data -> new SimpleDoubleProperty(
                        data.getValue()
                                .calcularValorFinal()
                ).asObject()
        );

        tblInscripciones.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, anterior, nuevo) ->
                                inscripcionSeleccionada =
                                        nuevo
                );
    }

    public void setApp(App app) {

        this.app = app;

        controller =
                new InscripcionController(
                        app.getGimnasio()
                );

        cargarDatos();
    }

    private void cargarDatos() {

        cmbCliente.setItems(
                FXCollections.observableArrayList(
                        controller.obtenerClientes()
                )
        );

        cmbPlan.setItems(
                FXCollections.observableArrayList(
                        controller.obtenerPlanes()
                )
        );

        cmbEntrenador.setItems(
                FXCollections.observableArrayList(
                        controller.obtenerEntrenadores()
                )
        );

        cmbServicio.setItems(
                FXCollections.observableArrayList(
                        controller.obtenerServicios()
                )
        );

        tblInscripciones.setItems(
                FXCollections.observableArrayList(
                        controller.obtenerInscripciones()
                )
        );
    }

    @FXML
    void onCrearInscripcion() {
        try {
            Cliente cliente = cmbCliente.getValue();
            PlanEntrenamiento plan = cmbPlan.getValue();
            if (cliente == null || plan == null) {
                mostrarError("Seleccione cliente y plan.");
                return;
            }
            AsignacionEntrenador asignacion = null;
            /*
             * Solo los planes personalizados pueden
             * tener asignación de entrenador.
             */
            if (plan instanceof PlanPersonalizado personalizado) {
                Entrenador entrenador = cmbEntrenador.getValue();
                if (entrenador != null) {
                    asignacion = new AsignacionEntrenador(cliente, personalizado, entrenador);
                }
            }

            double descuento = 0;
            if (!txtDescuento.getText().isBlank()) {
                descuento = Double.parseDouble(txtDescuento.getText());
            }

            Inscripcion inscripcion = controller.crearInscripcion(txtCodigo.getText(), dpFecha.getValue(), cliente, plan, descuento, asignacion);

            String resultado = controller.guardarInscripcion(inscripcion);

            mostrarMensaje(resultado);

            cargarDatos();
            limpiar();

        } catch (NumberFormatException e) {
            mostrarError("El descuento debe ser numérico.");
        }
    }

    @FXML
    void onAgregarServicio() {
        if (inscripcionSeleccionada == null) {
            mostrarError("Seleccione primero una inscripción.");
            return;
        }

        ServicioAdicional servicio = cmbServicio.getValue();

        if (servicio == null) {
            mostrarError("Seleccione un servicio.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            String resultado = controller.agregarServicio(inscripcionSeleccionada, servicio, cantidad);
            mostrarMensaje(resultado);
            tblInscripciones.refresh();
        } catch (NumberFormatException e) {
            mostrarError("La cantidad debe ser entera.");
        }
    }

    @FXML
    void onLimpiar() {
        limpiar();
    }

    @FXML
    void onVolver() {
        app.openPrimary();
    }

    private void limpiar() {
        txtCodigo.clear();
        txtDescuento.clear();
        txtCantidad.clear();
        dpFecha.setValue(LocalDate.now());
        cmbCliente.setValue(null);
        cmbPlan.setValue(null);
        cmbEntrenador.setValue(null);
        cmbServicio.setValue(null);
        inscripcionSeleccionada = null;
        tblInscripciones.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }

    private void mostrarError(String mensaje) {
        new Alert(Alert.AlertType.ERROR, mensaje).showAndWait();
    }
}
