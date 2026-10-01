package co.edu.uniquindio.poo.pismartgym.model;

public class PlanPersonalizado extends PlanEntrenamiento {

    private int cantidadSesiones;
    private Especialidad especialidadRequerida;
    private String objetivosCliente;

    /**
     * Metodo que permite construir un plan personalizado.
     * @param codigo código
     * @param nombre nombre
     * @param descripcion descripción
     * @param duracionMeses duración
     * @param valorMensual valor mensual
     * @param estado estado
     * @param cantidadSesiones cantidad de sesiones
     * @param especialidadRequerida especialidad requerida
     * @param objetivosCliente objetivos del cliente
     */
    public PlanPersonalizado(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado, int cantidadSesiones, Especialidad especialidadRequerida, String objetivosCliente) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
        this.cantidadSesiones = cantidadSesiones;
        this.especialidadRequerida = especialidadRequerida;
        this.objetivosCliente = objetivosCliente;
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public Especialidad getEspecialidadRequerida() {
        return especialidadRequerida;
    }

    public void setEspecialidadRequerida(
            Especialidad especialidadRequerida) {

        this.especialidadRequerida = especialidadRequerida;
    }

    public String getObjetivosCliente() {
        return objetivosCliente;
    }

    public void setObjetivosCliente(String objetivosCliente) {
        this.objetivosCliente = objetivosCliente;
    }
}
