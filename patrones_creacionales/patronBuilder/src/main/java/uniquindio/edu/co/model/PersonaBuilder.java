package uniquindio.edu.co.model;

public class PersonaBuilder {

    private String nombre;
    private int edad;
    private String fechaNacimiento;
    private String genero;
    private int telefono;
    private String email;

    public PersonaBuilder(Builder builder) {
        this.nombre = builder.nombre;
        this.edad = builder.edad;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.genero = builder.genero;
        this.telefono = builder.telefono;
        this.email = builder.fechaNacimiento;
    }

    //Setter y getter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PersonaBuilder{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", genero='" + genero + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean cambiarEstado(){
        return false;
    }

    public static class Builder{
        private String nombre;
        private int edad;
        private String fechaNacimiento;
        private String genero;
        private int telefono;
        private String email;

        public Builder nombre(String nombre){
            this.nombre = nombre;
            return this;
        }

        public Builder edad(int edad){
            this.edad = edad;
            return this;
        }

        public Builder fechaNacimiento(String fechaNacimiento){
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder genero(String genero){
            this.genero = genero;
            return this;
        }

        public Builder telefono(int telefono){
            this.telefono = telefono;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public PersonaBuilder build(){
            return new PersonaBuilder(this);
        }
    }
}
