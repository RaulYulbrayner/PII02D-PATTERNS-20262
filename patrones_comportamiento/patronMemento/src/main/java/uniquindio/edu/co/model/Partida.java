package uniquindio.edu.co.model;

public class Partida {

    private Memento estadoGuardado;

    public void guardarPartida(Memento recuerdo){
        estadoGuardado = recuerdo;
    }

    public Memento recuperarPartida(){
        return estadoGuardado;
    }

}
