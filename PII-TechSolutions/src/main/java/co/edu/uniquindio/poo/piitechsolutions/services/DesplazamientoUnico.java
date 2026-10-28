package co.edu.uniquindio.poo.piitechsolutions.services;

import co.edu.uniquindio.poo.piitechsolutions.model.adapter.InformacionTecnico;

import java.util.List;

public class DesplazamientoUnico implements PoliticaDesplazamiento {

    /**
     * Calcula un único costo de desplazamiento.
     * Recorre los resultados de las consultas
     * y conserva el mayor costo encontrado.
     * No permite calcular el desplazamiento si
     * alguno de los técnicos consultados no está
     * disponible, porque la solicitud no estaría
     * preparada para su confirmación.
     * @param consultas Consultas de los técnicos
     * requeridos para atender la solicitud.
     * @return Mayor costo de desplazamiento
     * encontrado, o cero si la lista está vacía.
     * @throws IllegalArgumentException Si la lista
     * es nula o contiene información inválida.
     * @throws IllegalStateException Si algún técnico
     * no se encuentra disponible.
     */
    @Override
    public double calcularCostoDesplazamiento(List<TecnicoService.ConsultaTecnico> consultas) {
        if (consultas == null) {
            throw new IllegalArgumentException("La lista de consultas es obligatoria");
        }
        double mayorDesplazamiento = 0;

        for (TecnicoService.ConsultaTecnico consulta : consultas) {
            if (consulta == null) {
                throw new IllegalArgumentException("No se permiten consultas nulas");
            }
            InformacionTecnico tecnico = consulta.getInformacion();
            if (tecnico == null) {
                throw new IllegalArgumentException("La información del técnico es obligatoria");
            }
            if (!tecnico.disponible()) {
                throw new IllegalStateException("No se puede calcular el desplazamiento " + "porque hay técnicos no disponibles");
            }
            double costo = tecnico.costoDesplazamiento();
            if (!Double.isFinite(costo) || costo < 0) {
                throw new IllegalArgumentException("El costo de desplazamiento no es válido");
            }
            if (costo > mayorDesplazamiento) {
                mayorDesplazamiento = costo;
            }
        }
        return mayorDesplazamiento;
    }
}

