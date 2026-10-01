package co.edu.uniquindio.poo.pismartgym.controller;

import co.edu.uniquindio.poo.pismartgym.model.Cliente;
import co.edu.uniquindio.poo.pismartgym.model.Gimnasio;

import java.util.List;

/**
 * Controlador encargado de coordinar las operaciones
 * relacionadas con los clientes.
 */
public class ClienteController {

    private final Gimnasio gimnasio;

    /**
     * Construye el controlador.
     *
     * @param gimnasio gimnasio administrado
     */
    public ClienteController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    public String agregarCliente(Cliente cliente) {
        return gimnasio.agregarCliente(cliente);
    }

    public Cliente buscarCliente(String documento) {
        return gimnasio.buscarCliente(documento);
    }

    public boolean eliminarCliente(String documento) {
        return gimnasio.eliminarCliente(documento);
    }

    public boolean actualizarCliente(String documento, Cliente clienteActualizado) {
        return gimnasio.actualizarCliente(documento, clienteActualizado);
    }

    public List<Cliente> obtenerClientes() {
        return gimnasio.getClientes();
    }
}
