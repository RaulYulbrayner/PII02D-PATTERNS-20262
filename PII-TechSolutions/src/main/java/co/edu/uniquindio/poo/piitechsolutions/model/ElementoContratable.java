package co.edu.uniquindio.poo.piitechsolutions.model;
import java.util.List;

public interface ElementoContratable {

    String getCodigo();
    String getNombre();
    String getDescripcion();
    double calcularCosto();

    List<EspecialidadTecnica> obtenerEspecialidadesPresenciales();
}
