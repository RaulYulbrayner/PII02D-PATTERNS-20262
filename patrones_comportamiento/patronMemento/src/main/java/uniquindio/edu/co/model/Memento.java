package uniquindio.edu.co.model;

public class Memento {

    private int vida;
    private String posicion;

    public Memento(int vida, String posicion){
        this.vida = vida;
        this.posicion = posicion;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
