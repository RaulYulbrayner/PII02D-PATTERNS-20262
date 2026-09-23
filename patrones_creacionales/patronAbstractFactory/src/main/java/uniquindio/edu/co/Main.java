package uniquindio.edu.co;

import uniquindio.edu.co.arma.Arma;
import uniquindio.edu.co.armadura.Armadura;
import uniquindio.edu.co.factory.FabricaFantasia;
import uniquindio.edu.co.factory.FabricaFuturista;
import uniquindio.edu.co.factory.FabricaVideoJuego;
import uniquindio.edu.co.personaje.Personaje;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FabricaVideoJuego fabrica = new FabricaFantasia();
        Personaje personaje = fabrica.crearPersonaje();
        Armadura armadura = fabrica.crearArmadura();
        Arma arma = fabrica.crearArma();
        System.out.println("----- FANTASIA -----");
        personaje.atacar();
        arma.usar();
        armadura.proteger();

        System.out.println();

        fabrica = new FabricaFuturista();
        personaje = fabrica.crearPersonaje();
        armadura = fabrica.crearArmadura();
        arma = fabrica.crearArma();
        System.out.println("----- FUTURISTA -----");
        personaje.atacar();
        arma.usar();
        armadura.proteger();

    }
}