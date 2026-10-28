package co.edu.uniquindio.poo.piitechsolutions;

import co.edu.uniquindio.poo.piitechsolutions.controller.TechSolutionsController;
import co.edu.uniquindio.poo.piitechsolutions.viewController.PrincipalViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    /**
     * Inicia la aplicación JavaFX.
     *
     * Carga la vista principal, construye
     * el controlador de aplicación y lo entrega
     * al controlador principal de JavaFX.
     *
     * @param stage Ventana principal de JavaFX.
     * @throws IOException Si no es posible cargar
     * el archivo FXML de la ventana principal.
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            String rutaFXML = "/co/edu/uniquindio/poo/piitechsolutions/" + "Principal.fxml";
            URL recurso = App.class.getResource(rutaFXML);
            if (recurso == null) {
                throw new IOException(
                        "No se encontró el archivo: " + rutaFXML
                );
            }
            FXMLLoader loader = new FXMLLoader(recurso);
            Parent root = loader.load();
            PrincipalViewController principalController =
                    loader.getController();
            TechSolutionsController techSolutionsController =
                    new TechSolutionsController();

            principalController.setTechSolutionsController(
                    techSolutionsController
            );

            Scene scene = new Scene(root, 1100, 720);

            stage.setTitle(
                    "TechSolutions - Gestión de Servicios Tecnológicos"
            );

            stage.setScene(scene);

            stage.setMinWidth(900);
            stage.setMinHeight(600);

            stage.show();

        } catch (IOException excepcion) {

            mostrarErrorInicio(excepcion.getMessage());
            throw excepcion;

        } catch (RuntimeException excepcion) {

            mostrarErrorInicio(excepcion.getMessage());
            throw excepcion;
        }
    }

    /**
     * Presenta un mensaje cuando ocurre un error
     * durante la inicialización de la aplicación.
     *
     * @param mensaje Descripción del problema.
     */
    private void mostrarErrorInicio(String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle("Error de inicialización");
        alerta.setHeaderText(
                "No fue posible iniciar TechSolutions"
        );

        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }

    /**
     * Punto de entrada de la aplicación Java.
     *
     * Inicia el ciclo de vida de JavaFX mediante
     * el método launch() heredado de Application.
     *
     * @param args Argumentos de ejecución.
     */
    public static void main(String[] args) {

        launch(args);
    }
}
