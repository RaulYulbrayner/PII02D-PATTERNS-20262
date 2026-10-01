package co.edu.uniquindio.poo.pismartgym.model.factory;

import co.edu.uniquindio.poo.pismartgym.model.PlanEntrenamiento;

/**
 * Define el Factory Method utilizado para crear
 * los diferentes tipos de planes de entrenamiento.
 */
public abstract class CreadorPlan {

    /**
     * Factory Method encargado de crear un plan.
     * Cada creador concreto decide qué tipo específico
     * de plan debe instanciar.
     * @param datos información necesaria para crear el plan
     * @return plan de entrenamiento creado
     */
    public abstract PlanEntrenamiento crearPlan(DatosPlan datos);

}