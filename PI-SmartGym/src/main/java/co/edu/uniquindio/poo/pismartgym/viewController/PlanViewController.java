package co.edu.uniquindio.poo.pismartgym.viewController;

import co.edu.uniquindio.poo.pismartgym.App;
import co.edu.uniquindio.poo.pismartgym.controller.PlanController;
import co.edu.uniquindio.poo.pismartgym.model.factory.*;
import co.edu.uniquindio.poo.pismartgym.model.*;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PlanViewController {

    private App app;
    private PlanController planController;

    @FXML private ComboBox<String> cmbTipoPlan;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtValor;
    @FXML private ComboBox<EstadoPlan> cmbEstado;

    @FXML private TextField txtSesiones;
    @FXML private ComboBox<Especialidad> cmbEspecialidad;
    @FXML private TextField txtObjetivos;

    @FXML private TableView<PlanEntrenamiento> tblPlanes;

    @FXML private TableColumn<PlanEntrenamiento, String> colCodigo;
    @FXML private TableColumn<PlanEntrenamiento, String> colNombre;
    @FXML private TableColumn<PlanEntrenamiento, String> colTipo;
    @FXML private TableColumn<PlanEntrenamiento, Integer> colDuracion;
    @FXML private TableColumn<PlanEntrenamiento, Double> colValor;
    @FXML private TableColumn<PlanEntrenamiento, EstadoPlan> colEstado;

    @FXML
    void initialize() {

        cmbTipoPlan.setItems(
                FXCollections.observableArrayList(
                        "BÁSICO",
                        "PREMIUM",
                        "PERSONALIZADO"
                )
        );

        cmbEstado.setItems(
                FXCollections.observableArrayList(
                        EstadoPlan.values()
                )
        );

        cmbEspecialidad.setItems(
                FXCollections.observableArrayList(
                        Especialidad.values()
                )
        );

        cmbTipoPlan.valueProperty()
                .addListener(
                        (obs, anterior, nuevo) ->
                                actualizarCamposPersonalizados(nuevo)
                );

        colCodigo.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getCodigo())
        );

        colNombre.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getNombre())
        );

        colTipo.setCellValueFactory(
                data -> new SimpleStringProperty(
                        obtenerTipo(data.getValue()))
        );

        colDuracion.setCellValueFactory(
                data -> new SimpleIntegerProperty(
                        data.getValue().getDuracionMeses())
                        .asObject()
        );

        colValor.setCellValueFactory(
                data -> new SimpleDoubleProperty(
                        data.getValue().getValorMensual())
                        .asObject()
        );

        colEstado.setCellValueFactory(
                data -> new SimpleObjectProperty<>(
                        data.getValue().getEstado())
        );

        actualizarCamposPersonalizados(null);
    }

    public void setApp(App app) {

        this.app = app;

        planController =
                new PlanController(
                        app.getGimnasio()
                );

        cargarPlanes();
    }

    @FXML
    void onCrear() {

        try {

            String tipo =
                    cmbTipoPlan.getValue();

            if (tipo == null) {

                mostrarError(
                        "Seleccione un tipo de plan."
                );

                return;
            }

            DatosPlan datos;
            CreadorPlan creador;

            if (tipo.equals("PERSONALIZADO")) {

                datos = new DatosPlan(
                        txtCodigo.getText(),
                        txtNombre.getText(),
                        txtDescripcion.getText(),
                        Integer.parseInt(
                                txtDuracion.getText()
                        ),
                        Double.parseDouble(
                                txtValor.getText()
                        ),
                        cmbEstado.getValue(),
                        Integer.parseInt(
                                txtSesiones.getText()
                        ),
                        cmbEspecialidad.getValue(),
                        txtObjetivos.getText()
                );

                creador =
                        new CreadorPlanPersonalizado();

            } else {

                datos = new DatosPlan(
                        txtCodigo.getText(),
                        txtNombre.getText(),
                        txtDescripcion.getText(),
                        Integer.parseInt(
                                txtDuracion.getText()
                        ),
                        Double.parseDouble(
                                txtValor.getText()
                        ),
                        cmbEstado.getValue()
                );

                if (tipo.equals("BÁSICO")) {

                    creador =
                            new CreadorPlanBasico();

                } else {

                    creador =
                            new CreadorPlanPremium();
                }
            }

            String resultado =
                    planController.crearPlan(
                            creador,
                            datos
                    );

            mostrarMensaje(resultado);

            cargarPlanes();
            limpiar();

        } catch (NumberFormatException e) {

            mostrarError(
                    "Duración, valor y sesiones deben contener valores numéricos válidos."
            );
        }
    }

    private void actualizarCamposPersonalizados(
            String tipo) {

        boolean personalizado =
                "PERSONALIZADO".equals(tipo);

        txtSesiones.setDisable(!personalizado);
        cmbEspecialidad.setDisable(!personalizado);
        txtObjetivos.setDisable(!personalizado);

        if (!personalizado) {

            txtSesiones.clear();
            cmbEspecialidad.setValue(null);
            txtObjetivos.clear();
        }
    }

    private String obtenerTipo(
            PlanEntrenamiento plan) {

        if (plan instanceof PlanBasico) {
            return "Básico";
        }

        if (plan instanceof PlanPremium) {
            return "Premium";
        }

        if (plan instanceof PlanPersonalizado) {
            return "Personalizado";
        }

        return "Desconocido";
    }

    private void cargarPlanes() {

        tblPlanes.setItems(
                FXCollections.observableArrayList(
                        planController.obtenerPlanes()
                )
        );
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
        txtNombre.clear();
        txtDescripcion.clear();
        txtDuracion.clear();
        txtValor.clear();
        txtSesiones.clear();
        txtObjetivos.clear();

        cmbTipoPlan.setValue(null);
        cmbEstado.setValue(null);
        cmbEspecialidad.setValue(null);
    }

    private void mostrarMensaje(String mensaje) {

        new Alert(
                Alert.AlertType.INFORMATION,
                mensaje
        ).showAndWait();
    }

    private void mostrarError(String mensaje) {

        new Alert(
                Alert.AlertType.ERROR,
                mensaje
        ).showAndWait();
    }
}
