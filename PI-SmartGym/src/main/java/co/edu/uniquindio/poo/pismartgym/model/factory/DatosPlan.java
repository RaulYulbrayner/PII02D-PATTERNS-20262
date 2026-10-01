package co.edu.uniquindio.poo.pismartgym.model.factory;

import co.edu.uniquindio.poo.pismartgym.model.Especialidad;
import co.edu.uniquindio.poo.pismartgym.model.EstadoPlan;

/**
 * Representa los datos necesarios para crear un plan de entrenamiento.
 * Esta clase permite centralizar la información utilizada por los
 * diferentes creadores de planes, evitando que cada Factory Method
 * tenga una firma diferente.
 */
public class DatosPlan {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private EstadoPlan estado;

    private int cantidadSesiones;
    private Especialidad especialidadRequerida;
    private String objetivosCliente;

    /**
     * Construye los datos básicos necesarios para crear un plan.
     * @param codigo código del plan
     * @param nombre nombre del plan
     * @param descripcion descripción del plan
     * @param duracionMeses duración del plan en meses
     * @param valorMensual valor mensual del plan
     * @param estado estado del plan
     */
    public DatosPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
    }

    /**
     * Construye los datos necesarios para crear un plan personalizado.
     * @param codigo código del plan
     * @param nombre nombre del plan
     * @param descripcion descripción
     * @param duracionMeses duración en meses
     * @param valorMensual valor mensual
     * @param estado estado
     * @param cantidadSesiones cantidad de sesiones
     * @param especialidadRequerida especialidad requerida
     * @param objetivosCliente objetivos del cliente
     */
    public DatosPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado, int cantidadSesiones, Especialidad especialidadRequerida, String objetivosCliente) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
        this.cantidadSesiones = cantidadSesiones;
        this.especialidadRequerida = especialidadRequerida;
        this.objetivosCliente = objetivosCliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public EstadoPlan getEstado() {
        return estado;
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public Especialidad getEspecialidadRequerida() {
        return especialidadRequerida;
    }

    public String getObjetivosCliente() {
        return objetivosCliente;
    }
}
