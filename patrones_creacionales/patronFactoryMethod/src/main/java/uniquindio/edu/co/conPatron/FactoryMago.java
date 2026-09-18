package uniquindio.edu.co.conPatron;

public class FactoryMago extends FactoryPersonaje{

    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Mago(nombre);
    }
}
