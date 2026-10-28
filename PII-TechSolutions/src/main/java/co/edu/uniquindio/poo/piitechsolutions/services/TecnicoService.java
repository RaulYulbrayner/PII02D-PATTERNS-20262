package co.edu.uniquindio.poo.piitechsolutions.services;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.adapter.InformacionTecnico;
import co.edu.uniquindio.poo.piitechsolutions.model.adapter.ProveedorTecnicos;

import java.util.ArrayList;
import java.util.List;


public class TecnicoService {

    private final ProveedorTecnicos proveedorTecnicos;

    /**
     * Construye el servicio de consulta de técnicos.
     * @param proveedorTecnicos Proveedor utilizado
     * para realizar las consultas.
     * @throws IllegalArgumentException Si el proveedor es nulo.
     */
    public TecnicoService(ProveedorTecnicos proveedorTecnicos) {
        if (proveedorTecnicos == null) {
            throw new IllegalArgumentException("El proveedor de técnicos es obligatorio");
        }
        this.proveedorTecnicos = proveedorTecnicos;
    }

    /**
     * Consulta la información de un técnico
     * para una especialidad determinada.
     * @param especialidad Especialidad requerida.
     * @return Información del técnico consultado.
     * @throws IllegalArgumentException Si la
     * especialidad es nula.
     * @throws IllegalStateException Si el proveedor
     * devuelve una respuesta nula.
     */
    public InformacionTecnico consultarTecnico(EspecialidadTecnica especialidad) {
        if (especialidad == null) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        InformacionTecnico informacion = proveedorTecnicos.consultarTecnico(especialidad);
        if (informacion == null) {
            throw new IllegalStateException("El proveedor no devolvió información");
        }
        return informacion;
    }

    /**
     * Consulta los técnicos correspondientes
     * a varias especialidades.
     * Cada especialidad se consulta una sola vez,
     * incluso si aparece repetida en la lista.
     * @param especialidades Lista de especialidades.
     * @return Lista de consultas realizadas.
     * @throws IllegalArgumentException Si la lista
     * es nula o contiene especialidades nulas.
     */
    public List<ConsultaTecnico> consultarTecnicos(List<EspecialidadTecnica> especialidades) {
        if (especialidades == null) {
            throw new IllegalArgumentException("La lista de especialidades es obligatoria");
        }
        List<ConsultaTecnico> consultas = new ArrayList<>();
        for (EspecialidadTecnica especialidad : especialidades) {
            if (especialidad == null) {
                throw new IllegalArgumentException("No se permiten especialidades nulas");
            }
            if (!existeConsulta(consultas, especialidad)) {
                InformacionTecnico informacion = consultarTecnico(especialidad);
                ConsultaTecnico consulta = new ConsultaTecnico(especialidad, informacion);
                consultas.add(consulta);
            }
        }
        return consultas;
    }

    /**
     * Verifica si una especialidad ya fue consultada.
     * @param consultas Consultas realizadas.
     * @param especialidad Especialidad que se busca.
     * @return true si la especialidad ya existe.
     */
    private boolean existeConsulta(List<ConsultaTecnico> consultas, EspecialidadTecnica especialidad) {
        boolean existe = false;
        for (ConsultaTecnico consulta : consultas) {
            if (consulta.getEspecialidad() == especialidad) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    /**
     * Determina si todas las especialidades indicadas
     * tienen un técnico disponible.
     * Si no se requieren especialidades presenciales,
     * devuelve true porque no es necesario asignar técnicos.
     * @param especialidades Especialidades requeridas.
     * @return true si todas tienen técnico disponible.
     */
    public boolean verificarDisponibilidad(List<EspecialidadTecnica> especialidades) {
        boolean disponibles = true;
        List<ConsultaTecnico> consultas = consultarTecnicos(especialidades);
        for (ConsultaTecnico consulta : consultas) {
            if (!consulta.getInformacion().disponible()) {
                disponibles = false;
                break;
            }
        }
        return disponibles;
    }

    /**
     * Obtiene las especialidades que no cuentan
     * con un técnico disponible.
     * @param especialidades Especialidades requeridas.
     * @return Lista de especialidades sin disponibilidad.
     */
    public List<EspecialidadTecnica> consultarEspecialidadesNoDisponibles(List<EspecialidadTecnica> especialidades) {
        List<EspecialidadTecnica> noDisponibles = new ArrayList<>();
        List<ConsultaTecnico> consultas = consultarTecnicos(especialidades);
        for (ConsultaTecnico consulta : consultas) {
            if (!consulta.getInformacion().disponible()) {
                noDisponibles.add(
                        consulta.getEspecialidad()
                );
            }
        }
        return noDisponibles;
    }

    /**
     * Representa el resultado de consultar
     * una especialidad técnica.
     * Relaciona la especialidad solicitada
     * con los datos obtenidos del proveedor.
     * Se declara pública para permitir que
     * SolicitudService utilice los resultados
     * sin repetir las consultas.
     */
    public static class ConsultaTecnico {
        private final EspecialidadTecnica especialidad;
        private final InformacionTecnico informacion;

        /**
         * Construye el resultado de una consulta.
         * @param especialidad Especialidad consultada.
         * @param informacion Información del técnico.
         */
        public ConsultaTecnico(EspecialidadTecnica especialidad, InformacionTecnico informacion) {
            if (especialidad == null || informacion == null) {
                throw new IllegalArgumentException("La especialidad y la información " + "son obligatorias");
            }
            this.especialidad = especialidad;
            this.informacion = informacion;
        }

        /**
         * Obtiene la especialidad consultada.
         * @return Especialidad técnica.
         */
        public EspecialidadTecnica getEspecialidad() {
            return especialidad;
        }

        /**
         * Obtiene la información del técnico.
         * @return Datos de disponibilidad y desplazamiento.
         */
        public InformacionTecnico getInformacion() {
            return informacion;
        }
    }
}
