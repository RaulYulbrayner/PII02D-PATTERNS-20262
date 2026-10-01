package co.edu.uniquindio.poo.pismartgym.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa la inscripción de un cliente a un plan de entrenamiento
 * dentro del gimnasio SmartGym.
 * La clase utiliza el patrón creacional Builder para permitir la
 * construcción flexible de las inscripciones.
 */
public class Inscripcion {

    private String codigo;
    private LocalDate fechaInscripcion;
    private Cliente cliente;
    private PlanEntrenamiento plan;
    private double porcentajeDescuento;
    private AsignacionEntrenador asignacionEntrenador;
    private ArrayList<DetalleServicio> servicios;

    /**
     * Constructor privado utilizado únicamente por el Builder.
     * @param builder constructor de la inscripción
     */
    private Inscripcion(Builder builder) {
        this.codigo = builder.codigo;
        this.fechaInscripcion = builder.fechaInscripcion;
        this.cliente = builder.cliente;
        this.plan = builder.plan;
        this.porcentajeDescuento = builder.porcentajeDescuento;
        this.asignacionEntrenador = builder.asignacionEntrenador;
        this.servicios = builder.servicios;
    }

    /**
     * Metodo que permite calcula el valor total de los servicios adicionales asociados a la inscripción.
     * @return valor total de servicios adicionales
     */
    public double calcularTotalServicios() {
        double total = 0;
        for (DetalleServicio detalle : servicios) {
            total += detalle.calcularSubtotal();
        }
        return total;
    }

    /**
     * Metodo que permite calcular el valor correspondiente a las sesiones del entrenador asignado.
     * @return valor de las sesiones del entrenador
     */
    public double calcularValorEntrenador() {
        double valor = 0;
        if (asignacionEntrenador != null) {
            valor = asignacionEntrenador.calcularCostoSesiones();
        }
        return valor;
    }

    /**
     * Metodo que permite calcular el valor total de la inscripción teniendo en cuenta
     * el plan adquirido, los servicios adicionales, el entrenador
     * asignado y el porcentaje de descuento.
     * @return valor final de la inscripción
     */
    public double calcularValorFinal() {
        double subtotal = plan.calcularValorPlan() + calcularTotalServicios() + calcularValorEntrenador();
        double descuento = subtotal * porcentajeDescuento / 100;
        double valorFinal = subtotal - descuento;
        return valorFinal;
    }

    /**
     * Metodo que permite agregar un servicio adicional a la inscripción.
     * @param servicio servicio adicional
     * @param cantidad cantidad utilizada
     * @return mensaje indicando el resultado de la operación
     */
    public String agregarServicio(ServicioAdicional servicio, int cantidad) {
        String mensaje = "";
        if (!servicio.isDisponible()) {
            mensaje = "El servicio no se encuentra disponible.";
        } else if (cantidad <= 0) {
            mensaje = "La cantidad debe ser mayor que cero.";
        } else {
            DetalleServicio detalle = new DetalleServicio(servicio, cantidad);
            servicios.add(detalle);
            mensaje = "Servicio agregado correctamente.";
        }
        return mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PlanEntrenamiento getPlan() {
        return plan;
    }

    public void setPlan(PlanEntrenamiento plan) {
        this.plan = plan;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public AsignacionEntrenador getAsignacionEntrenador() {
        return asignacionEntrenador;
    }

    public void setAsignacionEntrenador(
            AsignacionEntrenador asignacionEntrenador) {

        this.asignacionEntrenador = asignacionEntrenador;
    }

    public ArrayList<DetalleServicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<DetalleServicio> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "codigo='" + codigo + '\'' +
                ", fechaInscripcion=" + fechaInscripcion +
                ", cliente=" + cliente +
                ", plan=" + plan +
                ", porcentajeDescuento=" + porcentajeDescuento +
                ", asignacionEntrenador=" + asignacionEntrenador +
                ", servicios=" + servicios +
                '}';
    }

    /**
     * Clase Builder encargada de construir objetos de tipo Inscripcion
     * de manera flexible.
     */
    public static class Builder {

        private String codigo;
        private LocalDate fechaInscripcion;
        private Cliente cliente;
        private PlanEntrenamiento plan;
        private double porcentajeDescuento;
        private AsignacionEntrenador asignacionEntrenador;

        private ArrayList<DetalleServicio> servicios =
                new ArrayList<>();

        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder fechaInscripcion(LocalDate fechaInscripcion) {
            this.fechaInscripcion = fechaInscripcion;
            return this;
        }

        public Builder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder plan(PlanEntrenamiento plan) {
            this.plan = plan;
            return this;
        }

        public Builder porcentajeDescuento(double porcentajeDescuento) {
            this.porcentajeDescuento = porcentajeDescuento;
            return this;
        }

        public Builder asignacionEntrenador(AsignacionEntrenador asignacionEntrenador) {
            this.asignacionEntrenador = asignacionEntrenador;
            return this;
        }

        /**
         * Metodo que permite agregar un servicio adicional durante la construcción
         * de la inscripción.
         * @param servicio servicio adicional
         * @param cantidad cantidad solicitada
         * @return instancia actual del Builder
         */
        public Builder servicio(ServicioAdicional servicio, int cantidad) {
            this.servicios.add(new DetalleServicio(servicio, cantidad));
            return this;
        }

        /**
         * Metodo que permite construir una nueva inscripción.
         * @return inscripción construida
         */
        public Inscripcion build() {
            return new Inscripcion(this);
        }
    }
}