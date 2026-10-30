package uniquindio.edu.co.model.state;

import uniquindio.edu.co.model.Cajero;

public class EstadoBloqueado implements EstadoCajero {
    @Override
    public void retirarDinero(Cajero cajero, double monto) {
        System.out.println("Cajero bloqueado");
    }
}
