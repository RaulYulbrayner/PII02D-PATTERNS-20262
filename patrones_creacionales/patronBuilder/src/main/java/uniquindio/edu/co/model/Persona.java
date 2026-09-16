package uniquindio.edu.co.model;

/**
 * Clase que permite mapear una persona
 */
public class Persona {

    private String nombre;
    private int edad;
    private String fechaNacimiento;
    private String genero;
    private int telefono;
    private String email;

    /**
     * Metodo constructor de la clase persona
     * @param nombre de la persona
     * @param edad de la persona
     * @param fechaNacimiento de la persona
     * @param genero de la persona
     * @param telefono de la persona
     * @param email de la persona
     */
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

    /**
     * Metodo que permite modificar la edad de una persona
     * @param edad
     */
    public void setEdad(int edad) {
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
