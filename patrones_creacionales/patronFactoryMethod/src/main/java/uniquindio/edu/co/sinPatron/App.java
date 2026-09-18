package uniquindio.edu.co.sinPatron;

public class App {

    public static void main(String[] args){

        Juego juego = new Juego();

        Personaje personaje = juego.crearPersonaje("Mago", "Mago Oscuro");
        Personaje personaje1 = juego.crearPersonaje("Guerrero", "Raul");
        Personaje personaje2 = juego.crearPersonaje("Arquero", "Thor");

        personaje.atacar();
        personaje1.atacar();
        personaje2.atacar();

    }

}
