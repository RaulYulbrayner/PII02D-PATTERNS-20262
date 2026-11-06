package uniquindio.edu.co.model;

public class Personaje {

    private int vida;
    private String nombre;
    private String posicion;

    public Personaje(){
        nombre = "Mario Bros";
        vida = 80;
        posicion = "Nivel 1";
    }

    public void mover(String nuevaPosicion){
        posicion = nuevaPosicion;
    }

    public void recibirDanio(int danio){
        vida = vida - danio;
    }

    public Memento guardarEstado(){
        Memento recuerdo = new Memento(vida, posicion);
        return recuerdo;
    }

    public void restaurarEstado(Memento recuerdo){
        vida = recuerdo.getVida();
        posicion = recuerdo.getPosicion();
    }

    public String mostrarEstado(){
        String estado = nombre + " tiene un vida de: " + vida + " puntos " + " y su posicion es: " + posicion;
        return estado;
    }

}
