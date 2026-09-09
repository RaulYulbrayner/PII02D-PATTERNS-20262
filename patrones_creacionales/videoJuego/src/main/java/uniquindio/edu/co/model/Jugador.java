package uniquindio.edu.co.model;

public class Jugador {

    private String nombre;
    private int nivel, experiencia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public Jugador(String nombre, int nivel, int experiencia) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", experiencia=" + experiencia +
                '}';
    }
}
