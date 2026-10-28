package co.edu.uniquindio.poo.piitechsolutions.controller;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.PrioridadSolicitud;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoComplemento;

import co.edu.uniquindio.poo.piitechsolutions.model.Cliente;
import co.edu.uniquindio.poo.piitechsolutions.model.ElementoContratable;
import co.edu.uniquindio.poo.piitechsolutions.model.PaqueteTecnologico;
import co.edu.uniquindio.poo.piitechsolutions.model.ServicioPersonalizado;
import co.edu.uniquindio.poo.piitechsolutions.model.ServicioTecnologico;
import co.edu.uniquindio.poo.piitechsolutions.model.SolicitudServicio;

import co.edu.uniquindio.poo.piitechsolutions.model.adapter.InformacionTecnico;
import co.edu.uniquindio.poo.piitechsolutions.model.adapter.LegacySupport;
import co.edu.uniquindio.poo.piitechsolutions.model.adapter.LegacySupportAdapter;
import co.edu.uniquindio.poo.piitechsolutions.model.adapter.ProveedorTecnicos;
import co.edu.uniquindio.poo.piitechsolutions.model.factory.RegistroFabricas;

import co.edu.uniquindio.poo.piitechsolutions.services.CatalogoService;
import co.edu.uniquindio.poo.piitechsolutions.services.DesplazamientoUnico;
import co.edu.uniquindio.poo.piitechsolutions.services.InicializadorData;
import co.edu.uniquindio.poo.piitechsolutions.services.PaqueteService;
import co.edu.uniquindio.poo.piitechsolutions.services.PoliticaDesplazamiento;
import co.edu.uniquindio.poo.piitechsolutions.services.SolicitudService;
import co.edu.uniquindio.poo.piitechsolutions.services.TecnicoService;

import java.time.LocalDate;
import java.util.List;

import java.util.ArrayList;


public class TechSolutionsController {

    private final CatalogoService catalogoService;
    private final PaqueteService paqueteService;
    private final TecnicoService tecnicoService;
    private final SolicitudService solicitudService;

    /**
     * Construye el controlador principal y configura
     * los componentes iniciales de TechSolutions.
     * Crea las dependencias necesarias para el
     * catálogo, los paquetes, las solicitudes
     * y las consultas de técnicos.
     */
    public TechSolutionsController() {
        RegistroFabricas registroFabricas = new RegistroFabricas();
        this.catalogoService = new CatalogoService(registroFabricas);
        this.paqueteService = new PaqueteService(catalogoService);
        LegacySupport legacySupport = new LegacySupport();
        LegacySupportAdapter adapter = new LegacySupportAdapter(legacySupport);
        ProveedorTecnicos proveedorTecnicos = adapter;
        this.tecnicoService = new TecnicoService(proveedorTecnicos);
        PoliticaDesplazamiento politica = new DesplazamientoUnico();
        this.solicitudService = new SolicitudService(tecnicoService, politica);
        InicializadorData.inicializarData(registroFabricas, catalogoService, paqueteService, solicitudService, legacySupport, adapter);
    }

    /**
     * Consulta los servicios originales registrados.
     *
     * @return Lista de servicios tecnológicos.
     */
    public List<ServicioTecnologico> listarServicios() {
        return catalogoService.listarServicios();
    }

    /**
     * Consulta las personalizaciones registradas.
     *
     * @return Lista de servicios personalizados.
     */
    public List<ServicioPersonalizado> listarPersonalizados() {
        return catalogoService.listarPersonalizados();
    }

    /**
     * Consulta los servicios originales y personalizados.
     *
     * @return Lista de elementos del catálogo.
     */
    public List<ElementoContratable> listarElementosCatalogo() {
        return catalogoService.listarElementos();
    }

    /**
     * Busca un servicio original mediante su código.
     *
     * @param codigo Código del servicio.
     * @return Servicio encontrado o null.
     */
    public ServicioTecnologico buscarServicio(String codigo) {
        return catalogoService.buscarServicio(codigo);
    }

    /**
     * Busca un servicio personalizado por su código.
     *
     * @param codigo Código de la personalización.
     * @return Personalización encontrada o null.
     */
    public ServicioPersonalizado buscarServicioPersonalizado(
            String codigo) {

        return catalogoService.buscarServicioPersonalizado(codigo);
    }

    /**
     * Consulta los complementos configurados.
     *
     * @return Lista de tipos de complementos.
     */
    public List<TipoComplemento> listarComplementos() {
        return catalogoService.listarComplementos();
    }

    /**
     * Consulta el costo adicional de un complemento.
     *
     * @param tipo Tipo de complemento.
     * @return Costo adicional.
     */
    public double consultarCostoComplemento(
            TipoComplemento tipo) {

        return catalogoService.consultarCostoComplemento(tipo);
    }

    /**
     * Crea y registra un servicio personalizado.
     *
     * Los complementos se incorporan mediante Decorator.
     *
     * @param codigoOriginal Código del servicio original.
     * @param nuevoCodigo Código de la personalización.
     * @param complementos Complementos seleccionados.
     * @return Servicio personalizado creado.
     */
    public ServicioPersonalizado personalizarServicio(
            String codigoOriginal,
            String nuevoCodigo,
            List<TipoComplemento> complementos) {

        validarCodigoDisponible(nuevoCodigo);

        return catalogoService.personalizarServicio(
                codigoOriginal,
                nuevoCodigo,
                complementos
        );
    }

    /**
     * Crea un paquete tecnológico vacío.
     *
     * @param codigo Código del nuevo paquete.
     * @param nombre Nombre del paquete.
     * @param descripcion Descripción del paquete.
     * @return Paquete creado.
     */
    public PaqueteTecnologico crearPaquete(
            String codigo,
            String nombre,
            String descripcion) {

        validarCodigoDisponible(codigo);

        return paqueteService.crearPaquete(
                codigo,
                nombre,
                descripcion
        );
    }

    /**
     * Consulta los paquetes tecnológicos registrados.
     *
     * @return Lista de paquetes.
     */
    public List<PaqueteTecnologico> listarPaquetes() {
        return paqueteService.listarPaquetes();
    }

    /**
     * Busca un paquete tecnológico por código.
     *
     * @param codigo Código del paquete.
     * @return Paquete encontrado o null.
     */
    public PaqueteTecnologico buscarPaquete(String codigo) {
        return paqueteService.buscarPaquete(codigo);
    }

    /**
     * Agrega un servicio, personalización o paquete
     * dentro de otro paquete tecnológico.
     *
     * La validación de referencias circulares
     * corresponde a PaqueteTecnologico.
     *
     * @param codigoPaquete Código del paquete destino.
     * @param codigoElemento Código del elemento a agregar.
     */
    public void agregarElementoPaquete(
            String codigoPaquete,
            String codigoElemento) {

        paqueteService.agregarElemento(
                codigoPaquete,
                codigoElemento
        );
    }

    /**
     * Elimina un componente directo de un paquete.
     *
     * @param codigoPaquete Código del paquete.
     * @param codigoElemento Código del elemento.
     * @return true si se eliminó el componente.
     */
    public boolean eliminarElementoPaquete(
            String codigoPaquete,
            String codigoElemento) {

        return paqueteService.eliminarElemento(
                codigoPaquete,
                codigoElemento
        );
    }

    /**
     * Consulta los componentes directos de un paquete.
     *
     * @param codigoPaquete Código del paquete.
     * @return Lista de elementos.
     */
    public List<ElementoContratable> listarElementosPaquete(
            String codigoPaquete) {

        return paqueteService.listarElementosPaquete(
                codigoPaquete
        );
    }

    /**
     * Calcula el costo actual de un paquete
     * utilizando el patrón Composite.
     *
     * @param codigoPaquete Código del paquete.
     * @return Costo total del paquete.
     */
    public double calcularCostoPaquete(String codigoPaquete) {
        return paqueteService.calcularCostoPaquete(codigoPaquete);
    }

    /**
     * Consulta los servicios y paquetes disponibles
     * para crear solicitudes.
     *
     * @return Lista de elementos contratables.
     */
    public List<ElementoContratable> listarElementosContratables() {

        List<ElementoContratable> elementos =
                new ArrayList<>();

        List<ElementoContratable> catalogo =
                catalogoService.listarElementos();

        for (ElementoContratable elemento : catalogo) {
            elementos.add(elemento);
        }

        List<PaqueteTecnologico> paquetes =
                paqueteService.listarPaquetes();

        for (PaqueteTecnologico paquete : paquetes) {
            elementos.add(paquete);
        }

        return elementos;
    }

    /**
     * Busca un elemento contratable por código.
     *
     * Puede ser un servicio original,
     * una personalización o un paquete.
     *
     * @param codigo Código solicitado.
     * @return Elemento encontrado o null.
     */
    public ElementoContratable buscarElementoContratable(
            String codigo) {

        return paqueteService.buscarElemento(codigo);
    }

    /**
     * Verifica si un código ya pertenece
     * al catálogo o a un paquete.
     *
     * @param codigo Código que se desea verificar.
     * @return true si se encuentra registrado.
     */
    public boolean existeCodigoElemento(String codigo) {
        return paqueteService.existeCodigo(codigo);
    }

    /**
     * Valida que un código nuevo no se encuentre
     * registrado en el catálogo o los paquetes.
     *
     * @param codigo Código que se desea utilizar.
     */
    private void validarCodigoDisponible(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "El código es obligatorio");
        }

        if (existeCodigoElemento(codigo.trim())) {
            throw new IllegalArgumentException(
                    "Ya existe un elemento con el código: "
                            + codigo);
        }
    }

    /**
     * Consulta los clientes registrados.
     *
     * @return Lista de clientes.
     */
    public List<Cliente> listarClientes() {
        return solicitudService.listarClientes();
    }

    /**
     * Busca un cliente mediante su documento.
     *
     * @param documento Documento del cliente.
     * @return Cliente encontrado o null.
     */
    public Cliente buscarCliente(String documento) {
        return solicitudService.buscarCliente(documento);
    }

    /**
     * Registra un cliente en la aplicación.
     *
     * @param cliente Cliente que se desea registrar.
     */
    public void registrarCliente(Cliente cliente) {
        solicitudService.registrarCliente(cliente);
    }

    /**
     * Crea una solicitud utilizando los códigos
     * de los elementos seleccionados.
     *
     * Verifica que cada elemento exista en TechSolutions
     * y delega la construcción a SolicitudService.
     *
     * @param codigo Código de la solicitud.
     * @param fechaCreacion Fecha de creación.
     * @param documentoCliente Documento del cliente.
     * @param codigosElementos Códigos de los elementos.
     * @param fechaProgramada Fecha opcional de atención.
     * @param observaciones Observaciones opcionales.
     * @param prioridad Prioridad opcional.
     * @return Solicitud registrada.
     */
    public SolicitudServicio crearSolicitud(
            String codigo,
            LocalDate fechaCreacion,
            String documentoCliente,
            List<String> codigosElementos,
            LocalDate fechaProgramada,
            String observaciones,
            PrioridadSolicitud prioridad) {

        if (codigosElementos == null
                || codigosElementos.isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe seleccionar al menos un elemento");
        }

        List<ElementoContratable> elementos =
                new ArrayList<>();

        for (String codigoElemento : codigosElementos) {

            ElementoContratable elemento =
                    buscarElementoContratable(codigoElemento);

            if (elemento == null) {
                throw new IllegalArgumentException(
                        "No existe el elemento: " + codigoElemento);
            }

            boolean repetido = false;

            for (ElementoContratable actual : elementos) {

                if (actual == elemento) {
                    repetido = true;
                    break;
                }
            }

            if (repetido) {
                throw new IllegalArgumentException(
                        "El elemento está repetido: "
                                + codigoElemento);
            }

            elementos.add(elemento);
        }

        return solicitudService.crearSolicitud(
                codigo,
                fechaCreacion,
                documentoCliente,
                elementos,
                fechaProgramada,
                observaciones,
                prioridad
        );
    }

    /**
     * Obtiene las solicitudes registradas.
     *
     * @return Lista de solicitudes.
     */
    public List<SolicitudServicio> listarSolicitudes() {
        return solicitudService.listarSolicitudes();
    }

    /**
     * Busca una solicitud por su código.
     *
     * @param codigo Código de la solicitud.
     * @return Solicitud encontrada o null.
     */
    public SolicitudServicio buscarSolicitud(String codigo) {
        return solicitudService.buscarSolicitud(codigo);
    }

    /**
     * Consulta las solicitudes asociadas
     * a un documento de cliente.
     *
     * @param documento Documento del cliente.
     * @return Lista de solicitudes del cliente.
     */
    public List<SolicitudServicio> listarSolicitudesCliente(
            String documento) {

        return solicitudService.listarSolicitudesCliente(documento);
    }

    /**
     * Confirma una solicitud después de verificar
     * la disponibilidad de los técnicos requeridos.
     *
     * @param codigoSolicitud Código de la solicitud.
     * @return Solicitud confirmada.
     */
    public SolicitudServicio confirmarSolicitud(
            String codigoSolicitud) {

        return solicitudService.confirmarSolicitud(codigoSolicitud);
    }

    /**
     * Consulta las especialidades que no cuentan
     * con un técnico disponible para una solicitud.
     *
     * @param codigoSolicitud Código de la solicitud.
     * @return Especialidades no disponibles.
     */
    public List<EspecialidadTecnica>
    consultarEspecialidadesNoDisponibles(
            String codigoSolicitud) {

        return solicitudService
                .consultarEspecialidadesNoDisponibles(
                        codigoSolicitud
                );
    }

    /**
     * Consulta la información de un técnico
     * mediante su especialidad.
     *
     * @param especialidad Especialidad requerida.
     * @return Información del técnico.
     */
    public InformacionTecnico consultarTecnico(
            EspecialidadTecnica especialidad) {

        return tecnicoService.consultarTecnico(especialidad);
    }

    /**
     * Consulta los técnicos de varias especialidades.
     *
     * @param especialidades Especialidades solicitadas.
     * @return Resultados de las consultas.
     */
    public List<TecnicoService.ConsultaTecnico> consultarTecnicos(
            List<EspecialidadTecnica> especialidades) {

        return tecnicoService.consultarTecnicos(especialidades);
    }

    /**
     * Verifica la disponibilidad de los técnicos
     * correspondientes a varias especialidades.
     *
     * @param especialidades Especialidades requeridas.
     * @return true si todos están disponibles.
     */
    public boolean verificarDisponibilidadTecnicos(
            List<EspecialidadTecnica> especialidades) {

        return tecnicoService.verificarDisponibilidad(especialidades);
    }
}
