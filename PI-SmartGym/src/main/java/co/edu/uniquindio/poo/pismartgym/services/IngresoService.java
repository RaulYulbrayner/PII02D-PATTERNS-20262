package co.edu.uniquindio.poo.pismartgym.services;

import co.edu.uniquindio.poo.pismartgym.model.Inscripcion;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio encargado de calcular los ingresos
 * generados por las inscripciones.
 */
public class IngresoService {

    /**
     * Metodo que permite calcular los ingresos generados durante
     * un periodo determinado.
     * @param inscripciones lista de inscripciones
     * @param fechaInicial fecha inicial
     * @param fechaFinal fecha final
     * @return total de ingresos
     */
    public double calcularIngresos(List<Inscripcion> inscripciones, LocalDate fechaInicial, LocalDate fechaFinal) {
        double total = 0;
        for (Inscripcion inscripcion : inscripciones) {
            LocalDate fecha = inscripcion.getFechaInscripcion();
            boolean dentroPeriodo = !fecha.isBefore(fechaInicial) && !fecha.isAfter(fechaFinal);
            if (dentroPeriodo) {
                total += inscripcion.calcularValorFinal();
            }
        }
        return total;
    }
}
