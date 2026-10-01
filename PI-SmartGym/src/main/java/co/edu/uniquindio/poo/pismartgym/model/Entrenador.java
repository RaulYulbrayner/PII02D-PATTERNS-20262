package co.edu.uniquindio.poo.pismartgym.model;

public class Entrenador {

    private String identificacion;
    private String nombre;
    private Especialidad especialidad;
    private String telefono;
    private double tarifaSesion;

    /**
     * Metodo que permite construir un entrenador.
     * @param identificacion identificación del entrenador
     * @param nombre nombre del entrenador
     * @param especialidad especialidad profesional
     * @param telefono teléfono
     * @param tarifaSesion tarifa cobrada por sesión
     */
    public Entrenador(String identificacion, String nombre, Especialidad especialidad, String telefono, double tarifaSesion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.tarifaSesion = tarifaSesion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getTarifaSesion() {
        return tarifaSesion;
    }

    public void setTarifaSesion(double tarifaSesion) {
        this.tarifaSesion = tarifaSesion;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especialidad=" + especialidad +
                ", telefono='" + telefono + '\'' +
                ", tarifaSesion=" + tarifaSesion +
                '}';
    }
}
