package uniquindio.edu.co.sinPatron;

public class Mago extends Personaje {

    /**
     * Metodo constructor de la clase Mago
     * @param nombre
     */
    public Mago(String nombre){
        super(nombre);
    }

    @Override
    public void atacar() {
        System.out.println("El mago: " + getNombre() + " atacando");
    }
}
