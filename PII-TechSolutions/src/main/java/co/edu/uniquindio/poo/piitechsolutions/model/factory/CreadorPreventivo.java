package co.edu.uniquindio.poo.piitechsolutions.model.factory;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.MantenimientoPreventivo;
import co.edu.uniquindio.poo.piitechsolutions.model.ServicioTecnologico;

public class CreadorPreventivo extends CreadorServicio {

    /**
     * Metodo que construye un mantenimiento preventivo.
     * @param codigo Código del servicio.
     * @param nombre Nombre del servicio.
     * @param descripcion Descripción del servicio.
     * @param costoBase Costo base del servicio.
     * @param especialidad Especialidad técnica.
     * @param presencial Indica si requiere atención presencial.
     * @return Servicio de mantenimiento preventivo.
     */
    @Override
    public ServicioTecnologico crearServicio(String codigo, String nombre, String descripcion, double costoBase, EspecialidadTecnica especialidad, boolean presencial) {
        return new MantenimientoPreventivo(codigo, nombre, descripcion, costoBase, especialidad, presencial);
    }
}
