package uniquindio.edu.co;

import uniquindio.edu.co.model.Cajero;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Cajero cajero = new Cajero();

        //Insertar tarjeta
        System.out.println("Insertar tarjeta");
        cajero.insertarTarjeta();

        //Validar la informacion - pin
        System.out.println("Ingrese el pin");
        cajero.ingresarPin("12345");

        //Solicitar retiro
        System.out.println("Ingrese el valor a retirar");
        cajero.retirar(100000);

        //Mostrar resultado del retiro
        System.out.println("Saldo: $" + cajero.getSaldo());

    }

}