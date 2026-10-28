package co.edu.uniquindio.poo.piitechsolutions.model;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoComplemento;

import java.util.ArrayList;
import java.util.List;

public final class ServicioPersonalizado implements ServicioPersonalizable {

    private String codigo;
    private ServicioPersonalizable servicioDecorado;

    /**
     * Construye un servicio tecnológico personalizado.
     * Verifica que exista un código y que el servicio
     * recibido tenga al menos un complemento.
     * @param codigo Código identificador de la personalización.
     * @param servicioDecorado Servicio con los complementos aplicados.
     * @throws IllegalArgumentException Si el código es inválido,
     * el servicio es nulo o no contiene complementos.
     */
    public ServicioPersonalizado(String codigo, ServicioPersonalizable servicioDecorado) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código de la personalización es obligatorio");
        }
        if (servicioDecorado == null) {
            throw new IllegalArgumentException("El servicio decorado es obligatorio");
        }
        if (servicioDecorado.getComplementos().isEmpty()) {
            throw new IllegalArgumentException("Debe incorporar al menos un complemento");
        }
        this.codigo = codigo;
        this.servicioDecorado = servicioDecorado;
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getNombre() {
        return servicioDecorado.getNombre();
    }

    @Override
    public String getDescripcion() {
        return servicioDecorado.getDescripcion();
    }

    @Override
    public double getCostoBase() {
        return servicioDecorado.getCostoBase();
    }

    /**
     * Calcula el costo total del servicio personalizado.
     * Delega el cálculo a la cadena de decoradores,
     * que incorpora los incrementos correspondientes.
     * @return Costo total del servicio personalizado.
     */
    @Override
    public double calcularCosto() {
        return servicioDecorado.calcularCosto();
    }

    /**
     * Calcula el costo acumulado de los complementos.
     * Obtiene la diferencia entre el costo total
     * personalizado y el costo base original.
     * @return Costo total de los complementos aplicados.
     */
    public double calcularIncrementos() {
        return calcularCosto() - getCostoBase();
    }

    /**
     * Obtiene la especialidad técnica asociada
     * al servicio tecnológico original.
     * @return Especialidad técnica de referencia.
     */
    @Override
    public EspecialidadTecnica getEspecialidadReferencia() {
        return servicioDecorado.getEspecialidadReferencia();
    }

    /**
     * Obtiene los complementos aplicados al servicio.
     * Devuelve una copia de la lista para evitar
     * modificaciones externas.
     * @return Lista de complementos incorporados.
     */
    @Override
    public List<TipoComplemento> getComplementos() {
        return new ArrayList<>(servicioDecorado.getComplementos());
    }

    /**
     * Obtiene las especialidades que requieren
     * atención presencial.
     * Incluye los requerimientos del servicio original
     * y los incorporados mediante Decorator.
     * @return Lista de especialidades presenciales.
     */
    @Override
    public List<EspecialidadTecnica>
    obtenerEspecialidadesPresenciales() {
        return new ArrayList<>(servicioDecorado.obtenerEspecialidadesPresenciales());
    }

    /**
     * Obtiene el servicio decorado que representa
     * la configuración personalizada.
     * @return Servicio con sus complementos aplicados.
     */
    public ServicioPersonalizable getServicioDecorado() {
        return servicioDecorado;
    }

    /**
     * Devuelve una representación textual del servicio.
     * @return Código, nombre y costo total.
     */
    @Override
    public String toString() {
        return codigo + " | " + getNombre() + " | $" + calcularCosto();
    }
}
