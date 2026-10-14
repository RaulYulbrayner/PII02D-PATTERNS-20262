package uniquindio.edu.co.services;

import uniquindio.edu.co.model.Cafe;

public class CafeteriaFacade {

    private final PedidoService pedido;
    private final PagoService pago;
    private final FacturaService factura;

    public CafeteriaFacade(){
        pedido = new PedidoService();
        pago = new PagoService();
        factura = new FacturaService();
    }

    public void comprarCafe(Cafe cafe){
        pedido.registrarPedido(cafe);
        pago.pagar(cafe.costo());
        factura.mostrarFactura(cafe);
    }


}
