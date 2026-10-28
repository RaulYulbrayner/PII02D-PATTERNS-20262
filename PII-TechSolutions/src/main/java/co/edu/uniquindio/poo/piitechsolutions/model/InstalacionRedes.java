package co.edu.uniquindio.poo.piitechsolutions.model;

public final class InstalacionRedes extends ServicioTecnologico {

    /**
     * Metodo qye construye un servicio de instalación de redes.
     * @param codigo Código del servicio.
     * @param nombre Nombre del servicio.
     * @param descripcion Descripción del servicio.
     * @param costoBase Costo inicial.
     * @param especialidad Especialidad técnica.
     * @param presencial Si requiere atención presencial.
     */
    public InstalacionRedes(String codigo, String nombre, String descripcion, double costoBase, EspecialidadTecnica especialidad, boolean presencial) {
        super(codigo, nombre, descripcion, costoBase, especialidad, presencial);
    }

    @Override
    public TipoServicio getTipo() {
        return TipoServicio.INSTALACION_REDES;
    }
}
