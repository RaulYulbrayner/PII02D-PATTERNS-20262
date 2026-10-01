package co.edu.uniquindio.poo.pismartgym;

import co.edu.uniquindio.poo.pismartgym.model.Gimnasio;
import co.edu.uniquindio.poo.pismartgym.viewController.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación SmartGym.
 * Se encarga de iniciar JavaFX y administrar
 * la navegación entre las diferentes vistas.
 */
public class App extends Application {

    private Stage primaryStage;

    private final Gimnasio gimnasio = new Gimnasio(
            "SmartGym",
            "901000001",
            "Armenia, Quindío",
            "6067000000",
            "contacto@smartgym.com",
            "www.smartgym.com"
    );

    /**
     * Inicia la aplicación JavaFX.
     * @param primaryStage ventana principal
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SmartGym");
        openPrimary();
    }

    public void openPrimary() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
            Pane root = loader.load();
            PrimaryViewController controller = loader.getController();
            controller.setApp(this);
            mostrarEscena(root, "SmartGym");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("cliente.fxml"));
            Pane root = loader.load();
            ClienteViewController controller = loader.getController();
            controller.setApp(this);
            mostrarEscena(root, "Gestión de clientes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openEntrenador() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("entrenador.fxml"));
            Pane root = loader.load();
            EntrenadorViewController controller = loader.getController();
            controller.setApp(this);
            mostrarEscena(root, "Gestión de entrenadores");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPlan() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("plan.fxml"));
            Pane root = loader.load();
            PlanViewController controller = loader.getController();
            controller.setApp(this);
            mostrarEscena(root, "Gestión de planes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openServicio() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("servicio.fxml"));
            Pane root = loader.load();
            ServicioViewController controller = loader.getController();
            controller.setApp(this);
            mostrarEscena(root, "Servicios adicionales");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openInscripcion() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("inscripcion.fxml"));
            Pane root = loader.load();
            InscripcionViewController controller = loader.getController();
            controller.setApp(this);
            mostrarEscena(root, "Inscripciones");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConsulta() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("consulta.fxml"));
            Pane root = loader.load();
            ConsultaViewController controller = loader.getController();
            controller.setApp(this);
            mostrarEscena(root, "Consultas SmartGym");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cambia la escena de la ventana principal.
     * @param root nodo raíz de la vista
     * @param titulo título de la ventana
     */
    private void mostrarEscena(Pane root, String titulo) {
        Scene scene = new Scene(root);
        primaryStage.setTitle(titulo);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public static void main(String[] args) {
        launch();
    }
}