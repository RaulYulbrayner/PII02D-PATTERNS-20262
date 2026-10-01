package co.edu.uniquindio.poo.pismartgym.model.factory;

import co.edu.uniquindio.poo.pismartgym.model.EstadoPlan;
import co.edu.uniquindio.poo.pismartgym.model.PlanBasico;
import co.edu.uniquindio.poo.pismartgym.model.PlanEntrenamiento;

/**
 * Creador concreto encargado de construir
 * planes básicos.
 */
public class CreadorPlanBasico extends CreadorPlan {

    /**
     * Crea un plan básico utilizando los datos recibidos.
     * @param datos información del plan
     * @return plan básico creado
     */
    @Override
    public PlanEntrenamiento crearPlan(DatosPlan datos) {
        return new PlanBasico(datos.getCodigo(), datos.getNombre(), datos.getDescripcion(), datos.getDuracionMeses(), datos.getValorMensual(), datos.getEstado());
    }
}
