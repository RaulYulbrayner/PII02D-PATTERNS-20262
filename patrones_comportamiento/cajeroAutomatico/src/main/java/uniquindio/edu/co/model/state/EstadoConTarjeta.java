package uniquindio.edu.co.model.state;

import uniquindio.edu.co.model.Cajero;

public class EstadoConTarjeta implements EstadoCajero {
    @Override
    public void retirarDinero(Cajero cajero, double monto) {
        cajero.validarRetiro(monto);
    }
}
