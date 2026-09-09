package uniquindio.edu.co.model;

public class Inventario {

    private String nombre;
    private int capacidad;

    public Inventario(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
