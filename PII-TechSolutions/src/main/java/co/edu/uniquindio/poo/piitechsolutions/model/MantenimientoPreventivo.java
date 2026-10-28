package co.edu.uniquindio.poo.piitechsolutions.model;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoServicio;

public final class MantenimientoPreventivo extends ServicioTecnologico {

    /**
     * Construye un servicio de mantenimiento preventivo.
     * @param codigo Código identificador del servicio.
     * @param nombre Nombre del servicio.
     * @param descripcion Descripción del servicio.
     * @param costoBase Costo base en pesos colombianos.
     * @param especialidad Especialidad técnica asociada.
     * @param presencial Indica si requiere atención presencial.
     * @throws IllegalArgumentException Si los datos
     * recibidos no cumplen las validaciones establecidas
     * en ServicioTecnologico.
     */
    public MantenimientoPreventivo(String codigo, String nombre, String descripcion, double costoBase, EspecialidadTecnica especialidad, boolean presencial) {
        super(codigo, nombre, descripcion, costoBase, especialidad, presencial);
    }

    @Override
    public TipoServicio getTipo() {
        return TipoServicio.MANTENIMIENTO_PREVENTIVO;
    }
}

