package co.edu.uniquindio.poo.piitechsolutions.services;

import co.edu.uniquindio.poo.piitechsolutions.model.adapter.InformacionTecnico;
import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.EstadoSolicitud;
import co.edu.uniquindio.poo.piitechsolutions.model.PrioridadSolicitud;
import co.edu.uniquindio.poo.piitechsolutions.model.Cliente;
import co.edu.uniquindio.poo.piitechsolutions.model.ElementoContratable;
import co.edu.uniquindio.poo.piitechsolutions.model.SolicitudServicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitudService {
    private final List<Cliente> clientes;
    private final List<SolicitudServicio> solicitudes;
    private final TecnicoService tecnicoService;
    private final PoliticaDesplazamiento politicaDesplazamiento;

    /**
     * Construye el servicio de solicitudes.
     * @param tecnicoService Servicio de consulta de técnicos.
     * @param politicaDesplazamiento Política utilizada
     * para calcular los desplazamientos.
     * @throws IllegalArgumentException Si alguna
     * dependencia es nula.
     */
    public SolicitudService(TecnicoService tecnicoService, PoliticaDesplazamiento politicaDesplazamiento) {
        if (tecnicoService == null) {
            throw new IllegalArgumentException("El servicio de técnicos es obligatorio");
        }
        if (politicaDesplazamiento == null) {
            throw new IllegalArgumentException("La política de desplazamiento es obligatoria");
        }
        this.tecnicoService = tecnicoService;
        this.politicaDesplazamiento = politicaDesplazamiento;
        this.clientes = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
    }

    /**
     * Registra un cliente en TechSolutions.
     * No permite registrar dos clientes
     * con el mismo documento.
     * @param cliente Cliente que se desea registrar.
     * @throws IllegalArgumentException Si el cliente
     * es nulo o su documento ya está registrado.
     */
    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        if (existeCliente(cliente.getDocumento())) {
            throw new IllegalArgumentException("Ya existe un cliente con el documento: " + cliente.getDocumento());
        }
        clientes.add(cliente);
    }

    /**
     * Busca un cliente por su documento.
     * @param documento Documento del cliente.
     * @return Cliente encontrado o null.
     */
    public Cliente buscarCliente(String documento) {
        Cliente encontrado = null;
        if (documento != null) {
            for (Cliente cliente : clientes) {
                if (cliente.getDocumento().equals(documento)) {
                    encontrado = cliente;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * Verifica si existe un cliente registrado.
     * @param documento Documento solicitado.
     * @return true si el cliente existe.
     */
    public boolean existeCliente(String documento) {
        boolean existe = false;
        if (buscarCliente(documento) != null) {
            existe = true;
        }
        return existe;
    }

    /**
     * Obtiene los clientes registrados.
     * @return Copia de la lista de clientes.
     */
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    /**
     * Busca una solicitud mediante su código.
     * @param codigo Código de la solicitud.
     * @return Solicitud encontrada o null.
     */
    public SolicitudServicio buscarSolicitud(String codigo) {
        SolicitudServicio encontrada = null;
        if (codigo != null) {
            for (SolicitudServicio solicitud : solicitudes) {
                if (solicitud.getCodigo().equals(codigo.trim())) {
                    encontrada = solicitud;
                    break;
                }
            }
        }
        return encontrada;
    }

    /**
     * Verifica si ya existe una solicitud
     * con el código indicado.
     * @param codigo Código que se desea consultar.
     * @return true si la solicitud existe.
     */
    public boolean existeSolicitud(String codigo) {
        boolean existe = false;
        if (buscarSolicitud(codigo) != null) {
            existe = true;
        }
        return existe;
    }

    /**
     * Registra una solicitud previamente construida.
     * Verifica que el código no esté repetido
     * y que el cliente se encuentre registrado.
     * La solicitud debe encontrarse inicialmente
     * en estado REGISTRADA.
     * @param solicitud Solicitud que se desea registrar.
     * @throws IllegalArgumentException Si los datos
     * de la solicitud no son válidos.
     */
    public void registrarSolicitud(SolicitudServicio solicitud) {
        if (solicitud == null) {
            throw new IllegalArgumentException(
                    "La solicitud es obligatoria");
        }
        if (existeSolicitud(solicitud.getCodigo())) {
            throw new IllegalArgumentException("Ya existe una solicitud con el código: " + solicitud.getCodigo());
        }
        if (!existeCliente(solicitud.getCliente().getDocumento())) {
            throw new IllegalArgumentException("El cliente de la solicitud no está registrado");
        }
        if (solicitud.getEstado() != EstadoSolicitud.REGISTRADA) {
            throw new IllegalArgumentException("Solo pueden registrarse solicitudes " + "en estado REGISTRADA");
        }
        solicitudes.add(solicitud);
    }

    /**
     * Construye y registra una solicitud utilizando
     * el patrón creacional Builder.
     * Los datos obligatorios son el código,
     * la fecha, el cliente y los elementos contratados.
     * Los demás datos pueden ser nulos.
     * @param codigo Código de la solicitud.
     * @param fechaCreacion Fecha de creación.
     * @param documentoCliente Documento del cliente.
     * @param elementos Elementos que se contratarán.
     * @param fechaProgramada Fecha opcional de atención.
     * @param observaciones Observaciones opcionales.
     * @param prioridad Prioridad opcional.
     * @return Solicitud construida y registrada.
     */
    public SolicitudServicio crearSolicitud(
            String codigo,
            LocalDate fechaCreacion,
            String documentoCliente,
            List<ElementoContratable> elementos,
            LocalDate fechaProgramada,
            String observaciones,
            PrioridadSolicitud prioridad) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código de la solicitud es obligatorio");
        }
        if (existeSolicitud(codigo)) {
            throw new IllegalArgumentException("El código de la solicitud ya existe");
        }
        Cliente cliente = buscarCliente(documentoCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no se encuentra registrado");
        }
        if (elementos == null || elementos.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar al menos un elemento");
        }
        SolicitudServicio.Builder builder = new SolicitudServicio.Builder(codigo, fechaCreacion, cliente);
        for (ElementoContratable elemento : elementos) {
            builder.agregarElemento(elemento);
        }
        builder.conFechaProgramada(fechaProgramada);
        builder.conObservaciones(observaciones);
        builder.conPrioridad(prioridad);
        SolicitudServicio solicitud = builder.build();
        registrarSolicitud(solicitud);
        return solicitud;
    }

    /**
     * Obtiene todas las solicitudes registradas.
     * @return Copia de la lista de solicitudes.
     */
    public List<SolicitudServicio> listarSolicitudes() {
        return new ArrayList<>(solicitudes);
    }

    /**
     * Consulta las solicitudes de un cliente
     * mediante su documento.
     * @param documento Documento del cliente.
     * @return Lista de solicitudes del cliente.
     */
    public List<SolicitudServicio> listarSolicitudesCliente(String documento) {
        List<SolicitudServicio> resultado = new ArrayList<>();
        if (documento != null) {
            for (SolicitudServicio solicitud : solicitudes) {
                if (solicitud.getCliente().getDocumento().equals(documento.trim())) {
                    resultado.add(solicitud);
                }
            }
        }
        return resultado;
    }

    /**
     * Consulta las especialidades presenciales
     * que no tienen un técnico disponible.
     * Cada especialidad se consulta una sola vez
     * durante esta operación.
     * @param codigoSolicitud Código de la solicitud.
     * @return Lista de especialidades no disponibles.
     */
    public List<EspecialidadTecnica> consultarEspecialidadesNoDisponibles(String codigoSolicitud) {
        SolicitudServicio solicitud = buscarSolicitud(codigoSolicitud);
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no existe");
        }
        List<EspecialidadTecnica> requeridas = solicitud.obtenerEspecialidadesPresenciales();
        return tecnicoService.consultarEspecialidadesNoDisponibles(requeridas);
    }

    /**
     * Confirma una solicitud registrada.
     * Consulta los técnicos correspondientes
     * a las especialidades presenciales requeridas.
     * Si algún técnico no está disponible,
     * la solicitud permanece REGISTRADA.
     * Si todos están disponibles, calcula
     * el desplazamiento aplicando la política
     * configurada y confirma la solicitud.
     * Las consultas de técnicos se realizan
     * una sola vez por especialidad dentro
     * de este intento de confirmación.
     * @param codigoSolicitud Código de la solicitud.
     * @return Solicitud confirmada.
     * @throws IllegalArgumentException Si la solicitud
     * no existe o el costo calculado es inválido.
     * @throws IllegalStateException Si ya está confirmada
     * o no hay disponibilidad de técnicos.
     */
    public SolicitudServicio confirmarSolicitud(String codigoSolicitud) {
        SolicitudServicio solicitud = buscarSolicitud(codigoSolicitud);
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no existe");
        }
        if (solicitud.getEstado() != EstadoSolicitud.REGISTRADA) {
            throw new IllegalStateException("Solo pueden confirmarse solicitudes registradas");
        }
        List<EspecialidadTecnica> especialidades = solicitud.obtenerEspecialidadesPresenciales();
        List<TecnicoService.ConsultaTecnico> consultas = tecnicoService.consultarTecnicos(especialidades);
        for (TecnicoService.ConsultaTecnico consulta : consultas) {
            InformacionTecnico informacion = consulta.getInformacion();
            if (!informacion.disponible()) {
                throw new IllegalStateException("No hay técnico disponible para: " + consulta.getEspecialidad());
            }
        }
        double desplazamiento = politicaDesplazamiento.calcularCostoDesplazamiento(consultas);
        if (!Double.isFinite(desplazamiento)
                || desplazamiento < 0) {
            throw new IllegalArgumentException("El desplazamiento calculado no es válido");
        }
        solicitud.confirmar(desplazamiento);
        return solicitud;
    }
}
