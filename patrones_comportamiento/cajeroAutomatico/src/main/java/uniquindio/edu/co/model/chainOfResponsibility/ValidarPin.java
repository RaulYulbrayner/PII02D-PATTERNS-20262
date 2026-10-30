package uniquindio.edu.co.model.chainOfResponsibility;

import uniquindio.edu.co.model.Cajero;

public class ValidarPin extends Validador {

    @Override
    protected boolean validarActual(double monto, Cajero cajero) {
        boolean valido = cajero.isPinCorrecto();
        if(!valido){
            System.out.println("Pin incorrecto");
        }
        return valido;
    }

}
