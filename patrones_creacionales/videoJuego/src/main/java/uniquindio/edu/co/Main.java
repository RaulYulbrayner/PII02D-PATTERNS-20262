package uniquindio.edu.co;

import uniquindio.edu.co.model.Juego;
import uniquindio.edu.co.model.Jugador;
import uniquindio.edu.co.model.Mision;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        //Jugadores
        Jugador jugador1 = new Jugador("Raul", 1, 5);
        Jugador jugador2 = new Jugador("Yulbraynner", 10, 55);
        Jugador jugador3 = new Jugador("Linda", 80, 79);
        System.out.println(jugador1);
        System.out.println(jugador2);
        System.out.println(jugador3);

        //Misiones
        Mision mision1 = new Mision("Rescate",25, 30, jugador3);
        Mision mision2 = new Mision("Vida",15, 20, jugador1);
        System.out.println(mision1);
        System.out.println(mision2);

        //Juego
        Juego juego1 = Juego.getInstance();
        //Juego juego2 = Juego.getInstance();

        juego1.agregarJugador(jugador1);
        //juego2.agregarJugador(jugador3);
        juego1.agregarMision(mision1);
       // juego2.agregarMision(mision2);
        System.out.println(juego1);
        //System.out.println(juego2);

       // System.out.println(juego1 == juego2);

        Juego juego3 = new Juego();
        System.out.println(juego1 == juego3);

    }

}