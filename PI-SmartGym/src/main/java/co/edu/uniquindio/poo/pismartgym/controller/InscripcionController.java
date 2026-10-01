package co.edu.uniquindio.poo.pismartgym.controller;

import co.edu.uniquindio.poo.pismartgym.model.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador encargado de gestionar las inscripciones.
 */
public class InscripcionController {

    private final Gimnasio gimnasio;

    public InscripcionController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    /**
     * Crea una inscripción mediante el patrón Builder.
     * @param codigo código
     * @param fecha fecha de inscripción
     * @param cliente cliente
     * @param plan plan
     * @param descuento descuento
     * @param asignacion asignación opcional
     * @return inscripción construida
     */
    public Inscripcion crearInscripcion(String codigo, LocalDate fecha, Cliente cliente, PlanEntrenamiento plan, double descuento, AsignacionEntrenador asignacion) {
        Inscripcion.Builder builder = new Inscripcion.Builder()
                        .codigo(codigo)
                        .fechaInscripcion(fecha)
                        .cliente(cliente)
                        .plan(plan)
                        .porcentajeDescuento(descuento);

        if (asignacion != null) {
            builder.asignacionEntrenador(
                    asignacion
            );
        }
        return builder.build();
    }

    public String guardarInscripcion(Inscripcion inscripcion) {
        return gimnasio.agregarInscripcion(
                inscripcion
        );
    }

    public String agregarServicio(Inscripcion inscripcion, ServicioAdicional servicio, int cantidad) {
        return inscripcion.agregarServicio(servicio, cantidad);
    }

    public List<Cliente> obtenerClientes() {
        return gimnasio.getClientes();
    }

    public List<PlanEntrenamiento> obtenerPlanes() {
        return gimnasio.getPlanes();
    }

    public List<Entrenador> obtenerEntrenadores() {
        return gimnasio.getEntrenadores();
    }

    public List<ServicioAdicional> obtenerServicios() {
        return gimnasio.getServiciosAdicionales();
    }

    public List<Inscripcion> obtenerInscripciones() {
        return gimnasio.getInscripciones();
    }
}
