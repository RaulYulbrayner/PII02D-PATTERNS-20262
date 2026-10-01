package co.edu.uniquindio.poo.pismartgym.viewController;

import co.edu.uniquindio.poo.pismartgym.App;
import co.edu.uniquindio.poo.pismartgym.controller.ServicioController;
import co.edu.uniquindio.poo.pismartgym.model.ServicioAdicional;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controlador gráfico encargado de gestionar
 * la vista de servicios adicionales.
 * Esta clase recibe las acciones realizadas por el usuario
 * desde el archivo FXML y delega las operaciones de negocio
 * al ServicioController.
 */
public class ServicioViewController {

    private App app;
    private ServicioController servicioController;

    private final ObservableList<ServicioAdicional> listaServicios =
            FXCollections.observableArrayList();

    private ServicioAdicional servicioSeleccionado;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private CheckBox chkDisponible;

    @FXML
    private TableView<ServicioAdicional> tblServicios;

    @FXML
    private TableColumn<ServicioAdicional, String> colCodigo;

    @FXML
    private TableColumn<ServicioAdicional, String> colNombre;

    @FXML
    private TableColumn<ServicioAdicional, String> colDescripcion;

    @FXML
    private TableColumn<ServicioAdicional, Double> colPrecio;

    @FXML
    private TableColumn<ServicioAdicional, Boolean> colDisponible;

    /**
     * Inicializa los componentes de la interfaz gráfica.
     */
    @FXML
    void initialize() {

        initDataBinding();
        listenerSelection();

        chkDisponible.setSelected(true);
    }

    /**
     * Establece la aplicación principal y crea
     * el controlador correspondiente.
     *
     * @param app aplicación principal
     */
    public void setApp(App app) {

        this.app = app;

        this.servicioController =
                new ServicioController(
                        app.getGimnasio()
                );

        obtenerServicios();
    }

    /**
     * Configura las columnas de la tabla con los
     * atributos de ServicioAdicional.
     */
    private void initDataBinding() {

        colCodigo.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getCodigo()
                )
        );

        colNombre.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getNombre()
                )
        );

        colDescripcion.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getDescripcion()
                )
        );

        colPrecio.setCellValueFactory(
                data -> new SimpleDoubleProperty(
                        data.getValue().getPrecio()
                ).asObject()
        );

        colDisponible.setCellValueFactory(
                data -> new SimpleBooleanProperty(
                        data.getValue().isDisponible()
                )
        );
    }

    /**
     * Obtiene los servicios registrados y los
     * muestra en la tabla.
     */
    private void obtenerServicios() {

        listaServicios.clear();

        listaServicios.addAll(
                servicioController.obtenerServicios()
        );

        tblServicios.setItems(
                listaServicios
        );
    }

    /**
     * Construye un servicio adicional utilizando
     * los datos ingresados en la interfaz.
     *
     * @return servicio adicional construido
     */
    private ServicioAdicional buildServicio() {

        return new ServicioAdicional(
                txtCodigo.getText(),
                txtNombre.getText(),
                txtDescripcion.getText(),
                Double.parseDouble(
                        txtPrecio.getText()
                ),
                chkDisponible.isSelected()
        );
    }

    /**
     * Atiende la acción de agregar un servicio.
     */
    @FXML
    void onAgregar() {
        agregarServicio();
    }

    /**
     * Atiende la acción de actualizar un servicio.
     */
    @FXML
    void onActualizar() {
        actualizarServicio();
    }

    /**
     * Atiende la acción de eliminar un servicio.
     */
    @FXML
    void onEliminar() {
        eliminarServicio();
    }

    /**
     * Atiende la acción de limpiar los campos.
     */
    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    /**
     * Regresa a la vista principal.
     */
    @FXML
    void onVolver() {
        app.openPrimary();
    }

    /**
     * Agrega un nuevo servicio adicional.
     */
    private void agregarServicio() {

        try {

            if (!validarCampos()) {
                return;
            }

            ServicioAdicional servicio =
                    buildServicio();

            String resultado =
                    servicioController
                            .agregarServicio(servicio);

            mostrarInformacion(resultado);

            obtenerServicios();
            limpiarSeleccion();

        } catch (NumberFormatException e) {

            mostrarError(
                    "El precio debe ser un valor numérico."
            );
        }
    }

    /**
     * Actualiza el servicio seleccionado.
     */
    private void actualizarServicio() {
        if (servicioSeleccionado == null) {
            mostrarError("Debe seleccionar un servicio de la tabla.");
            return;
        }
        try {
            if (!validarCampos()) {
                return;
            }
            ServicioAdicional actualizado = buildServicio();
            boolean resultado = servicioController.actualizarServicio(servicioSeleccionado.getCodigo(), actualizado);

            if (resultado) {
                mostrarInformacion("Servicio actualizado correctamente.");
                obtenerServicios();
                limpiarSeleccion();
            } else {
                mostrarError("No fue posible actualizar el servicio.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El precio debe ser un valor numérico.");
        }
    }

    /**
     * Elimina el servicio seleccionado.
     */
    private void eliminarServicio() {
        if (servicioSeleccionado == null) {
            mostrarError("Debe seleccionar un servicio de la tabla.");
            return;
        }
        boolean resultado = servicioController.eliminarServicio(servicioSeleccionado.getCodigo());

        if (resultado) {
            mostrarInformacion("Servicio eliminado correctamente.");
            obtenerServicios();
            limpiarSeleccion();

        } else {
            mostrarError("No fue posible eliminar el servicio.");
        }
    }

    /**
     * Configura el listener de selección de la tabla.
     */
    private void listenerSelection() {
        tblServicios
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, anterior, nuevo) -> {

                            servicioSeleccionado = nuevo;
                            mostrarInformacionServicio(
                                    servicioSeleccionado
                            );
                        }
                );
    }

    /**
     * Muestra en los campos la información
     * del servicio seleccionado.
     *
     * @param servicio servicio seleccionado
     */
    private void mostrarInformacionServicio(ServicioAdicional servicio) {
        if (servicio != null) {
            txtCodigo.setText(servicio.getCodigo());
            txtNombre.setText(servicio.getNombre());
            txtDescripcion.setText(servicio.getDescripcion());
            txtPrecio.setText(String.valueOf(servicio.getPrecio()));
            chkDisponible.setSelected(servicio.isDisponible());
        }
    }

    /**
     * Valida que los campos obligatorios
     * estén diligenciados.
     * @return true si los campos son válidos
     */
    private boolean validarCampos() {
        if (txtCodigo.getText().isBlank()) {
            mostrarError("El código es obligatorio.");
            return false;
        }
        if (txtNombre.getText().isBlank()) {
            mostrarError("El nombre es obligatorio.");
            return false;
        }
        if (txtDescripcion.getText().isBlank()) {
            mostrarError("La descripción es obligatoria.");
            return false;
        }
        if (txtPrecio.getText().isBlank()) {
            mostrarError("El precio es obligatorio.");
            return false;
        }
        return true;
    }

    /**
     * Limpia la selección de la tabla
     * y los campos de entrada.
     */
    private void limpiarSeleccion() {
        tblServicios.getSelectionModel().clearSelection();
        servicioSeleccionado = null;
        limpiarCampos();
    }

    /**
     * Limpia los campos del formulario.
     */
    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        chkDisponible.setSelected(true);
    }

    /**
     * Muestra un mensaje informativo.
     * @param mensaje mensaje mostrado al usuario
     */
    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SmartGym");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de error.
     * @param mensaje mensaje mostrado al usuario
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("SmartGym");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
