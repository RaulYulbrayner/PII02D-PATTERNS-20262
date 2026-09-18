package uniquindio.edu.co.conPatron;

public abstract class Personaje {

    private String nombre;

    /**
     * Metodo constructor de la clase Personaje
     * @param nombre
     */
    public Personaje(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    /**
     * Metodo que permite declarar la forma de atacar
     */
    public abstract void atacar();

}
