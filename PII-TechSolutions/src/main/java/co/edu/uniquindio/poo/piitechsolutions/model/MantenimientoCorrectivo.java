package co.edu.uniquindio.poo.piitechsolutions.model;

public final class MantenimientoCorrectivo extends ServicioTecnologico {

    /**
     * Metodo que construye un servicio de mantenimiento correctivo.
     * @param codigo Código del servicio.
     * @param nombre Nombre del servicio.
     * @param descripcion Descripción del servicio.
     * @param costoBase Costo inicial.
     * @param especialidad Especialidad técnica.
     * @param presencial Si requiere atención presencial.
     */
    public MantenimientoCorrectivo(String codigo, String nombre, String descripcion, double costoBase, EspecialidadTecnica especialidad, boolean presencial) {
        super(codigo, nombre, descripcion, costoBase, especialidad, presencial);
    }

    @Override
    public TipoServicio getTipo() {
        return TipoServicio.MANTENIMIENTO_CORRECTIVO;
    }
}
