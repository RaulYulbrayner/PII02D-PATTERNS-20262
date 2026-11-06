package uniquindio.edu.co;

import uniquindio.edu.co.model.Partida;
import uniquindio.edu.co.model.Personaje;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Personaje personaje = new Personaje();
        Partida partida = new Partida();
        System.out.println("Inicio");
        System.out.println(personaje.mostrarEstado());

        System.out.println("Avance");
        partida.guardarPartida(personaje.guardarEstado());

        System.out.println("Avance - recibe daño");
        personaje.mover("Nivel 1 - paso");
        personaje.recibirDanio(10);

        System.out.println("Avance nuevo estado");
        System.out.println(personaje.mostrarEstado());

        System.out.println("Estado restaurado");
        personaje.restaurarEstado(partida.recuperarPartida());
        System.out.println(personaje.mostrarEstado());

    }

}