package co.edu.uniquindio.poo.pismartgym.model;

/**
 * Representa la asignación de un entrenador a un cliente
 * para un determinado plan personalizado.
 */
public class AsignacionEntrenador {

    private Cliente cliente;
    private PlanPersonalizado plan;
    private Entrenador entrenador;

    /**
     * Metodo que permite construir una asignación.
     * @param cliente cliente
     * @param plan plan personalizado
     * @param entrenador entrenador responsable
     */
    public AsignacionEntrenador(Cliente cliente, PlanPersonalizado plan, Entrenador entrenador) {
        this.cliente = cliente;
        this.plan = plan;
        this.entrenador = entrenador;
    }

    /**
     * Metodo que permite calcular el costo correspondiente a las sesiones del entrenador.
     * @return costo de las sesiones
     */
    public double calcularCostoSesiones() {
        double costo = plan.getCantidadSesiones() * entrenador.getTarifaSesion();
        return costo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public PlanPersonalizado getPlan() {
        return plan;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }
}