package uniquindio.edu.co.services;

import uniquindio.edu.co.model.Cafe;

public class PedidoService {

    public void registrarPedido(Cafe cafe){
        System.out.println("Pedido registrado: " + cafe.descripcion());
    }

}
