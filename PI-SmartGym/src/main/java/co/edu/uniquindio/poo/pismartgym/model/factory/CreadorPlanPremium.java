package co.edu.uniquindio.poo.pismartgym.model.factory;

import co.edu.uniquindio.poo.pismartgym.model.PlanEntrenamiento;
import co.edu.uniquindio.poo.pismartgym.model.PlanPremium;

/**
 * Creador concreto encargado de construir
 * planes premium.
 */
public class CreadorPlanPremium extends CreadorPlan {

    /**
     * Metodo que permite crear un plan premium utilizando los datos recibidos.
     * @param datos información del plan
     * @return plan premium creado
     */
    @Override
    public PlanEntrenamiento crearPlan(DatosPlan datos) {
        return new PlanPremium(datos.getCodigo(), datos.getNombre(), datos.getDescripcion(), datos.getDuracionMeses(), datos.getValorMensual(), datos.getEstado());
    }
}
