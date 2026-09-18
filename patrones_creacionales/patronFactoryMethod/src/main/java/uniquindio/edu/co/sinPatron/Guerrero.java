package uniquindio.edu.co.sinPatron;

public class Guerrero extends Personaje {

    /**
     * Metodo constructor de la clase Guerrero
     * @param nombre
     */
    public Guerrero(String nombre) {
        super(nombre);
    }

    @Override
    public void atacar() {
        System.out.println("El guerrero: " + getNombre() + " atacando");
    }
}
