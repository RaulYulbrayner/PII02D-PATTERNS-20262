package co.edu.uniquindio.poo.piitechsolutions.model.decorator;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.ServicioPersonalizable;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoComplemento;

import java.util.ArrayList;
import java.util.List;

public abstract class DecoradorServicio implements ServicioPersonalizable {

    private final ServicioPersonalizable servicio;
    private final TipoComplemento tipoComplemento;
    private final double incremento;

    /**
     * Construye un decorador para un servicio tecnológico.
     * Verifica que el servicio exista, que el costo
     * adicional sea válido y que el complemento
     * no haya sido aplicado previamente.
     * @param servicio Servicio que se desea personalizar.
     * @param tipoComplemento Tipo de complemento aplicado.
     * @param incremento Costo adicional del complemento.
     * @throws IllegalArgumentException Si los datos son
     * inválidos o el complemento ya fue aplicado.
     */
    protected DecoradorServicio(ServicioPersonalizable servicio, TipoComplemento tipoComplemento, double incremento) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo");
        }
        if (tipoComplemento == null) {
            throw new IllegalArgumentException("El tipo de complemento es obligatorio");
        }
        if (!Double.isFinite(incremento) || incremento < 0) {
            throw new IllegalArgumentException("El costo adicional debe ser válido " + "y mayor o igual a cero");
        }
        if (contieneComplemento(servicio.getComplementos(), tipoComplemento)) {
            throw new IllegalArgumentException("El complemento ya fue aplicado al servicio");
        }
        this.servicio = servicio;
        this.tipoComplemento = tipoComplemento;
        this.incremento = incremento;
    }

    /**
     * Verifica si un complemento está presente
     * en una lista de complementos.
     * Utiliza un ciclo tradicional para localizarlo.
     * @param complementos Lista de complementos.
     * @param buscado Complemento que se desea localizar.
     * @return true si el complemento ya existe.
     */
    private boolean contieneComplemento(List<TipoComplemento> complementos, TipoComplemento buscado) {
        boolean encontrado = false;
        for (TipoComplemento complemento : complementos) {
            if (complemento == buscado) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    /**
     * Obtiene el servicio que está siendo decorado.
     * @return Servicio original u otro decorador.
     */
    protected ServicioPersonalizable getServicio() {
        return servicio;
    }

    /**
     * Obtiene el costo adicional del complemento.
     * @return Incremento en pesos colombianos.
     */
    public double getIncremento() {
        return incremento;
    }

    /**
     * Obtiene el tipo de complemento aplicado.
     * @return Tipo de complemento.
     */
    public TipoComplemento getTipoComplemento() {
        return tipoComplemento;
    }

    /**
     * Obtiene el código del servicio decorado.
     * Los decoradores conservan el código del
     * servicio original.
     * @return Código del servicio.
     */
    @Override
    public String getCodigo() {
        return servicio.getCodigo();
    }

    /**
     * Obtiene el nombre del servicio decorado.
     * Incorpora el complemento aplicado
     * a la descripción del nombre.
     * @return Nombre del servicio personalizado.
     */
    @Override
    public String getNombre() {
        return servicio.getNombre() + " + " + tipoComplemento.name();
    }

    /**
     * Obtiene la descripción del servicio decorado.
     * @return Descripción original y complemento aplicado.
     */
    @Override
    public String getDescripcion() {
        return servicio.getDescripcion() + " | " + tipoComplemento.name();
    }

    /**
     * Obtiene el costo base del servicio original.
     * No incluye los complementos incorporados.
     * @return Costo base del servicio.
     */
    @Override
    public double getCostoBase() {
        return servicio.getCostoBase();
    }

    /**
     * Calcula el costo del servicio personalizado.
     * Suma el costo calculado por el servicio
     * decorado y el incremento correspondiente
     * al complemento actual.
     * Cuando existen varios decoradores,
     * el cálculo se realiza recursivamente.
     * @return Costo total del servicio personalizado.
     */
    @Override
    public double calcularCosto() {
        return servicio.calcularCosto() + incremento;
    }

    /**
     * Obtiene la especialidad técnica del
     * servicio tecnológico original.
     * @return Especialidad técnica del servicio.
     */
    @Override
    public EspecialidadTecnica getEspecialidadReferencia() {
        return servicio.getEspecialidadReferencia();
    }

    /**
     * Obtiene todos los complementos incorporados
     * al servicio tecnológico.
     * Construye una lista con los complementos
     * previamente aplicados y posteriormente agrega
     * el complemento correspondiente al decorador actual.
     * Devuelve una lista independiente para proteger
     * la estructura interna de los decoradores.
     * @return Lista de complementos aplicados.
     */
    @Override
    public List<TipoComplemento> getComplementos() {
        List<TipoComplemento> complementos = new ArrayList<>();
        List<TipoComplemento> anteriores = servicio.getComplementos();
        for (TipoComplemento complemento : anteriores) {
            if (!contieneComplemento(complementos, complemento)) {
                complementos.add(complemento);
            }
        }
        complementos.add(tipoComplemento);
        return complementos;
    }

    /**
     * Obtiene las especialidades técnicas
     * que requieren atención presencial.
     * De manera predeterminada delega esta
     * responsabilidad al servicio decorado.
     * Los complementos que incorporen atención
     * presencial podrán sobrescribir este método.
     * @return Lista de especialidades requeridas.
     */
    @Override
    public List<EspecialidadTecnica> obtenerEspecialidadesPresenciales() {
        return new ArrayList<>(servicio.obtenerEspecialidadesPresenciales());
    }

    /**
     * Devuelve una representación textual
     * del servicio personalizado.
     * @return Nombre y costo total.
     */
    @Override
    public String toString() {
        return getNombre() + " | $" + calcularCosto();
    }
}
