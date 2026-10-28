package co.edu.uniquindio.poo.piitechsolutions.viewController;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputControl;

import java.text.NumberFormat;
import java.util.Locale;

public final class Ui {

    private Ui() {}

    /**
     * Muestra un mensaje informativo al usuario.
     * @param titulo Título de la ventana.
     * @param mensaje Contenido del mensaje.
     */
    public static void mostrarInformacion(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Muestra un mensaje de error al usuario.
     * @param titulo Título de la ventana.
     * @param mensaje Descripción del error.
     */
    public static void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Muestra un mensaje de advertencia al usuario.
     * @param titulo Título de la ventana.
     * @param mensaje Descripción de la advertencia.
     */
    public static void mostrarAdvertencia(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Convierte un valor numérico a una representación
     * monetaria en pesos colombianos.
     * Utiliza el formato regional de Colombia.
     * No modifica el valor original ni realiza
     * operaciones de negocio.
     * @param valor Valor monetario de tipo double.
     * @return Valor formateado como moneda colombiana.
     * @throws IllegalArgumentException Si el valor
     * no es un número finito.
     */
    public static String formatoMoneda(double valor) {
        if (!Double.isFinite(valor)) {
            throw new IllegalArgumentException("El valor monetario no es válido");
        }
        Locale colombia = new Locale("es", "CO");
        NumberFormat formato = NumberFormat.getCurrencyInstance(colombia);
        formato.setMinimumFractionDigits(0);
        formato.setMaximumFractionDigits(2);
        return formato.format(valor);
    }

    /**
     * Obtiene el contenido de un campo de texto
     * y verifica que no se encuentre vacío.
     * Elimina los espacios al inicio y al final.
     * @param campo Control JavaFX que contiene el texto.
     * @param nombreCampo Nombre utilizado en el mensaje
     * de validación.
     * @return Texto validado sin espacios exteriores.
     * @throws IllegalArgumentException Si el campo
     * es nulo o no contiene un valor válido.
     */
    public static String obtenerTextoObligatorio(TextInputControl campo, String nombreCampo) {
        if (campo == null) {
            throw new IllegalArgumentException("El control del formulario no existe");
        }
        String texto = campo.getText();
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " es obligatorio");
        }
        return texto.trim();
    }
}
