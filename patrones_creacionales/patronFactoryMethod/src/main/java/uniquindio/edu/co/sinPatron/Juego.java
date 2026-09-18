package uniquindio.edu.co.sinPatron;

public class Juego {

    /**
     * Metodo que permite crear personajes en el juego
     * @param tipo de personaje
     * @param nombre del personaje
     * @return
     */
    public Personaje crearPersonaje(String tipo, String nombre){
        Personaje personaje = null;
        if(tipo.equals("Mago")){
            personaje = new Mago(nombre);
        } else if (tipo.equals("Guerrero")) {
            personaje = new Guerrero(nombre);
        } else if (tipo.equals("Arquero")) {
            personaje = new Arquero(nombre);
        }
        return personaje;
    }

}
