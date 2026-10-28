package co.edu.uniquindio.poo.piitechsolutions.viewController;

import co.edu.uniquindio.poo.piitechsolutions.controller.TechSolutionsController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;

public class PrincipalViewController {

    @FXML
    private StackPane panelContenido;

    private TechSolutionsController techSolutionsController;

    /**
     * Recibe el controlador principal de aplicación
     * que utilizarán todas las vistas JavaFX.
     *
     * Después de asignarlo, carga la pantalla
     * inicial de servicios.
     *
     * @param techSolutionsController Controlador
     * compartido de la aplicación.
     * @throws IllegalArgumentException Si es nulo.
     */
    public void setTechSolutionsController(TechSolutionsController techSolutionsController) {
        this.techSolutionsController = techSolutionsController;
        cargarVista("Servicios.fxml");
    }

    /**
     * Inicializa los controles definidos en el FXML.
     * Verifica que exista el contenedor utilizado
     * para mostrar las diferentes pantallas.
     */
    @FXML
    private void initialize() {
        if (panelContenido == null) {
            throw new IllegalStateException("No se encontró el contenedor panelContenido");
        }
    }

    /**
     * Muestra la pantalla del catálogo
     * y personalización de servicios.
     */
    @FXML
    private void mostrarServicios() {
        cargarVista("Servicios.fxml");
    }

    /**
     * Muestra la pantalla de administración
     * de paquetes tecnológicos.
     */
    @FXML
    private void mostrarPaquetes() {
        cargarVista("Paquetes.fxml");
    }

    /**
     * Muestra la pantalla de registro
     * y confirmación de solicitudes.
     */
    @FXML
    private void mostrarSolicitudes() {
        cargarVista("Solicitudes.fxml");
    }

    /**
     * Muestra la pantalla de consulta
     * de técnicos especializados.
     */
    @FXML
    private void mostrarTecnicos() {
        cargarVista("Tecnicos.fxml");
    }

    /**
     * Carga una vista FXML dentro del contenedor
     * principal de la aplicación.
     * Utiliza FXMLLoader para construir la vista
     * y obtener su controlador correspondiente.
     * Antes de mostrarla, comparte la instancia
     * de TechSolutionsController.
     * @param nombreFXML Nombre del archivo FXML.
     */
    private void cargarVista(String nombreFXML) {
        if (techSolutionsController == null) {
            Ui.mostrarError("Error de inicialización", "El controlador de aplicación " + "no se encuentra configurado");
            return;
        }
        try {
            String ruta = "/co/edu/uniquindio/poo/piitechsolutions/" + nombreFXML;
            URL recurso = getClass().getResource(ruta);
            if (recurso == null) {
                throw new IOException("No se encontró la vista: " + ruta);
            }
            FXMLLoader loader = new FXMLLoader(recurso);
            Parent vista = loader.load();
            Object controladorVista =
                    loader.getController();
            configurarControladorVista(controladorVista);

            panelContenido.getChildren().clear();
            panelContenido.getChildren().add(vista);

        } catch (IOException excepcion) {

            Ui.mostrarError(
                    "Error al cargar la vista",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Comparte el controlador de aplicación
     * con el controlador de la vista cargada.
     *
     * Reconoce las cuatro pantallas disponibles
     * y asigna la misma instancia utilizada
     * por la aplicación.
     *
     * @param controladorVista Controlador creado
     * automáticamente por FXMLLoader.
     */
    private void configurarControladorVista(
            Object controladorVista) {

        if (controladorVista
                instanceof ServiciosViewController controlador) {

            controlador.setTechSolutionsController(
                    techSolutionsController
            );

        } else if (controladorVista
                instanceof PaquetesViewController controlador) {

            controlador.setTechSolutionsController(
                    techSolutionsController
            );

        } else if (controladorVista
                instanceof SolicitudesViewController controlador) {

            controlador.setTechSolutionsController(
                    techSolutionsController
            );

        } else if (controladorVista
                instanceof TecnicosViewController controlador) {

            controlador.setTechSolutionsController(
                    techSolutionsController
            );

        } else {

            throw new IllegalStateException(
                    "El controlador de la vista no es compatible"
            );
        }
    }
}
