package co.edu.uniquindio.poo.pismartgym.model.factory;

import co.edu.uniquindio.poo.pismartgym.model.PlanEntrenamiento;
import co.edu.uniquindio.poo.pismartgym.model.PlanPersonalizado;

/**
 * Creador concreto encargado de construir
 * planes personalizados.
 */
public class CreadorPlanPersonalizado extends CreadorPlan {

    /**
     * Metodo que permite crear un plan personalizado utilizando tanto
     * la información general como la información
     * específica de este tipo de plan.
     * @param datos información necesaria para crear el plan
     * @return plan personalizado creado
     */
    @Override
    public PlanEntrenamiento crearPlan(DatosPlan datos) {
        return new PlanPersonalizado(datos.getCodigo(), datos.getNombre(), datos.getDescripcion(), datos.getDuracionMeses(), datos.getValorMensual(), datos.getEstado(), datos.getCantidadSesiones(), datos.getEspecialidadRequerida(), datos.getObjetivosCliente());
    }
}