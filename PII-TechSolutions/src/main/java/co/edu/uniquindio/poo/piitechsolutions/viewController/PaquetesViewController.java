package co.edu.uniquindio.poo.piitechsolutions.viewController;

import co.edu.uniquindio.poo.piitechsolutions.controller.TechSolutionsController;
import co.edu.uniquindio.poo.piitechsolutions.model.ElementoContratable;
import co.edu.uniquindio.poo.piitechsolutions.model.PaqueteTecnologico;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class PaquetesViewController {

    @FXML
    private TextField txtCodigoPaquete;

    @FXML
    private TextField txtNombrePaquete;

    @FXML
    private TextArea txtDescripcionPaquete;

    @FXML
    private ComboBox<PaqueteTecnologico> cmbPaquetes;

    @FXML
    private ComboBox<ElementoContratable> cmbElementos;

    @FXML
    private ListView<ElementoContratable> listaElementosPaquete;

    @FXML
    private Label lblNombrePaquete;

    @FXML
    private Label lblCostoPaquete;

    private TechSolutionsController techSolutionsController;

    /**
     * Inicializa los controles visuales.
     *
     * La carga de datos se realiza después
     * de recibir TechSolutionsController.
     */
    @FXML
    private void initialize() {

        lblNombrePaquete.setText(
                "Seleccione un paquete tecnológico"
        );

        lblCostoPaquete.setText(
                Ui.formatoMoneda(0.0)
        );
    }

    /**
     * Recibe el controlador compartido
     * de la aplicación TechSolutions.
     *
     * Posteriormente carga los paquetes
     * y elementos disponibles.
     *
     * @param techSolutionsController Controlador
     * principal de la aplicación.
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

        cargarPaquetes();
        cargarElementosDisponibles();
    }

    /**
     * Carga los paquetes tecnológicos registrados.
     *
     * Mantiene la selección anterior cuando
     * el paquete todavía existe.
     */
    private void cargarPaquetes() {

        PaqueteTecnologico seleccionado =
                cmbPaquetes.getSelectionModel()
                        .getSelectedItem();

        List<PaqueteTecnologico> paquetes =
                techSolutionsController.listarPaquetes();

        cmbPaquetes.setItems(
                FXCollections.observableArrayList(paquetes)
        );

        if (seleccionado != null
                && paquetes.contains(seleccionado)) {

            cmbPaquetes.getSelectionModel()
                    .select(seleccionado);

        } else if (!paquetes.isEmpty()) {

            cmbPaquetes.getSelectionModel().selectFirst();
        }

        actualizarPaqueteSeleccionado();
    }

    /**
     * Carga los elementos que pueden agregarse
     * a los paquetes tecnológicos.
     *
     * Incluye servicios originales, servicios
     * personalizados y paquetes registrados.
     *
     * No filtra los paquetes anidados aquí;
     * su validación corresponde al modelo.
     */
    private void cargarElementosDisponibles() {

        List<ElementoContratable> elementos =
                techSolutionsController
                        .listarElementosContratables();

        cmbElementos.setItems(
                FXCollections.observableArrayList(elementos)
        );

        if (!elementos.isEmpty()) {
            cmbElementos.getSelectionModel().selectFirst();
        }
    }

    /**
     * Crea un paquete tecnológico utilizando
     * los datos del formulario.
     *
     * Delega su creación a TechSolutionsController.
     */
    @FXML
    private void crearPaquete() {

        try {

            String codigo =
                    Ui.obtenerTextoObligatorio(
                            txtCodigoPaquete,
                            "código del paquete"
                    );

            String nombre =
                    Ui.obtenerTextoObligatorio(
                            txtNombrePaquete,
                            "nombre del paquete"
                    );

            String descripcion =
                    Ui.obtenerTextoObligatorio(
                            txtDescripcionPaquete,
                            "descripción del paquete"
                    );

            PaqueteTecnologico paquete =
                    techSolutionsController.crearPaquete(
                            codigo,
                            nombre,
                            descripcion
                    );

            cargarPaquetes();
            cargarElementosDisponibles();

            cmbPaquetes.getSelectionModel().select(paquete);

            actualizarPaqueteSeleccionado();

            limpiarFormulario();

            Ui.mostrarInformacion(
                    "Paquete registrado",
                    "Se creó correctamente el paquete: "
                            + paquete.getNombre()
                            + "\nCódigo: "
                            + paquete.getCodigo()
            );

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            Ui.mostrarError(
                    "Error al crear el paquete",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Actualiza la información del paquete
     * seleccionado en el ComboBox.
     *
     * Este método se asocia al evento onAction
     * de cmbPaquetes en el archivo FXML.
     */
    @FXML
    private void seleccionarPaquete() {

        actualizarPaqueteSeleccionado();
    }

    /**
     * Muestra los componentes y el costo
     * del paquete seleccionado.
     *
     * El costo se obtiene desde el modelo
     * mediante TechSolutionsController.
     */
    private void actualizarPaqueteSeleccionado() {

        PaqueteTecnologico paquete =
                cmbPaquetes.getSelectionModel()
                        .getSelectedItem();

        if (paquete == null) {

            lblNombrePaquete.setText(
                    "Seleccione un paquete tecnológico"
            );

            lblCostoPaquete.setText(
                    Ui.formatoMoneda(0.0)
            );

            listaElementosPaquete.setItems(
                    FXCollections.observableArrayList(
                            new ArrayList<ElementoContratable>()
                    )
            );

            return;
        }

        try {

            List<ElementoContratable> elementos =
                    techSolutionsController
                            .listarElementosPaquete(
                                    paquete.getCodigo()
                            );

            double costo =
                    techSolutionsController
                            .calcularCostoPaquete(
                                    paquete.getCodigo()
                            );

            lblNombrePaquete.setText(
                    paquete.getNombre()
            );

            lblCostoPaquete.setText(
                    Ui.formatoMoneda(costo)
            );

            listaElementosPaquete.setItems(
                    FXCollections.observableArrayList(
                            elementos
                    )
            );

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            Ui.mostrarError(
                    "Error al consultar el paquete",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Agrega el elemento seleccionado al paquete.
     *
     * Puede agregarse un servicio original,
     * un servicio personalizado u otro paquete.
     *
     * La detección de elementos repetidos
     * y referencias circulares se delega
     * al modelo PaqueteTecnologico.
     */
    @FXML
    private void agregarElemento() {

        try {

            PaqueteTecnologico paquete =
                    cmbPaquetes.getSelectionModel()
                            .getSelectedItem();

            if (paquete == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar un paquete"
                );
            }

            ElementoContratable elemento =
                    cmbElementos.getSelectionModel()
                            .getSelectedItem();

            if (elemento == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar un elemento"
                );
            }

            techSolutionsController.agregarElementoPaquete(
                    paquete.getCodigo(),
                    elemento.getCodigo()
            );

            actualizarPaqueteSeleccionado();

            Ui.mostrarInformacion(
                    "Elemento agregado",
                    "Se agregó correctamente: "
                            + elemento.getNombre()
            );

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            Ui.mostrarError(
                    "No fue posible agregar el elemento",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Elimina el elemento seleccionado
     * directamente del paquete actual.
     *
     * No elimina el elemento del catálogo
     * ni de otros paquetes.
     */
    @FXML
    private void eliminarElemento() {

        try {

            PaqueteTecnologico paquete =
                    cmbPaquetes.getSelectionModel()
                            .getSelectedItem();

            if (paquete == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar un paquete"
                );
            }

            ElementoContratable elemento =
                    listaElementosPaquete.getSelectionModel()
                            .getSelectedItem();

            if (elemento == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar un elemento de la lista"
                );
            }

            boolean eliminado =
                    techSolutionsController.eliminarElementoPaquete(
                            paquete.getCodigo(),
                            elemento.getCodigo()
                    );

            if (!eliminado) {
                throw new IllegalStateException(
                        "El elemento no pertenece al paquete"
                );
            }

            actualizarPaqueteSeleccionado();

            Ui.mostrarInformacion(
                    "Elemento eliminado",
                    "Se retiró correctamente: "
                            + elemento.getNombre()
            );

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            Ui.mostrarError(
                    "No fue posible eliminar el elemento",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Actualiza los paquetes y los elementos
     * disponibles desde el controlador compartido.
     *
     * Permite visualizar personalizaciones y paquetes
     * creados después de abrir esta pantalla.
     */
    @FXML
    private void actualizarDatos() {

        if (techSolutionsController != null) {

            cargarPaquetes();
            cargarElementosDisponibles();
        }
    }

    /**
     * Limpia los campos utilizados
     * para crear nuevos paquetes.
     */
    @FXML
    private void limpiarFormulario() {

        txtCodigoPaquete.clear();
        txtNombrePaquete.clear();
        txtDescripcionPaquete.clear();
    }
}
