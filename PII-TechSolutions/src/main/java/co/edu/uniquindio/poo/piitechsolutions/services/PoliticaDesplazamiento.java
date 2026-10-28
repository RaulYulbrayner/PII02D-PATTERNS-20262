package co.edu.uniquindio.poo.piitechsolutions.services;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.adapter.InformacionTecnico;

import java.util.List;

/**
 * Define el contrato para calcular el costo
 * de desplazamiento de los técnicos requeridos
 * para atender una solicitud de TechSolutions.
 * Recibe las consultas realizadas por TecnicoService,
 * evitando que las implementaciones tengan que
 * comunicarse nuevamente con el proveedor externo.
 * Permite establecer diferentes reglas de cálculo
 * sin modificar la lógica de SolicitudService.
 * Favorece los principios abierto/cerrado (OCP)
 * e inversión de dependencias (DIP).
 */
public interface PoliticaDesplazamiento {

    /**
     * Calcula el costo de desplazamiento a partir
     * de la información de los técnicos consultados.
     * La disponibilidad de los técnicos debe ser
     * verificada previamente por SolicitudService.
     * Cuando no se requieren técnicos presenciales,
     * el resultado esperado es cero.
     * @param consultas Lista de consultas de técnicos
     * asociadas a las especialidades requeridas.
     * @return Costo de desplazamiento calculado
     * en pesos colombianos.
     * @throws IllegalArgumentException Si la lista
     * recibida es nula o contiene información inválida.
     */
    double calcularCostoDesplazamiento(List<TecnicoService.ConsultaTecnico> consultas);
}
