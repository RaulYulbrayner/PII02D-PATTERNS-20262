package co.edu.uniquindio.poo.pismartgym.viewController;

import co.edu.uniquindio.poo.pismartgym.App;
import co.edu.uniquindio.poo.pismartgym.controller.ConsultaController;
import co.edu.uniquindio.poo.pismartgym.model.Cliente;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ConsultaViewController {

    private App app;
    private ConsultaController controller;

    @FXML
    private TextField txtTelefono;

    @FXML
    private Label lblResultadoTelefono;

    @FXML
    private DatePicker dpInicio;

    @FXML
    private DatePicker dpFin;

    @FXML
    private Label lblIngresos;

    @FXML
    void initialize() {

        dpInicio.setValue(LocalDate.now());
        dpFin.setValue(LocalDate.now());
    }

    public void setApp(App app) {
        this.app = app;
        controller = new ConsultaController(app.getGimnasio());
    }

    @FXML
    void onConsultarTelefono() {
        String telefono = txtTelefono.getText();
        Cliente cliente = controller.buscarClienteTelefono(telefono);

        if (cliente == null) {
            lblResultadoTelefono.setText("No existe un cliente con ese teléfono.");
            return;
        }

        boolean perfecto = controller.telefonoEsPerfecto(cliente);

        String resultadoNumero = perfecto ? "es un número perfecto" : "no es un número perfecto";

        lblResultadoTelefono.setText(
                "Cliente: "
                        + cliente.getNombreCompleto()
                        + "\nEl teléfono "
                        + telefono
                        + " "
                        + resultadoNumero
                        + "."
        );
    }

    @FXML
    void onCalcularIngresos() {

        LocalDate inicio =
                dpInicio.getValue();

        LocalDate fin =
                dpFin.getValue();

        if (inicio == null || fin == null) {

            lblIngresos.setText(
                    "Debe seleccionar las dos fechas."
            );

            return;
        }

        if (inicio.isAfter(fin)) {

            lblIngresos.setText(
                    "La fecha inicial no puede ser posterior a la fecha final."
            );

            return;
        }

        double total =
                controller.calcularIngresos(
                        inicio,
                        fin
                );

        lblIngresos.setText(
                "Ingresos generados: $"
                        + String.format(
                        "%,.2f",
                        total
                )
        );
    }

    @FXML
    void onVolver() {
        app.openPrimary();
    }
}