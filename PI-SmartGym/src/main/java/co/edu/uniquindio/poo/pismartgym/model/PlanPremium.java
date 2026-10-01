package co.edu.uniquindio.poo.pismartgym.model;

public class PlanPremium extends PlanEntrenamiento {

    /**
     * Metodo que permite construir un plan premium.
     * @param codigo código
     * @param nombre nombre
     * @param descripcion descripción
     * @param duracionMeses duración
     * @param valorMensual valor mensual
     * @param estado estado
     */
    public PlanPremium(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado) {
        super(codigo, nombre, descripcion,
                duracionMeses, valorMensual, estado);
    }
}
