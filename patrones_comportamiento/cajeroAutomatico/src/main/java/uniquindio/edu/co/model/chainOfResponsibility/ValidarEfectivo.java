package uniquindio.edu.co.model.chainOfResponsibility;

import uniquindio.edu.co.model.Cajero;

public class ValidarEfectivo extends Validador {
    @Override
    protected boolean validarActual(double monto, Cajero cajero) {
        boolean valido = monto <= cajero.getEfectivo();
        if(!valido){
            System.out.println("Efectivo insuficiente");
        }
        return valido;
    }
}
