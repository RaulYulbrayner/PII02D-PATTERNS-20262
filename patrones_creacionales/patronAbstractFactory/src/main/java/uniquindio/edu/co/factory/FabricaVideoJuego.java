package uniquindio.edu.co.factory;

import uniquindio.edu.co.arma.Arma;
import uniquindio.edu.co.armadura.Armadura;
import uniquindio.edu.co.personaje.Personaje;

public interface FabricaVideoJuego {

    Personaje crearPersonaje();
    Arma crearArma();
    Armadura crearArmadura();

}
