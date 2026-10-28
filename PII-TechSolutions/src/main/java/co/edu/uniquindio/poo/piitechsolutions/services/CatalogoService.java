package co.edu.uniquindio.poo.piitechsolutions.services;

import co.edu.uniquindio.poo.piitechsolutions.model.*;
import co.edu.uniquindio.poo.piitechsolutions.model.decorator.AtencionDomicilio;
import co.edu.uniquindio.poo.piitechsolutions.model.decorator.AtencionPrioritaria;
import co.edu.uniquindio.poo.piitechsolutions.model.decorator.SoporteTecnico30Dias;
import co.edu.uniquindio.poo.piitechsolutions.model.factory.RegistroFabricas;

import java.util.ArrayList;
import java.util.List;


public class CatalogoService {

    private final RegistroFabricas registroFabricas;

    private final List<ServicioTecnologico> servicios;
    private final List<ServicioPersonalizado> personalizados;
    private final List<ConfiguracionComplemento> configuraciones;

    /**
     * Construye el servicio de catálogo.
     * @param registroFabricas Registro de fábricas
     * utilizadas para construir servicios originales.
     */
    public CatalogoService(RegistroFabricas registroFabricas) {
        if (registroFabricas == null) {
            throw new IllegalArgumentException("El registro de fábricas es obligatorio");
        }
        this.registroFabricas = registroFabricas;
        this.servicios = new ArrayList<>();
        this.personalizados = new ArrayList<>();
        this.configuraciones = new ArrayList<>();
    }

    /**
     * Registra un servicio tecnológico original.
     * @param servicio Servicio que se desea registrar.
     */
    public void registrarServicio(ServicioTecnologico servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio es obligatorio");
        }
        if (existeCodigo(servicio.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un elemento con el código: " + servicio.getCodigo());
        }
        servicios.add(servicio);
    }

    /**
     * Crea un servicio original utilizando Factory Method
     * y lo incorpora al catálogo.
     * @return Servicio tecnológico registrado.
     */
    public ServicioTecnologico crearYRegistrarServicio(TipoServicio tipo, String codigo, String nombre, String descripcion, double costoBase, EspecialidadTecnica especialidad, boolean presencial) {
        ServicioTecnologico servicio = registroFabricas.crearServicio(tipo, codigo, nombre, descripcion, costoBase, especialidad, presencial);
        registrarServicio(servicio);
        return servicio;
    }

    /**
     * Busca un servicio original mediante su código.
     * @param codigo Código solicitado.
     * @return Servicio encontrado o null.
     */
    public ServicioTecnologico buscarServicio(String codigo) {
        ServicioTecnologico encontrado = null;
        if (codigo != null) {
            for (ServicioTecnologico servicio : servicios) {
                if (servicio.getCodigo().equals(codigo.trim())) {
                    encontrado = servicio;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * Busca un servicio personalizado mediante su código.
     * @param codigo Código solicitado.
     * @return Servicio encontrado o null.
     */
    public ServicioPersonalizado buscarServicioPersonalizado(String codigo) {
        ServicioPersonalizado encontrado = null;
        if (codigo != null) {
            for (ServicioPersonalizado servicio : personalizados) {
                if (servicio.getCodigo().equals(codigo.trim())) {
                    encontrado = servicio;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * Consulta un elemento del catálogo, original
     * o personalizado, mediante su código.
     * @param codigo Código del elemento.
     * @return Elemento encontrado o null.
     */
    public ElementoContratable buscarElemento(String codigo) {
        ElementoContratable encontrado = buscarServicio(codigo);
        if (encontrado == null) {
            encontrado = buscarServicioPersonalizado(codigo);
        }
        return encontrado;
    }

    /**
     * Verifica si un código ya pertenece a un elemento
     * del catálogo.
     * @param codigo Código que se desea verificar.
     * @return true cuando el código ya existe.
     */
    public boolean existeCodigo(String codigo) {
        boolean existe = false;
        if (buscarElemento(codigo) != null) {
            existe = true;
        }
        return existe;
    }

    /**
     * Obtiene los servicios tecnológicos originales.
     * @return Copia de la lista de servicios.
     */
    public List<ServicioTecnologico> listarServicios() {
        return new ArrayList<>(servicios);
    }

    /**
     * Obtiene los servicios personalizados registrados.
     * @return Copia de la lista de personalizaciones.
     */
    public List<ServicioPersonalizado> listarPersonalizados() {
        return new ArrayList<>(personalizados);
    }

    /**
     * Obtiene todos los elementos del catálogo,
     * incluyendo originales y personalizados.
     * @return Lista de elementos contratables.
     */
    public List<ElementoContratable> listarElementos() {
        List<ElementoContratable> elementos = new ArrayList<>();
        for (ServicioTecnologico servicio : servicios) {
            elementos.add(servicio);
        }
        for (ServicioPersonalizado servicio : personalizados) {
            elementos.add(servicio);
        }
        return elementos;
    }

    /**
     * Registra el costo y el creador de un complemento.
     * Si el complemento ya existe, reemplaza su
     * configuración anterior.
     * @param tipo Tipo de complemento.
     * @param costo Costo adicional.
     * @param creador Objeto encargado de construir
     * el decorador correspondiente.
     */
    public void registrarComplemento(TipoComplemento tipo, double costo, CreadorComplemento creador) {
        if (tipo == null || creador == null) {
            throw new IllegalArgumentException("El tipo y el creador son obligatorios");
        }
        if (!Double.isFinite(costo) || costo < 0) {
            throw new IllegalArgumentException("El costo del complemento no es válido");
        }
        ConfiguracionComplemento configuracion = new ConfiguracionComplemento(tipo, costo, creador);
        int posicion = buscarPosicionComplemento(tipo);
        if (posicion >= 0) {
            configuraciones.set(posicion, configuracion);
        } else {
            configuraciones.add(configuracion);
        }
    }

    /**
     * Busca la posición de un complemento registrado.
     * @param tipo Complemento que se desea localizar.
     * @return Posición del complemento o -1.
     */
    private int buscarPosicionComplemento(TipoComplemento tipo) {
        int posicion = -1;
        for (int i = 0; i < configuraciones.size(); i++) {
            if (configuraciones.get(i).getTipo() == tipo) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    /**
     * Consulta el costo de un complemento registrado.
     * @param tipo Tipo de complemento.
     * @return Costo adicional.
     */
    public double consultarCostoComplemento(TipoComplemento tipo) {
        int posicion = buscarPosicionComplemento(tipo);
        if (posicion == -1) {
            throw new IllegalArgumentException("El complemento no está configurado: " + tipo);
        }
        return configuraciones.get(posicion).getCosto();
    }

    /**
     * Obtiene los tipos de complementos configurados.
     * @return Lista de tipos de complementos.
     */
    public List<TipoComplemento> listarComplementos() {
        List<TipoComplemento> tipos = new ArrayList<>();
        for (ConfiguracionComplemento configuracion : configuraciones) {
            tipos.add(configuracion.getTipo());
        }
        return tipos;
    }

    /**
     * Agrega un complemento a un servicio mediante
     * el creador configurado.
     * @param servicio Servicio original o decorado.
     * @param tipo Complemento que se desea incorporar.
     * @return Servicio con el nuevo decorador.
     */
    public ServicioPersonalizable aplicarComplemento(ServicioPersonalizable servicio, TipoComplemento tipo) {
        if (servicio == null || tipo == null) {
            throw new IllegalArgumentException("El servicio y el complemento son obligatorios");
        }
        int posicion = buscarPosicionComplemento(tipo);
        if (posicion == -1) {
            throw new IllegalArgumentException("El complemento no está configurado: " + tipo);
        }
        ConfiguracionComplemento configuracion = configuraciones.get(posicion);
        return configuracion.getCreador().crear(servicio, configuracion.getCosto());
    }

    /**
     * Personaliza un servicio original agregando
     * los complementos seleccionados.
     * La nueva personalización recibe un código
     * diferente al servicio original.
     * @param codigoOriginal Código del servicio original.
     * @param nuevoCodigo Código de la personalización.
     * @param complementos Lista de complementos solicitados.
     * @return Servicio personalizado registrado.
     */
    public ServicioPersonalizado personalizarServicio(String codigoOriginal, String nuevoCodigo, List<TipoComplemento> complementos) {
        if (nuevoCodigo == null || nuevoCodigo.isBlank()) {
            throw new IllegalArgumentException("El código de la personalización es obligatorio");
        }
        if (existeCodigo(nuevoCodigo)) {
            throw new IllegalArgumentException("El código ya está registrado: " + nuevoCodigo);
        }
        if (complementos == null || complementos.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar al menos un complemento");
        }
        ServicioTecnologico original = buscarServicio(codigoOriginal);
        if (original == null) {
            throw new IllegalArgumentException("No existe el servicio original: " + codigoOriginal);
        }
        ServicioPersonalizable decorado = original;
        for (TipoComplemento tipo : complementos) {
            decorado = aplicarComplemento(decorado, tipo);
        }
        ServicioPersonalizado personalizado = new ServicioPersonalizado(nuevoCodigo, decorado);
        personalizados.add(personalizado);
        return personalizado;
    }

    /**
     * Define el contrato para construir un decorador.
     * Sustituye el uso de BiFunction por una
     * interfaz propia del proyecto.
     */
    public interface CreadorComplemento {

        /**
         * Construye un complemento sobre un servicio.
         * @param servicio Servicio que será decorado.
         * @param incremento Costo adicional.
         * @return Servicio con el complemento aplicado.
         */
        ServicioPersonalizable crear(ServicioPersonalizable servicio, double incremento);
    }

    /**
     * Creador concreto de atención prioritaria.
     */
    public static class CreadorAtencionPrioritaria implements CreadorComplemento {

        /** {@inheritDoc} */
        @Override
        public ServicioPersonalizable crear(ServicioPersonalizable servicio, double incremento) {
            return new AtencionPrioritaria(servicio, incremento);
        }
    }

    /**
     * Creador concreto de soporte técnico por 30 días.
     */
    public static class CreadorSoporteTecnico30Dias implements CreadorComplemento {

        /** {@inheritDoc} */
        @Override
        public ServicioPersonalizable crear(ServicioPersonalizable servicio, double incremento) {
            return new SoporteTecnico30Dias(servicio, incremento);
        }
    }

    /**
     * Creador concreto de atención a domicilio.
     */
    public static class CreadorAtencionDomicilio implements CreadorComplemento {

        /** {@inheritDoc} */
        @Override
        public ServicioPersonalizable crear(ServicioPersonalizable servicio, double incremento) {
            return new AtencionDomicilio(servicio, incremento);
        }
    }

    /**
     * Agrupa la configuración comercial
     * de un complemento y su creador.
     * Es una clase interna privada utilizada
     * exclusivamente por CatalogoService.
     */
    private static class ConfiguracionComplemento {

        private final TipoComplemento tipo;
        private final double costo;
        private final CreadorComplemento creador;

        /**
         * Construye la configuración de un complemento.
         * @param tipo Tipo de complemento.
         * @param costo Costo adicional.
         * @param creador Creador correspondiente.
         */
        private ConfiguracionComplemento(TipoComplemento tipo, double costo, CreadorComplemento creador) {
            this.tipo = tipo;
            this.costo = costo;
            this.creador = creador;
        }

        /**
         * Obtiene el tipo configurado.
         * @return Tipo de complemento.
         */
        private TipoComplemento getTipo() {
            return tipo;
        }

        /**
         * Obtiene el costo adicional configurado.
         * @return Costo del complemento.
         */
        private double getCosto() {
            return costo;
        }

        /**
         * Obtiene el creador del complemento.
         * @return Creador registrado.
         */
        private CreadorComplemento getCreador() {
            return creador;
        }
    }
}
