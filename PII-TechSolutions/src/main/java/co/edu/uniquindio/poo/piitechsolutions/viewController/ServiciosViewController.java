package co.edu.uniquindio.poo.piitechsolutions.viewController;

import co.edu.uniquindio.poo.piitechsolutions.controller.TechSolutionsController;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoComplemento;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoServicio;

import co.edu.uniquindio.poo.piitechsolutions.model.ServicioPersonalizado;
import co.edu.uniquindio.poo.piitechsolutions.model.ServicioTecnologico;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class ServiciosViewController {

    @FXML
    private ComboBox<ServicioTecnologico> cmbServicios;

    @FXML
    private TextField txtCodigoPersonalizado;

    @FXML
    private CheckBox chkAtencionPrioritaria;

    @FXML
    private CheckBox chkSoporteTecnico;

    @FXML
    private CheckBox chkAtencionDomicilio;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblCostoBase;

    @FXML
    private Label lblCostoComplementos;

    @FXML
    private Label lblCostoTotal;

    @FXML
    private ListView<ServicioPersonalizado> listaPersonalizados;

    private TechSolutionsController techSolutionsController;

    /**
     * Inicializa los valores visuales de la pantalla.
     *
     * No consulta el catálogo porque el controlador
     * principal de aplicación todavía no ha sido
     * asignado por PrincipalViewController.
     */
    @FXML
    private void initialize() {

        lblDescripcion.setText(
                "Seleccione un servicio tecnológico"
        );

        lblCostoBase.setText(Ui.formatoMoneda(0.0));

        lblCostoComplementos.setText(
                Ui.formatoMoneda(0.0)
        );

        lblCostoTotal.setText(Ui.formatoMoneda(0.0));
    }

    /**
     * Recibe el controlador compartido de TechSolutions.
     *
     * Después de asignarlo, carga los servicios
     * originales y las personalizaciones registradas.
     *
     * @param techSolutionsController Controlador de aplicación.
     * @throws IllegalArgumentException Si es nulo.
     */
    public void setTechSolutionsController(
            TechSolutionsController techSolutionsController) {

        if (techSolutionsController == null) {
            throw new IllegalArgumentException(
                    "El controlador de aplicación es obligatorio"
            );
        }

        this.techSolutionsController = techSolutionsController;

        cargarServicios();
        cargarPersonalizados();
    }

    /**
     * Consulta los servicios tecnológicos originales
     * y los presenta en el ComboBox.
     *
     * Selecciona inicialmente el primer servicio,
     * siempre que el catálogo no esté vacío.
     */
    private void cargarServicios() {

        List<ServicioTecnologico> servicios =
                techSolutionsController.listarServicios();

        cmbServicios.setItems(
                FXCollections.observableArrayList(servicios)
        );

        if (!servicios.isEmpty()) {
            cmbServicios.getSelectionModel().selectFirst();
        }

        actualizarCosto();
    }

    /**
     * Consulta y muestra los servicios personalizados
     * registrados en el catálogo.
     */
    private void cargarPersonalizados() {

        List<ServicioPersonalizado> personalizados =
                techSolutionsController.listarPersonalizados();

        listaPersonalizados.setItems(
                FXCollections.observableArrayList(
                        personalizados
                )
        );
    }

    /**
     * Actualiza la información mostrada cuando
     * el usuario selecciona un servicio original.
     *
     * Este método debe estar asociado al evento
     * onAction del ComboBox en el archivo FXML.
     */
    @FXML
    private void seleccionarServicio() {

        actualizarCosto();
    }

    /**
     * Actualiza los costos cuando el usuario
     * marca o desmarca algún complemento.
     *
     * Los CheckBox pueden compartir este
     * mismo método en su evento onAction.
     */
    @FXML
    private void seleccionarComplemento() {

        actualizarCosto();
    }

    /**
     * Obtiene los complementos seleccionados
     * mediante los controles CheckBox.
     *
     * @return Lista de complementos seleccionados.
     */
    private List<TipoComplemento>
    obtenerComplementosSeleccionados() {

        List<TipoComplemento> complementos =
                new ArrayList<>();

        if (chkAtencionPrioritaria.isSelected()) {

            complementos.add(
                    TipoComplemento.ATENCION_PRIORITARIA
            );
        }

        if (chkSoporteTecnico.isSelected()) {

            complementos.add(
                    TipoComplemento.SOPORTE_30_DIAS
            );
        }

        if (chkAtencionDomicilio.isSelected()) {

            complementos.add(
                    TipoComplemento.ATENCION_DOMICILIO
            );
        }

        return complementos;
    }

    /**
     * Calcula el costo acumulado de los complementos
     * seleccionados por el usuario.
     *
     * Los valores comerciales se consultan
     * mediante TechSolutionsController.
     *
     * @return Costo total de los complementos.
     */
    private double calcularCostoComplementos() {

        double total = 0;

        List<TipoComplemento> complementos =
                obtenerComplementosSeleccionados();

        for (TipoComplemento complemento : complementos) {

            total +=
                    techSolutionsController
                            .consultarCostoComplemento(
                                    complemento
                            );
        }

        return total;
    }

    /**
     * Actualiza la descripción y los costos
     * del servicio que se está personalizando.
     *
     * El costo mostrado es una estimación
     * antes de registrar la personalización.
     *
     * No modifica el servicio original.
     */
    private void actualizarCosto() {

        if (techSolutionsController == null) {
            return;
        }

        ServicioTecnologico servicio =
                cmbServicios.getSelectionModel()
                        .getSelectedItem();

        if (servicio == null) {

            lblDescripcion.setText(
                    "Seleccione un servicio tecnológico"
            );

            lblCostoBase.setText(
                    Ui.formatoMoneda(0.0)
            );

            lblCostoComplementos.setText(
                    Ui.formatoMoneda(0.0)
            );

            lblCostoTotal.setText(
                    Ui.formatoMoneda(0.0)
            );

            return;
        }

        try {

            double costoBase = servicio.getCostoBase();

            double costoComplementos =
                    calcularCostoComplementos();

            double costoTotal =
                    costoBase + costoComplementos;

            if (!Double.isFinite(costoTotal)) {
                throw new IllegalArgumentException(
                        "El costo calculado no es válido"
                );
            }

            lblDescripcion.setText(
                    servicio.getDescripcion()
            );

            lblCostoBase.setText(
                    Ui.formatoMoneda(costoBase)
            );

            lblCostoComplementos.setText(
                    Ui.formatoMoneda(costoComplementos)
            );

            lblCostoTotal.setText(
                    Ui.formatoMoneda(costoTotal)
            );

        } catch (IllegalArgumentException excepcion) {

            Ui.mostrarError(
                    "Error al calcular el costo",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Registra un servicio personalizado
     * con los complementos seleccionados.
     *
     * Valida el código de la nueva personalización,
     * el servicio original y la selección
     * de al menos un complemento.
     *
     * Delega la creación de los decoradores
     * a TechSolutionsController.
     */
    @FXML
    private void personalizarServicio() {

        try {

            ServicioTecnologico original =
                    cmbServicios.getSelectionModel()
                            .getSelectedItem();

            if (original == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar un servicio"
                );
            }

            String nuevoCodigo =
                    Ui.obtenerTextoObligatorio(
                            txtCodigoPersonalizado,
                            "código de personalización"
                    );

            List<TipoComplemento> complementos =
                    obtenerComplementosSeleccionados();

            if (complementos.isEmpty()) {
                throw new IllegalArgumentException(
                        "Debe seleccionar al menos un complemento"
                );
            }

            ServicioPersonalizado personalizado =
                    techSolutionsController.personalizarServicio(
                            original.getCodigo(),
                            nuevoCodigo,
                            complementos
                    );

            cargarPersonalizados();

            Ui.mostrarInformacion(
                    "Personalización registrada",
                    "Servicio: " + personalizado.getNombre()
                            + "\nCódigo: "
                            + personalizado.getCodigo()
                            + "\nCosto total: "
                            + Ui.formatoMoneda(
                            personalizado.calcularCosto()
                    )
            );

            limpiarFormulario();

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            Ui.mostrarError(
                    "No fue posible personalizar el servicio",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Limpia los campos de personalización
     * y conserva el servicio original seleccionado.
     *
     * Actualiza el costo para mostrar
     * únicamente el valor base del servicio.
     */
    @FXML
    private void limpiarFormulario() {

        txtCodigoPersonalizado.clear();

        chkAtencionPrioritaria.setSelected(false);
        chkSoporteTecnico.setSelected(false);
        chkAtencionDomicilio.setSelected(false);

        actualizarCosto();
    }

    /**
     * Actualiza las listas de servicios originales
     * y personalizaciones registradas.
     *
     * Permite reflejar cambios realizados
     * desde otras pantallas del sistema.
     */
    @FXML
    private void actualizarCatalogo() {

        if (techSolutionsController != null) {

            cargarServicios();
            cargarPersonalizados();
        }
    }
}
