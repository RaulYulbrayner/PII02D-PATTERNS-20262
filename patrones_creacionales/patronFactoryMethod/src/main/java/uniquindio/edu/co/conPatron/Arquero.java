package uniquindio.edu.co.conPatron;

public class Arquero extends Personaje {

    /**
     * Metodo construtor de la clase Arquero
     * @param nombre
     */
    public Arquero(String nombre) {
        super(nombre);
    }

    @Override
    public void atacar() {
        System.out.println("El arquero: " + getNombre() + " atacando");
    }
}
