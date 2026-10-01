package co.edu.uniquindio.poo.pismartgym.services;

import co.edu.uniquindio.poo.pismartgym.model.Cliente;

import java.util.List;

/**
 * Servicio encargado de realizar consultas
 * relacionadas con los clientes.
 */
public class ConsultaCliente {

    /**
     * Metodo que permite buscar un cliente mediante su teléfono.
     * @param clientes lista de clientes
     * @param telefono teléfono buscado
     * @return cliente encontrado o null
     */
    public Cliente buscarPorTelefono(List<Cliente> clientes, String telefono) {
        for (Cliente cliente : clientes) {
            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Metodo que permite determinar si un número es perfecto.
     * @param numero número evaluado
     * @return true si es perfecto
     */
    public boolean esNumeroPerfecto(long numero) {
        boolean esPerfecto = false;
        if (numero > 1) {
            long suma = 1;
            for (long i = 2; i <= numero / 2; i++) {
                if (numero % i == 0) {
                    suma += i;
                }
            }
            esPerfecto = suma == numero;
        }
        return esPerfecto;
    }

    /**
     * Metodo que permite determina si el teléfono de un cliente
     * corresponde a un número perfecto.
     * @param cliente cliente
     * @return true si el teléfono es un número perfecto
     */
    public boolean telefonoEsPerfecto(Cliente cliente) {
        boolean esPerfecto = false;
        String telefonoTexto = cliente.getTelefono();
        try {
            long telefono = Long.parseLong(telefonoTexto);
            esPerfecto = esNumeroPerfecto(telefono);
        } catch (NumberFormatException e) {
            esPerfecto = false;
        }
        return esPerfecto;
    }

}