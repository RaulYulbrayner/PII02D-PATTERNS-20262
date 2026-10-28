package co.edu.uniquindio.poo.piitechsolutions.model;

import java.util.List;

public interface ServicioPersonalizable extends ElementoContratable {

    EspecialidadTecnica getEspecialidadReferencia();
    List<TipoComplemento> getComplementos();
    double getCostoBase();
}
