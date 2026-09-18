package uniquindio.edu.co.conPatron;

public class FactoryGuerrero extends FactoryPersonaje {

    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Guerrero(nombre);
    }

}
