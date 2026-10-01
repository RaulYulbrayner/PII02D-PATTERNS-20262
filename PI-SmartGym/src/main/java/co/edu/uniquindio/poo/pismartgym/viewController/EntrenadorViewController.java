package co.edu.uniquindio.poo.pismartgym.viewController;

import co.edu.uniquindio.poo.pismartgym.App;
import co.edu.uniquindio.poo.pismartgym.controller.EntrenadorController;
import co.edu.uniquindio.poo.pismartgym.model.Entrenador;
import co.edu.uniquindio.poo.pismartgym.model.Especialidad;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EntrenadorViewController {

    private App app;

    private EntrenadorController entrenadorController;

    private final ObservableList<Entrenador> listaEntrenadores =
            FXCollections.observableArrayList();

    private Entrenador entrenadorSeleccionado;

    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtNombre;
    @FXML private ComboBox<Especialidad> cmbEspecialidad;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtTarifa;

    @FXML private TableView<Entrenador> tblEntrenadores;

    @FXML private TableColumn<Entrenador, String> colIdentificacion;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, Especialidad> colEspecialidad;
    @FXML private TableColumn<Entrenador, String> colTelefono;
    @FXML private TableColumn<Entrenador, Double> colTarifa;

    @FXML
    void initialize() {

        cmbEspecialidad.setItems(
                FXCollections.observableArrayList(
                        Especialidad.values()
                )
        );

        colIdentificacion.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getIdentificacion())
        );

        colNombre.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getNombre())
        );

        colEspecialidad.setCellValueFactory(
                data -> new SimpleObjectProperty<>(
                        data.getValue().getEspecialidad())
        );

        colTelefono.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getTelefono())
        );

        colTarifa.setCellValueFactory(
                data -> new SimpleDoubleProperty(
                        data.getValue().getTarifaSesion())
                        .asObject()
        );

        listenerSelection();
    }

    public void setApp(App app) {
        this.app = app;
        entrenadorController = new EntrenadorController(app.getGimnasio());
        cargarEntrenadores();
    }

    private Entrenador buildEntrenador() {
        return new Entrenador(txtIdentificacion.getText(), txtNombre.getText(), cmbEspecialidad.getValue(), txtTelefono.getText(), Double.parseDouble(txtTarifa.getText()));
    }

    @FXML
    void onAgregar() {
        try {
            Entrenador entrenador = buildEntrenador();
            mostrarMensaje(entrenadorController.agregarEntrenador(entrenador));
            cargarEntrenadores();
            limpiar();
        } catch (NumberFormatException e) {
            mostrarError("La tarifa debe ser numérica.");
        }
    }

    @FXML
    void onActualizar() {
        if (entrenadorSeleccionado == null) {
            mostrarError("Seleccione un entrenador.");
            return;
        }
        entrenadorController.actualizarEntrenador(entrenadorSeleccionado.getIdentificacion(), buildEntrenador());
        cargarEntrenadores();
        limpiar();
    }

    @FXML
    void onEliminar() {
        if (entrenadorSeleccionado == null) {
            mostrarError("Seleccione un entrenador.");
            return;
        }
        entrenadorController.eliminarEntrenador(entrenadorSeleccionado.getIdentificacion());
        cargarEntrenadores();
        limpiar();
    }

    @FXML
    void onLimpiar() {
        limpiar();
    }

    @FXML
    void onVolver() {
        app.openPrimary();
    }

    private void cargarEntrenadores() {
        listaEntrenadores.clear();
        listaEntrenadores.addAll(entrenadorController.obtenerEntrenadores());
        tblEntrenadores.setItems(
                listaEntrenadores
        );
    }

    private void listenerSelection() {
        tblEntrenadores
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, anterior, nuevo) -> {
                            entrenadorSeleccionado =
                                    nuevo;
                            if (nuevo != null) {
                                txtIdentificacion.setText(
                                        nuevo.getIdentificacion()
                                );
                                txtNombre.setText(
                                        nuevo.getNombre()
                                );
                                cmbEspecialidad.setValue(
                                        nuevo.getEspecialidad()
                                );
                                txtTelefono.setText(
                                        nuevo.getTelefono()
                                );
                                txtTarifa.setText(
                                        String.valueOf(
                                                nuevo.getTarifaSesion()
                                        )
                                );
                            }
                        }
                );
    }

    private void limpiar() {
        txtIdentificacion.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtTarifa.clear();
        cmbEspecialidad.setValue(null);
        entrenadorSeleccionado = null;
        tblEntrenadores.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }

    private void mostrarError(String mensaje) {
        new Alert(Alert.AlertType.ERROR, mensaje).showAndWait();
    }
}
