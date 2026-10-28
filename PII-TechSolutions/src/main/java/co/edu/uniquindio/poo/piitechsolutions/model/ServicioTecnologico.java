package co.edu.uniquindio.poo.piitechsolutions.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ServicioTecnologico implements ServicioPersonalizable {

    private final String codigo;
    private final String nombre;
    private final String descripcion;
    private final double costoBase;
    private final EspecialidadTecnica especialidad;
    private final boolean presencial;

    /**
     * Construye un servicio tecnológico.
     * @param codigo Código identificador del servicio.
     * @param nombre Nombre del servicio.
     * @param descripcion Descripción del servicio.
     * @param costoBase Costo base en pesos colombianos.
     * @param especialidad Especialidad técnica asociada.
     * @param presencial Indica si requiere atención presencial.
     * @throws IllegalArgumentException Si los datos
     * obligatorios son inválidos.
     */
    protected ServicioTecnologico(String codigo, String nombre, String descripcion, double costoBase, EspecialidadTecnica especialidad, boolean presencial) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código del servicio es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del servicio es obligatorio");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción del servicio es obligatoria");
        }
        if (!Double.isFinite(costoBase) || costoBase < 0) {
            throw new IllegalArgumentException("El costo base debe ser un valor válido " + "mayor o igual a cero");
        }
        if (especialidad == null) {
            throw new IllegalArgumentException("La especialidad técnica es obligatoria");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoBase = costoBase;
        this.especialidad = especialidad;
        this.presencial = presencial;
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public double getCostoBase() {
        return costoBase;
    }

    @Override
    public double calcularCosto() {
        return costoBase;
    }

    @Override
    public EspecialidadTecnica getEspecialidadReferencia() {
        return especialidad;
    }

    @Override
    public List<TipoComplemento> getComplementos() {
        return new ArrayList<>();
    }

    @Override
    public List<EspecialidadTecnica> obtenerEspecialidadesPresenciales() {
        List<EspecialidadTecnica> especialidades =
                new ArrayList<>();
        if (presencial) {
            especialidades.add(especialidad);
        }
        return especialidades;
    }

    public boolean isPresencial() {
        return presencial;
    }

    public abstract TipoServicio getTipo();

    @Override
    public String toString() {
        return codigo + " | " + nombre + " | $" + calcularCosto();
    }
}
