package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public final class Juego {

    private String nombre;
    private String clasificacion;
    private List<Jugador> listJugadores;
    private List<Mision> listMisiones;
    private List<Inventario> listInventario;
    private static Juego instance;

    /**
     * Metodo constructor de clase juego
     */
    public Juego() {
        nombre = "Reino Perdido";
        clasificacion = "Niños";
        this.listJugadores = new ArrayList<>();
        this.listMisiones = new ArrayList<>();
        this.listInventario = new ArrayList<>();
    }

    /**
     * Metodo que permite crear la instacia unica de la clase juego
     * @return instance
     */
    public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
        }
        return instance;
    }

    /**
     * Metodo que permite agregar jugadores al juego
     * @param jugador
     */
    public void agregarJugador(Jugador jugador){
        listJugadores.add(jugador);
    }

    /**
     * Metodo que permite agregar misiones al juego
     * @param mision
     */
    public void agregarMision(Mision mision){
        listMisiones.add(mision);
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "nombre='" + nombre + '\'' +
                ", clasificacion='" + clasificacion + '\'' +
                ", listJugadores=" + listJugadores +
                ", listMisiones=" + listMisiones +
                ", listInventario=" + listInventario +
                '}';
    }
}
