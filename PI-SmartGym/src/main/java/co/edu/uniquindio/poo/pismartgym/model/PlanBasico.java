package co.edu.uniquindio.poo.pismartgym.model;

public class PlanBasico extends PlanEntrenamiento {

    /**
     * Metodo que permite construir un plan básico.
     * @param codigo código del plan
     * @param nombre nombre
     * @param descripcion descripción
     * @param duracionMeses duración en meses
     * @param valorMensual valor mensual
     * @param estado estado
     */
    public PlanBasico(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado) {
        super(codigo, nombre, descripcion,
                duracionMeses, valorMensual, estado);
    }
}
