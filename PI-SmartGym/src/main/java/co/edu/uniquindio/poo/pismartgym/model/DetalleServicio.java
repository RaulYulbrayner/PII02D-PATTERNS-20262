package co.edu.uniquindio.poo.pismartgym.model;

/**
 * Representa un servicio adicional utilizado
 * dentro de una inscripción.
 */
public class DetalleServicio {

    private ServicioAdicional servicio;
    private int cantidad;

    /**
     * Metodo que permite construir un detalle de servicio.
     * @param servicio servicio utilizado
     * @param cantidad cantidad solicitada
     */
    public DetalleServicio(ServicioAdicional servicio, int cantidad) {
        this.servicio = servicio;
        this.cantidad = cantidad;
    }

    /**
     * Metodo que permite calcular el subtotal del servicio.
     * @return subtotal
     */
    public double calcularSubtotal() {
        return servicio.getPrecio() * cantidad;
    }

    public ServicioAdicional getServicio() {
        return servicio;
    }

    public int getCantidad() {
        return cantidad;
    }
}