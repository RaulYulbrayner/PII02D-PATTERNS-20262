package co.edu.uniquindio.poo.pismartgym.controller;


import co.edu.uniquindio.poo.pismartgym.model.Entrenador;
import co.edu.uniquindio.poo.pismartgym.model.Gimnasio;

import java.util.List;

/**
 * Controlador de las operaciones relacionadas
 * con los entrenadores.
 */
public class EntrenadorController {

    private final Gimnasio gimnasio;

    public EntrenadorController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    public String agregarEntrenador(Entrenador entrenador) {
        return gimnasio.agregarEntrenador(entrenador);
    }

    public boolean actualizarEntrenador(String identificacion, Entrenador entrenador) {
        return gimnasio.actualizarEntrenador(identificacion, entrenador);
    }

    public boolean eliminarEntrenador(String identificacion) {
        return gimnasio.eliminarEntrenador(identificacion);
    }

    public List<Entrenador> obtenerEntrenadores() {
        return gimnasio.getEntrenadores();
    }
}
