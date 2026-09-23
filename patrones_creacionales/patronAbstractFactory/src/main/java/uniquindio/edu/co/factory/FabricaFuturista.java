package uniquindio.edu.co.factory;

import uniquindio.edu.co.arma.Arma;
import uniquindio.edu.co.arma.Espada;
import uniquindio.edu.co.armadura.Armadura;
import uniquindio.edu.co.armadura.ArmaduraEspecial;
import uniquindio.edu.co.personaje.Mago;
import uniquindio.edu.co.personaje.Personaje;

public class FabricaFuturista implements FabricaVideoJuego {

    @Override
    public Personaje crearPersonaje() {
        return new Mago();
    }

    @Override
    public Arma crearArma() {
        return new Espada();
    }

    @Override
    public Armadura crearArmadura() {
        return new ArmaduraEspecial();
    }

}
