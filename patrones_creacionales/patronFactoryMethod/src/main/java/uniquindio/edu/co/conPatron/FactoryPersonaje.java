package uniquindio.edu.co.conPatron;

public abstract class FactoryPersonaje {

    //Factory method
    public abstract Personaje crearPersonaje(String nombre);

    /**
     * Metodo que permite la creación de un personaje
     * @param nombre
     */
    public void mostrarAtaque(String nombre){
        Personaje personaje = crearPersonaje(nombre);
        personaje.atacar();
    }

}
