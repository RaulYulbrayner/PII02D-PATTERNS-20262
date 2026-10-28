package co.edu.uniquindio.poo.piitechsolutions.model;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.EstadoSolicitud;
import co.edu.uniquindio.poo.piitechsolutions.model.PrioridadSolicitud;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class SolicitudServicio {

    private final String codigo;
    private final LocalDate fechaCreacion;
    private final Cliente cliente;
    private final List<ElementoContratable> elementos;
    private final LocalDate fechaProgramada;
    private final String observaciones;
    private final PrioridadSolicitud prioridad;
    private final double subtotalContratado;

    private EstadoSolicitud estado;
    private double costoDesplazamiento;

    /**
     * Construye una solicitud utilizando Builder.
     * El constructor es privado para impedir
     * la creación directa de solicitudes incompletas.
     * @param builder Objeto encargado de construir
     * y validar la solicitud.
     */
    private SolicitudServicio(Builder builder) {
        this.codigo = builder.codigo;
        this.fechaCreacion = builder.fechaCreacion;
        this.cliente = builder.cliente;
        this.elementos = new ArrayList<>(builder.elementos);
        this.fechaProgramada = builder.fechaProgramada;
        this.observaciones = builder.observaciones;
        this.prioridad = builder.prioridad;
        this.subtotalContratado = calcularSubtotalInicial();
        this.estado = EstadoSolicitud.REGISTRADA;
        this.costoDesplazamiento = 0;
    }

    /**
     * Calcula el costo de los elementos en el momento
     * de construir la solicitud.
     * El valor se conserva posteriormente mediante
     * el atributo subtotalContratado.
     * @return Subtotal inicial de la contratación.
     */
    private double calcularSubtotalInicial() {
        double subtotal = 0;
        for (ElementoContratable elemento : elementos) {
            subtotal += elemento.calcularCosto();
        }
        if (!Double.isFinite(subtotal)) {
            throw new IllegalArgumentException("El subtotal de la solicitud no es válido");
        }
        return subtotal;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ElementoContratable> getElementos() {
        return new ArrayList<>(elementos);
    }

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public PrioridadSolicitud getPrioridad() {
        return prioridad;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public double getCostoDesplazamiento() {
        return costoDesplazamiento;
    }

    /**
     * Consulta el subtotal contratado.
     * Este valor se fija al construir la solicitud
     * y no se recalcula al modificar posteriormente
     * alguno de los paquetes originales.
     * @return Subtotal de los elementos contratados.
     */
    public double calcularSubtotal() {
        return subtotalContratado;
    }

    /**
     * Calcula el costo total de la solicitud.
     * Suma el subtotal contratado y el costo
     * de desplazamiento asignado al confirmar.
     * @return Costo total de la solicitud.
     */
    public double calcularCostoTotal() {
        return subtotalContratado + costoDesplazamiento;
    }

    /**
     * Obtiene las especialidades técnicas necesarias
     * para prestar atención presencial.
     * Recorre los elementos contratados y evita
     * registrar especialidades duplicadas.
     * @return Lista de especialidades sin duplicados.
     */
    public List<EspecialidadTecnica> obtenerEspecialidadesPresenciales() {
        List<EspecialidadTecnica> especialidades = new ArrayList<>();
        for (ElementoContratable elemento : elementos) {
            List<EspecialidadTecnica> requeridas = elemento.obtenerEspecialidadesPresenciales();
            for (EspecialidadTecnica especialidad : requeridas) {
                if (!especialidades.contains(especialidad)) {
                    especialidades.add(especialidad);
                }
            }
        }
        return especialidades;
    }

    /**
     * Determina si la solicitud requiere
     * atención presencial.
     * @return true si necesita al menos
     * una especialidad presencial.
     */
    public boolean requiereAtencionPresencial() {
        boolean requiere = false;
        List<EspecialidadTecnica> especialidades =
                obtenerEspecialidadesPresenciales();

        if (!especialidades.isEmpty()) {
            requiere = true;
        }
        return requiere;
    }

    /**
     * Confirma una solicitud utilizando un costo
     * de desplazamiento previamente validado.
     * Esta operación debe ser invocada únicamente
     * por el proceso de confirmación del sistema,
     * después de consultar la disponibilidad.
     *
     * @param costoDesplazamiento Costo determinado
     * por la política de desplazamiento.
     * @throws IllegalArgumentException Si el costo
     * no es válido.
     * @throws IllegalStateException Si la solicitud
     * ya se encuentra confirmada.
     */
    public void confirmar(double costoDesplazamiento) {
        if (estado == EstadoSolicitud.CONFIRMADA) {
            throw new IllegalStateException("La solicitud ya se encuentra confirmada");
        }
        if (!Double.isFinite(costoDesplazamiento) || costoDesplazamiento < 0) {
            throw new IllegalArgumentException("El desplazamiento no es válido");
        }
        double total = subtotalContratado + costoDesplazamiento;
        if (!Double.isFinite(total)) {
            throw new IllegalArgumentException("El costo total no es válido");
        }
        this.costoDesplazamiento = costoDesplazamiento;
        this.estado = EstadoSolicitud.CONFIRMADA;
    }

    /**
     * Devuelve una representación textual
     * de la solicitud.
     * @return Código, cliente, estado y costo total.
     */
    @Override
    public String toString() {
        return codigo
                + " | " + cliente.getNombreCompleto()
                + " | " + estado
                + " | $" + calcularCostoTotal();
    }

    /**
     * Constructor flexible de solicitudes.
     * Implementa el patrón creacional Builder.
     * Los datos obligatorios se reciben mediante
     * el constructor, mientras que los datos
     * opcionales se establecen con métodos
     * que devuelven el propio Builder.
     */
    public static class Builder {

        private final String codigo;
        private final LocalDate fechaCreacion;
        private final Cliente cliente;
        private final List<ElementoContratable> elementos;
        private LocalDate fechaProgramada;
        private String observaciones;
        private PrioridadSolicitud prioridad;

        /**
         * Construye un Builder con los datos obligatorios.
         * @param codigo Código de la solicitud.
         * @param fechaCreacion Fecha de creación.
         * @param cliente Cliente registrado.
         * @throws IllegalArgumentException Si alguno
         * de los datos obligatorios es inválido.
         */
        public Builder(String codigo, LocalDate fechaCreacion, Cliente cliente) {
            if (codigo == null || codigo.isBlank()) {
                throw new IllegalArgumentException("El código de la solicitud es obligatorio");
            }
            if (fechaCreacion == null) {
                throw new IllegalArgumentException("La fecha de creación es obligatoria");
            }
            if (cliente == null) {
                throw new IllegalArgumentException("El cliente es obligatorio");
            }
            this.codigo = codigo;
            this.fechaCreacion = fechaCreacion;
            this.cliente = cliente;
            this.elementos = new ArrayList<>();
        }

        /**
         * Agrega un elemento contratable a la solicitud.
         * No permite agregar dos veces la misma instancia.
         * @param elemento Elemento que se contratará.
         * @return Builder actualizado.
         * @throws IllegalArgumentException Si el elemento
         * es nulo o ya se encuentra agregado.
         */
        public Builder agregarElemento(ElementoContratable elemento) {
            if (elemento == null) {
                throw new IllegalArgumentException("El elemento no puede ser nulo");
            }
            for (ElementoContratable actual : elementos) {
                if (actual == elemento) {
                    throw new IllegalArgumentException("El elemento ya fue agregado");
                }
            }
            elementos.add(elemento);
            return this;
        }

        /**
         * Establece la fecha programada de atención.
         * @param fechaProgramada Fecha de atención.
         * @return Builder actualizado.
         * @throws IllegalArgumentException Si la fecha
         * es anterior a la fecha de creación.
         */
        public Builder conFechaProgramada(LocalDate fechaProgramada) {
            if (fechaProgramada != null && fechaProgramada.isBefore(fechaCreacion)) {
                throw new IllegalArgumentException("La fecha programada no puede " + "ser anterior a la creación");
            }
            this.fechaProgramada = fechaProgramada;
            return this;
        }

        /**
         * Establece las observaciones de la solicitud.
         * @param observaciones Texto opcional.
         * @return Builder actualizado.
         */
        public Builder conObservaciones(String observaciones) {
            if (observaciones == null) {this.observaciones = null;
            } else {
                this.observaciones = observaciones.trim();
            }
            return this;
        }

        /**
         * Establece la prioridad de la solicitud.
         * @param prioridad Prioridad opcional.
         * @return Builder actualizado.
         */
        public Builder conPrioridad(PrioridadSolicitud prioridad) {
            this.prioridad = prioridad;
            return this;
        }

        /**
         * Construye una solicitud válida.
         * Verifica que exista al menos un
         * elemento contratable.
         * @return Solicitud inicialmente registrada.
         * @throws IllegalStateException Si no se
         * agregaron elementos contratables.
         */
        public SolicitudServicio build() {
            if (elementos.isEmpty()) {
                throw new IllegalStateException("La solicitud debe contener " + "al menos un elemento");
            }
            return new SolicitudServicio(this);
        }
    }
}