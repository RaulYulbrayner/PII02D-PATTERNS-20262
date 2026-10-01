package co.edu.uniquindio.poo.pismartgym.viewController;

import co.edu.uniquindio.poo.pismartgym.App;
import javafx.fxml.FXML;

/**
 * Controlador gráfico de la vista principal.
 */
public class PrimaryViewController {

    private App app;

    @FXML
    void onOpenCliente() {
        app.openCliente();
    }

    @FXML
    void onOpenEntrenador() {
        app.openEntrenador();
    }

    @FXML
    void onOpenPlan() {
        app.openPlan();
    }

    @FXML
    void onOpenServicio() {
        app.openServicio();
    }

    @FXML
    void onOpenInscripcion() {
        app.openInscripcion();
    }

    @FXML
    void onOpenConsulta() {
        app.openConsulta();
    }

    /**
     * Establece la aplicación principal.
     *
     * @param app aplicación
     */
    public void setApp(App app) {
        this.app = app;
    }
}
