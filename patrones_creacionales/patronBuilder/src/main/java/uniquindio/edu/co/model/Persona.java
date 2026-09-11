package uniquindio.edu.co.model;

public class Persona {

    private String nombre;
    private int edad;
    private String fechaNacimiento;
    private String genero;
    private int telefono;
    private String email;

    public Persona(String nombre, int edad, String fechaNacimiento, String genero, int telefono, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.telefono = telefono;
        this.email = email;
    }

    public Persona(String email, int telefono, String nombre) {
        this.email = email;
        this.telefono = telefono;
        this.nombre = nombre;
    }

    public Persona(String nombre, int edad, String fechaNacimiento, String genero, int telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.telefono = telefono;
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", genero='" + genero + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                '}';
    }
}
