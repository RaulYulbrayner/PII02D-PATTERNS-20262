package uniquindio.edu.co;

import uniquindio.edu.co.model.AdapterEntradaUSB;
import uniquindio.edu.co.model.CargadorUSBC;
import uniquindio.edu.co.model.Celular;
import uniquindio.edu.co.model.EntradaUSB;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Celular celular = new Celular();
        EntradaUSB usb = new EntradaUSB();
        CargadorUSBC adapter = new AdapterEntradaUSB(usb);
        celular.cargar(adapter);
    }

}