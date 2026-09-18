package uniquindio.edu.co.conPatron;

public class Juego {

    /**
     * Metodo que permite iniciar el juego definiendo a los personas
     * @param fabrica que crea el personaje en el juego
     * @param nombre del personaje
     */
    public void iniciarJuego(FactoryPersonaje fabrica, String nombre){
        Personaje personaje = fabrica.crearPersonaje(nombre);
        personaje.atacar();
    }

}
