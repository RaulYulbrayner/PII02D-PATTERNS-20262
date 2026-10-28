package co.edu.uniquindio.poo.piitechsolutions.services;

import co.edu.uniquindio.poo.piitechsolutions.model.Cliente;
import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoComplemento;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoServicio;
import co.edu.uniquindio.poo.piitechsolutions.model.factory.*;
import co.edu.uniquindio.poo.piitechsolutions.model.decorator.*;
import co.edu.uniquindio.poo.piitechsolutions.model.adapter.*;


public final class InicializadorData {

    /**
     * Impide crear objetos de la clase inicializadora.
     */
    private InicializadorData() {
    }

    /**
     * Registra los datos iniciales de TechSolutions.
     * Debe ejecutarse una sola vez sobre los servicios
     * y proveedores previamente construidos.
     * @param registroFabricas Registro de Factory Method.
     * @param catalogoService Administración del catálogo.
     * @param paqueteService Administración de paquetes.
     * @param solicitudService Administración de solicitudes.
     * @param legacySupport Sistema externo simulado.
     * @param legacySupportAdapter Adaptador del sistema externo.
     * @throws IllegalArgumentException Si alguna dependencia
     * recibida es nula.
     */
    public static void inicializarData(
            RegistroFabricas registroFabricas,
            CatalogoService catalogoService,
            PaqueteService paqueteService,
            SolicitudService solicitudService,
            LegacySupport legacySupport,
            LegacySupportAdapter legacySupportAdapter) {

        if (registroFabricas == null
                || catalogoService == null
                || paqueteService == null
                || solicitudService == null
                || legacySupport == null
                || legacySupportAdapter == null) {

            throw new IllegalArgumentException(
                    "Todas las dependencias son obligatorias");
        }
        registrarFabricas(registroFabricas);
        registrarServicios(catalogoService);
        registrarComplementos(catalogoService);
        registrarPaquetes(paqueteService);
        registrarTecnicos(legacySupport, legacySupportAdapter);
        registrarClientes(solicitudService);
    }

    /**
     * Registra las fábricas concretas utilizadas
     * para crear cada tipo de servicio tecnológico.
     * @param registroFabricas Registro de fábricas.
     */
    private static void registrarFabricas(RegistroFabricas registroFabricas) {
        registroFabricas.registrar(TipoServicio.MANTENIMIENTO_PREVENTIVO, new CreadorPreventivo());
        registroFabricas.registrar(TipoServicio.MANTENIMIENTO_CORRECTIVO, new CreadorCorrectivo());
        registroFabricas.registrar(TipoServicio.INSTALACION_REDES, new CreadorRedes());
        registroFabricas.registrar(TipoServicio.RECUPERACION_INFORMACION, new CreadorRecuperacion());
    }

    /**
     * Crea y registra los servicios tecnológicos
     * originales utilizando Factory Method.
     * Los costos y las descripciones comerciales
     * se definen exclusivamente en este método.
     * @param catalogoService Servicio de catálogo.
     */
    private static void registrarServicios(CatalogoService catalogoService) {
        catalogoService.crearYRegistrarServicio(
                TipoServicio.MANTENIMIENTO_PREVENTIVO,
                "S-01",
                "Mantenimiento preventivo",
                "Revisión, limpieza y optimización de equipos.",
                80000.0,
                EspecialidadTecnica.MANTENIMIENTO,
                true
        );

        catalogoService.crearYRegistrarServicio(
                TipoServicio.MANTENIMIENTO_CORRECTIVO,
                "S-02",
                "Mantenimiento correctivo",
                "Diagnóstico y corrección de fallas de equipos.",
                120000.0,
                EspecialidadTecnica.MANTENIMIENTO,
                true
        );

        catalogoService.crearYRegistrarServicio(
                TipoServicio.INSTALACION_REDES,
                "S-03",
                "Instalación de redes",
                "Configuración e instalación de redes informáticas.",
                180000.0,
                EspecialidadTecnica.REDES,
                true
        );

        catalogoService.crearYRegistrarServicio(
                TipoServicio.RECUPERACION_INFORMACION,
                "S-04",
                "Recuperación de información",
                "Recuperación de archivos y datos digitales.",
                150000.0,
                EspecialidadTecnica.RECUPERACION,
                false
        );
    }

    /**
     * Registra los complementos disponibles,
     * sus costos y sus creadores concretos.
     * Permite personalizar servicios utilizando
     * el patrón estructural Decorator.
     * @param catalogoService Servicio de catálogo.
     */
    private static void registrarComplementos(CatalogoService catalogoService) {
        catalogoService.registrarComplemento(
                TipoComplemento.ATENCION_PRIORITARIA,
                35000.0,
                new CatalogoService.CreadorAtencionPrioritaria()
        );

        catalogoService.registrarComplemento(
                TipoComplemento.SOPORTE_30_DIAS,
                50000.0,
                new CatalogoService.CreadorSoporteTecnico30Dias()
        );

        catalogoService.registrarComplemento(
                TipoComplemento.ATENCION_DOMICILIO,
                25000.0,
                new CatalogoService.CreadorAtencionDomicilio()
        );
    }

    /**
     * Registra dos paquetes tecnológicos de ejemplo.
     * El segundo paquete contiene al primero,
     * permitiendo demostrar paquetes anidados
     * mediante el patrón Composite.
     * @param paqueteService Servicio de paquetes.
     */
    private static void registrarPaquetes(PaqueteService paqueteService) {
        paqueteService.crearPaquete(
                "PAQ-001",
                "Paquete de mantenimiento",
                "Incluye mantenimiento preventivo y correctivo."
        );

        paqueteService.agregarElemento(
                "PAQ-001",
                "S-01"
        );

        paqueteService.agregarElemento(
                "PAQ-001",
                "S-02"
        );

        paqueteService.crearPaquete(
                "PAQ-002",
                "Paquete empresarial",
                "Incluye mantenimiento e instalación de redes."
        );

        paqueteService.agregarElemento(
                "PAQ-002",
                "PAQ-001"
        );

        paqueteService.agregarElemento(
                "PAQ-002",
                "S-03"
        );
    }

    /**
     * Configura los códigos externos y las respuestas
     * simuladas de disponibilidad de técnicos.
     * LegacySupport recibe códigos numéricos
     * y devuelve respuestas de tipo String.
     * El adaptador relaciona las especialidades
     * de TechSolutions con esos códigos.
     * @param legacySupport Proveedor externo simulado.
     * @param adapter Adaptador del proveedor externo.
     */
    private static void registrarTecnicos(LegacySupport legacySupport, LegacySupportAdapter adapter) {
        adapter.registrarEquivalencia(
                EspecialidadTecnica.MANTENIMIENTO,
                101
        );

        adapter.registrarEquivalencia(
                EspecialidadTecnica.REDES,
                102
        );

        adapter.registrarEquivalencia(
                EspecialidadTecnica.RECUPERACION,
                103
        );

        legacySupport.registrarRespuesta(
                101,
                "TEC-105|DISPONIBLE|30000"
        );

        legacySupport.registrarRespuesta(
                102,
                "TEC-108|DISPONIBLE|45000"
        );

        legacySupport.registrarRespuesta(
                103,
                "SIN_TECNICO|NO_DISPONIBLE|0"
        );
    }

    /**
     * Registra los clientes iniciales para permitir
     * la creación de solicitudes desde JavaFX.
     * Los datos corresponden a registros ficticios
     * utilizados con fines académicos.
     * @param solicitudService Servicio de solicitudes.
     */
    private static void registrarClientes(SolicitudService solicitudService) {

        solicitudService.registrarCliente(
                new Cliente(
                        "1001001",
                        "Ana Torres",
                        "ana.torres@ejemplo.com"
                )
        );

        solicitudService.registrarCliente(
                new Cliente(
                        "1001002",
                        "Carlos Ramírez",
                        "carlos.ramirez@ejemplo.com"
                )
        );

        solicitudService.registrarCliente(
                new Cliente(
                        "1001003",
                        "Laura Martínez",
                        "laura.martinez@ejemplo.com"
                )
        );
    }
}