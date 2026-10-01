package co.edu.uniquindio.poo.pismartgym.viewController;


import co.edu.uniquindio.poo.pismartgym.App;
import co.edu.uniquindio.poo.pismartgym.controller.ClienteController;
import co.edu.uniquindio.poo.pismartgym.model.Cliente;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

/**
 * Controlador gráfico encargado de gestionar
 * la vista de clientes.
 */
public class ClienteViewController {

    private App app;
    private ClienteController clienteController;

    private final ObservableList<Cliente> listaClientes =
            FXCollections.observableArrayList();

    private Cliente clienteSeleccionado;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtEdad;

    @FXML
    private DatePicker dpFechaRegistro;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn<Cliente, String> colDocumento;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colCorreo;

    @FXML
    private TableColumn<Cliente, Integer> colEdad;

    @FXML
    private TableColumn<Cliente, LocalDate> colFecha;

    /**
     * Inicializa componentes exclusivamente relacionados
     * con la interfaz.
     */
    @FXML
    void initialize() {

        initDataBinding();
        listenerSelection();

        dpFechaRegistro.setValue(LocalDate.now());
    }

    private void initDataBinding() {

        colDocumento.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getDocumento())
        );

        colNombre.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getNombreCompleto())
        );

        colTelefono.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getTelefono())
        );

        colCorreo.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getCorreoElectronico())
        );

        colEdad.setCellValueFactory(
                data -> new SimpleIntegerProperty(
                        data.getValue().getEdad()).asObject()
        );

        colFecha.setCellValueFactory(
                data -> new SimpleObjectProperty<>(
                        data.getValue().getFechaRegistro())
        );
    }

    /**
     * Recibe la aplicación y crea el Controller
     * después de tener disponible el modelo.
     * @param app aplicación principal
     */
    public void setApp(App app) {
        this.app = app;
        this.clienteController = new ClienteController(app.getGimnasio());
        obtenerClientes();
    }

    private void obtenerClientes() {
        listaClientes.clear();
        listaClientes.addAll(clienteController.obtenerClientes());
        tblClientes.setItems(listaClientes);
    }

    private Cliente buildCliente() {
        return new Cliente(txtNombre.getText(), txtDocumento.getText(), txtTelefono.getText(), txtCorreo.getText(), Integer.parseInt(txtEdad.getText()), dpFechaRegistro.getValue());
    }

    @FXML
    void onAgregar() {
        try {
            Cliente cliente = buildCliente();
            String resultado = clienteController.agregarCliente(cliente);
            mostrarInformacion(resultado);
            obtenerClientes();
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarError("La edad debe ser un número entero.");
        }
    }

    @FXML
    void onActualizar() {
        if (clienteSeleccionado == null) {
            mostrarError("Debe seleccionar un cliente.");
            return;
        }
        try {
            Cliente actualizado = buildCliente();
            boolean resultado = clienteController.actualizarCliente(clienteSeleccionado.getDocumento(), actualizado);
            if (resultado) {
                mostrarInformacion("Cliente actualizado correctamente.");
            } else {
                mostrarError("No fue posible actualizar el cliente.");
            }
            obtenerClientes();
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarError("La edad debe ser un número entero.");
        }
    }

    @FXML
    void onEliminar() {
        if (clienteSeleccionado == null) {
            mostrarError("Debe seleccionar un cliente.");
            return;
        }

        boolean resultado = clienteController.eliminarCliente(clienteSeleccionado.getDocumento());

        if (resultado) {
            mostrarInformacion("Cliente eliminado correctamente.");
        }
        obtenerClientes();
        limpiarCampos();
    }

    @FXML
    void onLimpiar() {
        limpiarCampos();
    }

    @FXML
    void onVolver() {
        app.openPrimary();
    }

    private void listenerSelection() {

        tblClientes.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, anterior, nuevo) -> {
                            clienteSeleccionado = nuevo;
                            mostrarCliente(clienteSeleccionado);
                        }
                );
    }

    private void mostrarCliente(Cliente cliente) {
        if (cliente != null) {
            txtDocumento.setText(cliente.getDocumento());
            txtNombre.setText(cliente.getNombreCompleto());
            txtTelefono.setText(cliente.getTelefono());
            txtCorreo.setText(cliente.getCorreoElectronico());
            txtEdad.setText(String.valueOf(cliente.getEdad()));
            dpFechaRegistro.setValue(cliente.getFechaRegistro());
        }
    }

    private void limpiarCampos() {
        txtDocumento.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
        dpFechaRegistro.setValue(LocalDate.now());
        tblClientes.getSelectionModel().clearSelection();
        clienteSeleccionado = null;
    }

    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SmartGym");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("SmartGym");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
