package co.edu.uniquindio.poo.piitechsolutions.model.adapter;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;

public interface ProveedorTecnicos {

    /**
     * Consulta la información de un técnico
     * correspondiente a una especialidad.
     * @param especialidad Especialidad técnica requerida.
     * @return Información del técnico consultado,
     * incluyendo su identificación, disponibilidad
     * y costo de desplazamiento.
     * @throws IllegalArgumentException Si la
     * especialidad recibida es nula.
     */
    InformacionTecnico consultarTecnico(EspecialidadTecnica especialidad);
}
