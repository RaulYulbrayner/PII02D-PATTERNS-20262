package co.edu.uniquindio.poo.pismartgym.controller;

import co.edu.uniquindio.poo.pismartgym.model.Cliente;
import co.edu.uniquindio.poo.pismartgym.model.Gimnasio;
import co.edu.uniquindio.poo.pismartgym.services.ConsultaCliente;
import co.edu.uniquindio.poo.pismartgym.services.IngresoService;

import java.time.LocalDate;

/**
 * Controlador encargado de coordinar las consultas
 * especiales realizadas en SmartGym.
 */
public class ConsultaController {

    private final Gimnasio gimnasio;

    private final ConsultaCliente consultaClienteService;
    private final IngresoService ingresoService;

    /**
     * Construye el controlador de consultas.
     *
     * @param gimnasio gimnasio administrado
     */
    public ConsultaController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
        this.consultaClienteService = new ConsultaCliente();
        this.ingresoService = new IngresoService();
    }

    /**
     * Busca un cliente mediante su teléfono.
     * @param telefono teléfono buscado
     * @return cliente encontrado o null
     */
    public Cliente buscarClienteTelefono(String telefono) {
        return consultaClienteService.buscarPorTelefono(gimnasio.getClientes(), telefono);
    }

    /**
     * Determina si el teléfono de un cliente corresponde
     * a un número perfecto.
     * @param cliente cliente evaluado
     * @return true si el teléfono es perfecto
     */
    public boolean telefonoEsPerfecto(Cliente cliente) {
        return consultaClienteService.telefonoEsPerfecto(cliente);
    }

    /**
     * Calcula los ingresos obtenidos dentro
     * de un periodo determinado.
     * @param fechaInicial fecha inicial
     * @param fechaFinal fecha final
     * @return ingresos generados
     */
    public double calcularIngresos(LocalDate fechaInicial, LocalDate fechaFinal) {
        return ingresoService.calcularIngresos(gimnasio.getInscripciones(), fechaInicial, fechaFinal);
    }
}
