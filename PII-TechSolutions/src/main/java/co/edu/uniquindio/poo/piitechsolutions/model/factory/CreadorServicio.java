package co.edu.uniquindio.poo.piitechsolutions.model.factory;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.ServicioTecnologico;

public abstract class CreadorServicio {

    /**
     * Metodo que crea un servicio tecnológico.
     * @param codigo Código identificador del servicio.
     * @param nombre Nombre del servicio.
     * @param descripcion Descripción del servicio.
     * @param costoBase Costo base en pesos colombianos.
     * @param especialidad Especialidad técnica requerida.
     * @param presencial Indica si requiere atención presencial.
     * @return Servicio tecnológico creado.
     * @throws IllegalArgumentException Si los datos son inválidos.
     */
    public abstract ServicioTecnologico crearServicio(
            String codigo,
            String nombre,
            String descripcion,
            double costoBase,
            EspecialidadTecnica especialidad,
            boolean presencial);
}
