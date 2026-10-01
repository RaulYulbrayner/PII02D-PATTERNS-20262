package co.edu.uniquindio.poo.pismartgym.controller;

import co.edu.uniquindio.poo.pismartgym.model.factory.*;
import co.edu.uniquindio.poo.pismartgym.model.Gimnasio;
import co.edu.uniquindio.poo.pismartgym.model.PlanEntrenamiento;

import java.util.List;

/**
 * Controlador encargado de gestionar los planes
 * y delegar su construcción al Factory Method.
 */
public class PlanController {

    private final Gimnasio gimnasio;

    public PlanController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    /**
     * Crea un plan utilizando un creador concreto.
     * @param creador Factory Method seleccionado
     * @param datos datos del plan
     * @return resultado de la operación
     */
    public String crearPlan(CreadorPlan creador, DatosPlan datos) {
        PlanEntrenamiento plan = creador.crearPlan(datos);
        return gimnasio.agregarPlan(plan);
    }

    public List<PlanEntrenamiento> obtenerPlanes() {
        return gimnasio.getPlanes();
    }
}
