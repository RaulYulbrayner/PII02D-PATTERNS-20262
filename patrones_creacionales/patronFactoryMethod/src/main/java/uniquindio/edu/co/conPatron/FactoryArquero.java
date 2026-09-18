package uniquindio.edu.co.conPatron;

public class FactoryArquero extends FactoryPersonaje {

    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Arquero(nombre);
    }

}
