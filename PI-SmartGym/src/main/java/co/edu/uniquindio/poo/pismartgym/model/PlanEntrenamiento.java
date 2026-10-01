package co.edu.uniquindio.poo.pismartgym.model;

import java.util.ArrayList;

public abstract class PlanEntrenamiento {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private EstadoPlan estado;
    private ArrayList<String> beneficios;

    /**
     * Metodo que permite construir un plan de entrenamiento.
     * @param codigo código único
     * @param nombre nombre del plan
     * @param descripcion descripción
     * @param duracionMeses duración contratada
     * @param valorMensual valor mensual
     * @param estado estado del plan
     */
    public PlanEntrenamiento(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
        this.beneficios = new ArrayList<>();
    }

    /**
     * Metodo que permite calcular el valor correspondiente al plan.
     * @return valor del plan
     */
    public double calcularValorPlan() {
        return valorMensual * duracionMeses;
    }

    /**
     * Metodo que permite agregar un beneficio al plan.
     * @param beneficio beneficio que será agregado
     */
    public void agregarBeneficio(String beneficio) {
        beneficios.add(beneficio);
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

    public void setEstado(EstadoPlan estado) {
        this.estado = estado;
    }

    public ArrayList<String> getBeneficios() {
        return beneficios;
    }

    @Override
    public String toString() {
        return "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", duracionMeses=" + duracionMeses +
                ", valorMensual=" + valorMensual +
                ", estado=" + estado;
    }
}