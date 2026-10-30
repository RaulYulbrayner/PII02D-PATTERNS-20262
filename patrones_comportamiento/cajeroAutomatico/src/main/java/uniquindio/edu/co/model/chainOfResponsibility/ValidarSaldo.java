package uniquindio.edu.co.model.chainOfResponsibility;

import uniquindio.edu.co.model.Cajero;

public class ValidarSaldo extends Validador {
    @Override
    protected boolean validarActual(double monto, Cajero cajero) {
        boolean valido = monto > 0 && monto <= cajero.getSaldo();
        if(!valido){
            System.out.println("Saldo insuficiente para retiro por el cajero");
        }
        return valido;
    }
}
