package uniquindio.edu.co.services;

import uniquindio.edu.co.model.Cafe;

public class FacturaService {

    public void mostrarFactura(Cafe cafe){
        System.out.println("Factura de Venta");
        System.out.println(cafe.descripcion());
        System.out.println("Costo: " + cafe.costo());
    }

}
