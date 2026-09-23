package uniquindio.edu.co.factory;

import uniquindio.edu.co.arma.Arco;
import uniquindio.edu.co.arma.Arma;
import uniquindio.edu.co.armadura.Armadura;
import uniquindio.edu.co.armadura.ArmaduraMetalica;
import uniquindio.edu.co.personaje.Personaje;
import uniquindio.edu.co.personaje.Soldado;

public class FabricaFantasia implements FabricaVideoJuego {
    @Override
    public Personaje crearPersonaje() {
        return new Soldado();
    }

    @Override
    public Arma crearArma() {
        return new Arco();
    }

    @Override
    public Armadura crearArmadura() {
        return new ArmaduraMetalica();
    }

}
