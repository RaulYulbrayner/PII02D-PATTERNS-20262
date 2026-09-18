package uniquindio.edu.co.conPatron;

public class App {

    public static void main(String[] args){

        Juego juego = new Juego();

        FactoryPersonaje fabrica = new FactoryMago();
        juego.iniciarJuego(fabrica, "Mago Oscuro");

        fabrica = new FactoryGuerrero();
        juego.iniciarJuego(fabrica, "Raul");

        fabrica = new FactoryArquero();
        juego.iniciarJuego(fabrica, "Thor");

    }

}
