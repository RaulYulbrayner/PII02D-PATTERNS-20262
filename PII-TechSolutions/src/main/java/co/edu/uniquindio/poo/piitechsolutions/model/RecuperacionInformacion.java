package co.edu.uniquindio.poo.piitechsolutions.model;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoServicio;

public final class RecuperacionInformacion extends ServicioTecnologico {

    /**
     * Construye un servicio de recuperación de información.
     * @param codigo Código identificador del servicio.
     * @param nombre Nombre del servicio.
     * @param descripcion Descripción del servicio.
     * @param costoBase Costo base en pesos colombianos.
     * @param especialidad Especialidad técnica asociada.
     * @param presencial Indica si requiere atención presencial.
     * @throws IllegalArgumentException Si los datos
     * recibidos incumplen las validaciones definidas
     * en ServicioTecnologico.
     */
    public RecuperacionInformacion(String codigo, String nombre, String descripcion, double costoBase, EspecialidadTecnica especialidad, boolean presencial) {
        super(codigo, nombre, descripcion, costoBase, especialidad, presencial);
    }

    /**
     * Identifica el tipo concreto del servicio tecnológico.
     * Implementa el método abstracto definido
     * por ServicioTecnologico.
     * @return Tipo de recuperación de información.
     */
    @Override
    public TipoServicio getTipo() {
        return TipoServicio.RECUPERACION_INFORMACION;
    }
}

