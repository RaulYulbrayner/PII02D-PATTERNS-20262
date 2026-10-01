package co.edu.uniquindio.poo.pismartgym.model;

import java.util.ArrayList;

public class Gimnasio {

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String paginaWeb;

    private ArrayList<Cliente> clientes;
    private ArrayList<Entrenador> entrenadores;
    private ArrayList<PlanEntrenamiento> planes;
    private ArrayList<ServicioAdicional> serviciosAdicionales;
    private ArrayList<Inscripcion> inscripciones;

    /**
     * Metodo que construye un gimnasio.
     * @param nombreComercial nombre comercial
     * @param nit NIT
     * @param direccion dirección
     * @param telefono teléfono
     * @param correoElectronico correo
     * @param paginaWeb página web
     */
    public Gimnasio(String nombreComercial, String nit, String direccion, String telefono, String correoElectronico, String paginaWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.paginaWeb = paginaWeb;

        clientes = new ArrayList<>();
        entrenadores = new ArrayList<>();
        planes = new ArrayList<>();
        serviciosAdicionales = new ArrayList<>();
        inscripciones = new ArrayList<>();
    }

    /**
     * Metodo que permite buscar un cliente por documento.
     * @param documento documento buscado
     * @return cliente encontrado o null
     */
    public Cliente buscarCliente(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Metodo que permite agregar un cliente si no existe.
     * @param cliente cliente
     * @return mensaje con el resultado
     */
    public String agregarCliente(Cliente cliente) {
        String mensaje = "";
        Cliente clienteEncontrado = buscarCliente(cliente.getDocumento());
        if (clienteEncontrado != null) {
            mensaje = "El cliente ya se encuentra registrado.";
        } else {
            clientes.add(cliente);
            mensaje = "Cliente registrado correctamente.";
        }
        return mensaje;
    }

    /**
     * Metodo que permite actualizar la información de un cliente registrado.
     * @param documento documento del cliente que se desea actualizar
     * @param actualizado objeto con la nueva información del cliente
     * @return true si el cliente fue actualizado, false en caso contrario
     */
    public boolean actualizarCliente(String documento, Cliente actualizado) {
        Cliente cliente = buscarCliente(documento);
        if (cliente == null) {
            return false;
        }
        cliente.setNombreCompleto(actualizado.getNombreCompleto());
        cliente.setDocumento(actualizado.getDocumento());
        cliente.setTelefono(actualizado.getTelefono());
        cliente.setCorreoElectronico(actualizado.getCorreoElectronico());
        cliente.setEdad(actualizado.getEdad());
        cliente.setFechaRegistro(actualizado.getFechaRegistro());
        return true;
    }

    /**
     * Metodo que permite eliminar un cliente registrado en el gimnasio.
     * @param documento documento del cliente que se desea eliminar
     * @return true si el cliente fue eliminado, false en caso contrario
     */
    public boolean eliminarCliente(String documento) {
        Cliente cliente = buscarCliente(documento);
        if (cliente == null) {
            return false;
        }
        clientes.remove(cliente);
        return true;
    }

    /**
     * Metodo que permite buscar un entrenador mediante su identificación.
     * @param identificacion identificación
     * @return entrenador encontrado o null
     */
    public Entrenador buscarEntrenador(String identificacion) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getIdentificacion().equals(identificacion)) {
                return entrenador;
            }
        }
        return null;
    }

    /**
     * Metodo que permite agregar un entrenador.
     * @param entrenador entrenador
     * @return mensaje
     */
    public String agregarEntrenador(Entrenador entrenador) {
        String mensaje = "";
        Entrenador entrenadorEncontrado = buscarEntrenador(entrenador.getIdentificacion());
        if (entrenadorEncontrado != null) {
            mensaje = "El entrenador ya existe.";
        } else {
            entrenadores.add(entrenador);
            mensaje = "Entrenador registrado correctamente.";
        }
        return mensaje;
    }

    /**
     * Metodo que permite actualizar la información de un entrenador registrado.
     * @param identificacion identificación del entrenador que se desea actualizar
     * @param actualizado objeto con la nueva información del entrenador
     * @return true si fue actualizado, false en caso contrario
     */
    public boolean actualizarEntrenador(String identificacion, Entrenador actualizado) {
        Entrenador entrenador = buscarEntrenador(identificacion);
        if (entrenador == null) {
            return false;
        }
        entrenador.setIdentificacion(actualizado.getIdentificacion());
        entrenador.setNombre(actualizado.getNombre());
        entrenador.setEspecialidad(actualizado.getEspecialidad());
        entrenador.setTelefono(actualizado.getTelefono());
        entrenador.setTarifaSesion(actualizado.getTarifaSesion());
        return true;
    }

    /**
     * Metodo que permite eliminar un entrenador registrado.
     * @param identificacion identificación del entrenador
     * @return true si fue eliminado, false en caso contrario
     */
    public boolean eliminarEntrenador(String identificacion) {
        Entrenador entrenador = buscarEntrenador(identificacion);
        if (entrenador == null) {
            return false;
        }
        entrenadores.remove(entrenador);
        return true;
    }

    /**
     * Metodo que permite buscar un plan mediante su código.
     * @param codigo código
     * @return plan encontrado o null
     */
    public PlanEntrenamiento buscarPlan(String codigo) {
        for (PlanEntrenamiento plan : planes) {
            if (plan.getCodigo().equals(codigo)) {
                return plan;
            }
        }
        return null;
    }

    /**
     * Metodo que permite agregar un plan.
     * @param plan plan
     * @return mensaje
     */
    public String agregarPlan(PlanEntrenamiento plan) {
        String mensaje = "";
        PlanEntrenamiento planEncontrado = buscarPlan(plan.getCodigo());
        if (planEncontrado != null) {
            mensaje = "El plan ya existe.";
        } else {
            planes.add(plan);
            mensaje = "Plan registrado correctamente.";
        }
        return mensaje;
    }

    /**
     * Metodo que permiete buscar un servicio adicional.
     * @param codigo código
     * @return servicio encontrado o null
     */
    public ServicioAdicional buscarServicioAdicional(String codigo) {
        for (ServicioAdicional servicio : serviciosAdicionales) {
            if (servicio.getCodigo().equals(codigo)) {
                return servicio;
            }
        }
        return null;
    }

    /**
     * Metodo que permite agregar un servicio adicional.
     * @param servicio servicio
     * @return mensaje
     */
    public String agregarServicioAdicional(ServicioAdicional servicio) {
        String mensaje = "";
        ServicioAdicional servicioEncontrado = buscarServicioAdicional(servicio.getCodigo());
        if (servicioEncontrado != null) {
            mensaje = "El servicio ya existe.";
        } else {
            serviciosAdicionales.add(servicio);
            mensaje = "Servicio registrado correctamente.";
        }
        return mensaje;
    }

    /**
     * Metodo que permite actualizar la información de un servicio adicional.
     * @param codigo código del servicio que se desea actualizar
     * @param actualizado objeto con la nueva información
     * @return true si fue actualizado, false en caso contrario
     */
    public boolean actualizarServicioAdicional(String codigo, ServicioAdicional actualizado) {
        ServicioAdicional servicio = buscarServicioAdicional(codigo);
        if (servicio == null) {
            return false;
        }
        servicio.setCodigo(actualizado.getCodigo());
        servicio.setNombre(actualizado.getNombre());
        servicio.setDescripcion(actualizado.getDescripcion());
        servicio.setPrecio(actualizado.getPrecio());
        servicio.setDisponible(actualizado.isDisponible());
        return true;
    }

    /**
     * Metodo que permite eliminar un servicio adicional.
     * @param codigo código del servicio
     * @return true si fue eliminado, false en caso contrario
     */
    public boolean eliminarServicioAdicional(String codigo) {
        ServicioAdicional servicio = buscarServicioAdicional(codigo);
        if (servicio == null) {
            return false;
        }
        serviciosAdicionales.remove(servicio);
        return true;
    }

    /**
     * Metodo que permite buscar una inscripción.
     * @param codigo código
     * @return inscripción encontrada o null
     */
    public Inscripcion buscarInscripcion(String codigo) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getCodigo().equals(codigo)) {
                return inscripcion;
            }
        }
        return null;
    }

    /**
     * Metodo que permite agregar una inscripción.
     * @param inscripcion inscripción
     * @return mensaje
     */
    public String agregarInscripcion(Inscripcion inscripcion) {
        String mensaje;
        Inscripcion inscripcionEncontrada = buscarInscripcion(inscripcion.getCodigo());
        if (inscripcionEncontrada != null) {
            mensaje = "La inscripción ya existe.";
        } else {
            inscripciones.add(inscripcion);
            mensaje = "Inscripción registrada correctamente.";
        }
        return mensaje;
    }

    /**
     * Metodo que permite eliminar un plan mediante su código.
     * @param codigo código del plan
     * @return true si fue eliminado, false en caso contrario
     */
    public boolean eliminarPlan(String codigo) {
        PlanEntrenamiento plan = buscarPlan(codigo);
        if (plan == null) {
            return false;
        }
        planes.remove(plan);
        return true;
    }

    /**
     * Metodo que permite actualizar un plan reemplazándolo por el nuevo objeto recibido.
     * @param codigo código original del plan
     * @param actualizado nuevo plan
     * @return true si fue actualizado, false en caso contrario
     */
    public boolean actualizarPlan(String codigo, PlanEntrenamiento actualizado) {
        PlanEntrenamiento plan = buscarPlan(codigo);
        if (plan == null) {
            return false;
        }
        int posicion = planes.indexOf(plan);
        planes.set(posicion, actualizado);
        return true;
    }

    /**
     * Metodo que permita eliminar una inscripción registrada.
     * @param codigo código de la inscripción
     * @return true si fue eliminada, false en caso contrario
     */
    public boolean eliminarInscripcion(String codigo) {
        Inscripcion inscripcion = buscarInscripcion(codigo);
        if (inscripcion == null) {
            return false;
        }
        inscripciones.remove(inscripcion);
        return true;
    }

    /**
     * Metodo que permite actualizar una inscripción reemplazando la existente por una nueva instancia.
     * @param codigo código de la inscripción original
     * @param actualizada nueva inscripción
     * @return true si fue actualizada, false en caso contrario
     */
    public boolean actualizarInscripcion(String codigo, Inscripcion actualizada) {
        Inscripcion inscripcion = buscarInscripcion(codigo);
        if (inscripcion == null) {
            return false;
        }
        int posicion = inscripciones.indexOf(inscripcion);
        inscripciones.set(posicion, actualizada
        );
        return true;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public ArrayList<PlanEntrenamiento> getPlanes() {
        return planes;
    }

    public ArrayList<ServicioAdicional>
    getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }
}