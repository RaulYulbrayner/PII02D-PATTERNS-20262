package uniquindio.edu.co.model;

import java.util.List;

public class Mision {

    private String nombre;
    private int dificultad;
    private int experienciaObtenida;
    private Jugador jugador;

    public Mision(String nombre, int dificultad, int experienciaObtenida, Jugador jugador) {
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.experienciaObtenida = experienciaObtenida;
        this.jugador = jugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getExperienciaObtenida() {
        return experienciaObtenida;
    }

    public void setExperienciaObtenida(int experienciaObtenida) {
        this.experienciaObtenida = experienciaObtenida;
    }

    @Override
    public String toString() {
        return "Mision{" +
                "nombre='" + nombre + '\'' +
                ", dificultad=" + dificultad +
                ", experienciaObtenida=" + experienciaObtenida +
                ", jugador=" + jugador +
                '}';
    }
}
