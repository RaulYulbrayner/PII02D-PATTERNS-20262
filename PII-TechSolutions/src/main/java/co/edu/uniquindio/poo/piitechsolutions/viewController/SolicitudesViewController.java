package co.edu.uniquindio.poo.piitechsolutions.viewController;

import co.edu.uniquindio.poo.piitechsolutions.controller.TechSolutionsController;

import co.edu.uniquindio.poo.piitechsolutions.model.PrioridadSolicitud;

import co.edu.uniquindio.poo.piitechsolutions.model.Cliente;
import co.edu.uniquindio.poo.piitechsolutions.model.ElementoContratable;
import co.edu.uniquindio.poo.piitechsolutions.model.SolicitudServicio;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SolicitudesViewController {

    @FXML
    private TextField txtCodigoSolicitud;

    @FXML
    private ComboBox<Cliente> cmbClientes;

    @FXML
    private ComboBox<ElementoContratable> cmbElementosDisponibles;

    @FXML
    private ListView<ElementoContratable> listaElementosSeleccionados;

    @FXML
    private DatePicker dpFechaProgramada;

    @FXML
    private ComboBox<PrioridadSolicitud> cmbPrioridad;

    @FXML
    private TextArea txtObservaciones;

    @FXML
    private Label lblSubtotalEstimado;

    @FXML
    private ListView<SolicitudServicio> listaSolicitudes;

    @FXML
    private Label lblCodigoSolicitud;

    @FXML
    private Label lblClienteSolicitud;

    @FXML
    private Label lblEstadoSolicitud;

    @FXML
    private Label lblSubtotalSolicitud;

    @FXML
    private Label lblDesplazamientoSolicitud;

    @FXML
    private Label lblTotalSolicitud;

    private TechSolutionsController techSolutionsController;

    /**
     * Inicializa los elementos visuales de la pantalla.
     *
     * La consulta de los datos del sistema se realiza
     * después de recibir el controlador principal.
     */
    @FXML
    private void initialize() {

        cmbPrioridad.setItems(
                FXCollections.observableArrayList(
                        PrioridadSolicitud.values()
                )
        );

        listaElementosSeleccionados.setItems(
                FXCollections.observableArrayList(
                        new ArrayList<ElementoContratable>()
                )
        );

        lblSubtotalEstimado.setText(
                Ui.formatoMoneda(0.0)
        );

        limpiarDetalleSolicitud();
    }

    /**
     * Recibe el controlador compartido
     * de la aplicación TechSolutions.
     *
     * Después de asignarlo, carga clientes,
     * elementos contratables y solicitudes.
     *
     * @param techSolutionsController Controlador
     * principal de la aplicación.
     * @throws IllegalArgumentException Si es nulo.
     */
    public void setTechSolutionsController(
            TechSolutionsController techSolutionsController) {

        if (techSolutionsController == null) {
            throw new IllegalArgumentException(
                    "El controlador de aplicación es obligatorio"
            );
        }

        this.techSolutionsController =
                techSolutionsController;

        cargarClientes();
        cargarElementosDisponibles();
        cargarSolicitudes();
    }

    /**
     * Carga los clientes registrados en TechSolutions.
     */
    private void cargarClientes() {

        List<Cliente> clientes =
                techSolutionsController.listarClientes();

        cmbClientes.setItems(
                FXCollections.observableArrayList(clientes)
        );

        if (!clientes.isEmpty()) {
            cmbClientes.getSelectionModel().selectFirst();
        }
    }

    /**
     * Carga los servicios originales,
     * servicios personalizados y paquetes
     * tecnológicos disponibles para contratar.
     */
    private void cargarElementosDisponibles() {

        List<ElementoContratable> elementos =
                techSolutionsController
                        .listarElementosContratables();

        cmbElementosDisponibles.setItems(
                FXCollections.observableArrayList(elementos)
        );

        if (!elementos.isEmpty()) {
            cmbElementosDisponibles
                    .getSelectionModel()
                    .selectFirst();
        }
    }

    /**
     * Carga las solicitudes registradas.
     *
     * Conserva la solicitud seleccionada
     * cuando todavía existe en el sistema.
     */
    private void cargarSolicitudes() {

        SolicitudServicio seleccionada =
                listaSolicitudes.getSelectionModel()
                        .getSelectedItem();

        List<SolicitudServicio> solicitudes =
                techSolutionsController.listarSolicitudes();

        listaSolicitudes.setItems(
                FXCollections.observableArrayList(solicitudes)
        );

        if (seleccionada != null
                && solicitudes.contains(seleccionada)) {

            listaSolicitudes.getSelectionModel()
                    .select(seleccionada);

        } else if (!solicitudes.isEmpty()) {

            listaSolicitudes.getSelectionModel()
                    .selectFirst();
        }

        actualizarDetalleSolicitud();
    }

    /**
     * Agrega a la selección de contratación
     * el elemento elegido en el ComboBox.
     *
     * No permite seleccionar dos veces
     * la misma instancia de un elemento.
     */
    @FXML
    private void agregarElemento() {

        try {

            ElementoContratable elemento =
                    cmbElementosDisponibles
                            .getSelectionModel()
                            .getSelectedItem();

            if (elemento == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar un elemento"
                );
            }

            boolean repetido = false;

            for (ElementoContratable actual
                    : listaElementosSeleccionados.getItems()) {

                if (actual == elemento) {
                    repetido = true;
                    break;
                }
            }

            if (repetido) {
                throw new IllegalArgumentException(
                        "El elemento ya fue seleccionado"
                );
            }

            listaElementosSeleccionados.getItems()
                    .add(elemento);

            actualizarSubtotalEstimado();

        } catch (IllegalArgumentException excepcion) {

            Ui.mostrarError(
                    "No fue posible agregar el elemento",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Retira un elemento de la selección
     * de contratación actual.
     *
     * No elimina el elemento del catálogo.
     */
    @FXML
    private void quitarElemento() {

        ElementoContratable elemento =
                listaElementosSeleccionados
                        .getSelectionModel()
                        .getSelectedItem();

        if (elemento == null) {

            Ui.mostrarAdvertencia(
                    "Selección requerida",
                    "Debe seleccionar el elemento que desea quitar"
            );

            return;
        }

        listaElementosSeleccionados.getItems()
                .remove(elemento);

        actualizarSubtotalEstimado();
    }

    /**
     * Calcula el subtotal estimado de los
     * elementos actualmente seleccionados.
     *
     * El resultado solo se utiliza para mostrar
     * una vista previa antes de registrar la solicitud.
     *
     * El subtotal definitivo se conserva en
     * SolicitudServicio al construir la contratación.
     */
    private void actualizarSubtotalEstimado() {

        double subtotal = 0;

        for (ElementoContratable elemento
                : listaElementosSeleccionados.getItems()) {

            subtotal += elemento.calcularCosto();
        }

        if (!Double.isFinite(subtotal)) {

            Ui.mostrarError(
                    "Error en el costo",
                    "El subtotal calculado no es válido"
            );

            return;
        }

        lblSubtotalEstimado.setText(
                Ui.formatoMoneda(subtotal)
        );
    }

    /**
     * Registra una nueva solicitud utilizando
     * los elementos seleccionados por el usuario.
     *
     * Obtiene los datos del formulario y delega
     * la construcción mediante Builder a
     * TechSolutionsController y SolicitudService.
     */
    @FXML
    private void registrarSolicitud() {

        try {

            String codigo =
                    Ui.obtenerTextoObligatorio(
                            txtCodigoSolicitud,
                            "código de solicitud"
                    );

            Cliente cliente =
                    cmbClientes.getSelectionModel()
                            .getSelectedItem();

            if (cliente == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar un cliente"
                );
            }

            if (listaElementosSeleccionados
                    .getItems().isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe agregar al menos un elemento"
                );
            }

            List<String> codigosElementos =
                    new ArrayList<>();

            for (ElementoContratable elemento
                    : listaElementosSeleccionados.getItems()) {

                codigosElementos.add(
                        elemento.getCodigo()
                );
            }

            LocalDate fechaCreacion = LocalDate.now();

            LocalDate fechaProgramada =
                    dpFechaProgramada.getValue();

            PrioridadSolicitud prioridad =
                    cmbPrioridad.getValue();

            String observaciones =
                    txtObservaciones.getText();

            SolicitudServicio solicitud =
                    techSolutionsController.crearSolicitud(
                            codigo,
                            fechaCreacion,
                            cliente.getDocumento(),
                            codigosElementos,
                            fechaProgramada,
                            observaciones,
                            prioridad
                    );

            cargarSolicitudes();

            listaSolicitudes.getSelectionModel()
                    .select(solicitud);

            actualizarDetalleSolicitud();

            Ui.mostrarInformacion(
                    "Solicitud registrada",
                    "La solicitud fue registrada correctamente."
                            + "\nCódigo: " + solicitud.getCodigo()
                            + "\nEstado: " + solicitud.getEstado()
                            + "\nSubtotal: "
                            + Ui.formatoMoneda(
                            solicitud.calcularSubtotal()
                    )
            );

            limpiarFormulario();

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            Ui.mostrarError(
                    "No fue posible registrar la solicitud",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Actualiza el detalle cuando el usuario
     * selecciona una solicitud de la lista.
     *
     * Debe asociarse al evento onMouseClicked
     * de listaSolicitudes en el archivo FXML.
     */
    @FXML
    private void seleccionarSolicitud() {

        actualizarDetalleSolicitud();
    }

    /**
     * Muestra los datos y costos de la
     * solicitud seleccionada.
     *
     * Incluye el subtotal contratado,
     * el desplazamiento y el costo total.
     */
    private void actualizarDetalleSolicitud() {

        SolicitudServicio solicitud =
                listaSolicitudes.getSelectionModel()
                        .getSelectedItem();

        if (solicitud == null) {
            limpiarDetalleSolicitud();
            return;
        }

        lblCodigoSolicitud.setText(
                solicitud.getCodigo()
        );

        lblClienteSolicitud.setText(
                solicitud.getCliente().getNombreCompleto()
        );

        lblEstadoSolicitud.setText(
                solicitud.getEstado().name()
        );

        lblSubtotalSolicitud.setText(
                Ui.formatoMoneda(
                        solicitud.calcularSubtotal()
                )
        );

        lblDesplazamientoSolicitud.setText(
                Ui.formatoMoneda(
                        solicitud.getCostoDesplazamiento()
                )
        );

        lblTotalSolicitud.setText(
                Ui.formatoMoneda(
                        solicitud.calcularCostoTotal()
                )
        );
    }

    /**
     * Confirma la solicitud seleccionada.
     *
     * La confirmación se realiza exclusivamente
     * mediante TechSolutionsController.
     *
     * SolicitudService consulta los técnicos,
     * verifica su disponibilidad y calcula
     * el desplazamiento antes de confirmar.
     */
    @FXML
    private void confirmarSolicitud() {

        try {

            SolicitudServicio solicitud =
                    listaSolicitudes.getSelectionModel()
                            .getSelectedItem();

            if (solicitud == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar una solicitud"
                );
            }

            SolicitudServicio confirmada =
                    techSolutionsController.confirmarSolicitud(
                            solicitud.getCodigo()
                    );

            cargarSolicitudes();

            listaSolicitudes.getSelectionModel()
                    .select(confirmada);

            actualizarDetalleSolicitud();

            Ui.mostrarInformacion(
                    "Solicitud confirmada",
                    "La solicitud fue confirmada correctamente."
                            + "\nCódigo: "
                            + confirmada.getCodigo()
                            + "\nDesplazamiento: "
                            + Ui.formatoMoneda(
                            confirmada.getCostoDesplazamiento()
                    )
                            + "\nCosto total: "
                            + Ui.formatoMoneda(
                            confirmada.calcularCostoTotal()
                    )
            );

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            Ui.mostrarError(
                    "No fue posible confirmar la solicitud",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Consulta las especialidades sin disponibilidad
     * para la solicitud seleccionada.
     *
     * Los datos provienen de TecnicoService
     * a través de TechSolutionsController.
     */
    @FXML
    private void verificarTecnicos() {

        try {

            SolicitudServicio solicitud =
                    listaSolicitudes.getSelectionModel()
                            .getSelectedItem();

            if (solicitud == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar una solicitud"
                );
            }

            List<co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica>
                    noDisponibles =
                    techSolutionsController
                            .consultarEspecialidadesNoDisponibles(
                                    solicitud.getCodigo()
                            );

            if (noDisponibles.isEmpty()) {

                Ui.mostrarInformacion(
                        "Disponibilidad de técnicos",
                        "Todas las especialidades requeridas "
                                + "cuentan con disponibilidad."
                );

            } else {

                String mensaje =
                        "No hay técnicos disponibles para:";

                for (co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica
                        especialidad : noDisponibles) {

                    mensaje += "\n- " + especialidad.name();
                }

                Ui.mostrarAdvertencia(
                        "Técnicos no disponibles",
                        mensaje
                );
            }

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            Ui.mostrarError(
                    "Error al consultar técnicos",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Actualiza clientes, elementos contratables
     * y solicitudes registradas.
     *
     * Permite visualizar los cambios realizados
     * desde otras pantallas de TechSolutions.
     */
    @FXML
    private void actualizarDatos() {

        if (techSolutionsController != null) {

            cargarClientes();
            cargarElementosDisponibles();
            cargarSolicitudes();

            actualizarSubtotalEstimado();
        }
    }

    /**
     * Limpia los datos de creación de solicitudes.
     *
     * No modifica las solicitudes registradas
     * ni los datos de las demás vistas.
     */
    @FXML
    private void limpiarFormulario() {

        txtCodigoSolicitud.clear();

        dpFechaProgramada.setValue(null);

        cmbPrioridad.getSelectionModel()
                .clearSelection();

        txtObservaciones.clear();

        listaElementosSeleccionados.getItems()
                .clear();

        actualizarSubtotalEstimado();
    }

    /**
     * Limpia los campos del detalle
     * de la solicitud seleccionada.
     */
    private void limpiarDetalleSolicitud() {

        lblCodigoSolicitud.setText("-");

        lblClienteSolicitud.setText("-");

        lblEstadoSolicitud.setText("-");

        lblSubtotalSolicitud.setText(
                Ui.formatoMoneda(0.0)
        );

        lblDesplazamientoSolicitud.setText(
                Ui.formatoMoneda(0.0)
        );

        lblTotalSolicitud.setText(
                Ui.formatoMoneda(0.0)
        );
    }
}
