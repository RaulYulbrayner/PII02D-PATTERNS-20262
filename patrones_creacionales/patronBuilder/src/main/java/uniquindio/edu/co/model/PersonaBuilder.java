package uniquindio.edu.co.model;

public class PersonaBuilder implements Cloneable {

    private String nombre;
    private int edad;
    private String fechaNacimiento;
    private String genero;
    private int telefono;
    private String email;

    /**
     * Metodo constructor que permite crear personsas de forma flexible
     * @param builder
     */
    public PersonaBuilder(Builder builder) {
        this.nombre = builder.nombre;
        this.edad = builder.edad;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.genero = builder.genero;
        this.telefono = builder.telefono;
        this.email = builder.fechaNacimiento;
    }

    /**
     * Metodo que permite clonar objetos de tipo PersonaBuilder
     * @return PersonaBuilder
     * @throws CloneNotSupportedException
     */
    public PersonaBuilder clonar() throws CloneNotSupportedException {
        return (PersonaBuilder) super.clone();
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

        /**
         * Metodo que permite construir el nombre de una persona
         * @param nombre de la persona
         * @return
         */
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
