package co.edu.uniquindio.poo.piitechsolutions.services;

import co.edu.uniquindio.poo.piitechsolutions.model.PaqueteTecnologico;
import co.edu.uniquindio.poo.piitechsolutions.model.ElementoContratable;

import java.util.ArrayList;
import java.util.List;

public class PaqueteService {

    private final CatalogoService catalogoService;
    private final List<PaqueteTecnologico> paquetes;

    /**
     * Construye el servicio de administración
     * de paquetes tecnológicos.
     * @param catalogoService Catálogo de servicios
     * originales y personalizados.
     * @throws IllegalArgumentException Si el catálogo es nulo.
     */
    public PaqueteService(CatalogoService catalogoService) {
        if (catalogoService == null) {
            throw new IllegalArgumentException("El catálogo de servicios es obligatorio");
        }
        this.catalogoService = catalogoService;
        this.paquetes = new ArrayList<>();
    }

    /**
     * Crea y registra un paquete tecnológico vacío.
     * Verifica que el código no se encuentre utilizado
     * por otro paquete o elemento del catálogo.
     * @param codigo Código del paquete.
     * @param nombre Nombre del paquete.
     * @param descripcion Descripción del paquete.
     * @return Paquete creado y registrado.
     * @throws IllegalArgumentException Si los datos
     * son inválidos o el código ya existe.
     */
    public PaqueteTecnologico crearPaquete(String codigo, String nombre, String descripcion) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código del paquete es obligatorio");
        }
        if (existeCodigo(codigo)) {
            throw new IllegalArgumentException("El código ya se encuentra registrado: " + codigo);
        }
        PaqueteTecnologico paquete = new PaqueteTecnologico(codigo, nombre, descripcion);
        paquetes.add(paquete);
        return paquete;
    }

    /**
     * Registra un paquete tecnológico previamente creado.
     * @param paquete Paquete que se desea registrar.
     * @throws IllegalArgumentException Si es nulo
     * o su código ya se encuentra registrado.
     */
    public void registrarPaquete(PaqueteTecnologico paquete) {
        if (paquete == null) {
            throw new IllegalArgumentException("El paquete es obligatorio");
        }
        if (existeCodigo(paquete.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un elemento con ese código");
        }
        paquetes.add(paquete);
    }

    /**
     * Busca un paquete mediante su código.
     * @param codigo Código solicitado.
     * @return Paquete encontrado o null.
     */
    public PaqueteTecnologico buscarPaquete(String codigo) {
        PaqueteTecnologico encontrado = null;
        if (codigo != null) {
            for (PaqueteTecnologico paquete : paquetes) {
                if (paquete.getCodigo().equals(codigo.trim())) {
                    encontrado = paquete;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * Verifica si un código está utilizado
     * por un paquete o un elemento del catálogo.
     * @param codigo Código que se desea consultar.
     * @return true si el código ya existe.
     */
    public boolean existeCodigo(String codigo) {
        boolean existe = false;
        if (buscarPaquete(codigo) != null
                || catalogoService.existeCodigo(codigo)) {

            existe = true;
        }
        return existe;
    }

    /**
     * Busca un elemento contratable mediante su código.
     * Primero consulta los servicios del catálogo
     * y luego los paquetes registrados.
     * @param codigo Código del elemento.
     * @return Elemento encontrado o null.
     */
    public ElementoContratable buscarElemento(String codigo) {
        ElementoContratable elemento = catalogoService.buscarElemento(codigo);
        if (elemento == null) {
            elemento = buscarPaquete(codigo);
        }
        return elemento;
    }

    /**
     * Agrega un elemento contratable a un paquete.
     * Tanto el paquete destino como el elemento
     * deben estar previamente registrados.
     * Delega al paquete las validaciones de elementos
     * repetidos y referencias circulares.
     * @param codigoPaquete Código del paquete destino.
     * @param codigoElemento Código del elemento a agregar.
     * @throws IllegalArgumentException Si alguno
     * de los elementos no existe.
     * @throws IllegalStateException Si se intenta
     * generar una referencia circular.
     */
    public void agregarElemento(String codigoPaquete, String codigoElemento) {
        PaqueteTecnologico paquete = buscarPaquete(codigoPaquete);
        if (paquete == null) {
            throw new IllegalArgumentException("No existe el paquete: " + codigoPaquete);
        }
        ElementoContratable elemento = buscarElemento(codigoElemento);
        if (elemento == null) {
            throw new IllegalArgumentException("No existe el elemento: " + codigoElemento);
        }
        paquete.agregarElemento(elemento);
    }

    /**
     * Elimina un elemento agregado directamente
     * a un paquete tecnológico.
     * No elimina el elemento del catálogo ni
     * de otros paquetes.
     * @param codigoPaquete Código del paquete.
     * @param codigoElemento Código del elemento.
     * @return true si el elemento fue eliminado.
     */
    public boolean eliminarElemento(String codigoPaquete, String codigoElemento) {
        boolean eliminado = false;
        PaqueteTecnologico paquete = buscarPaquete(codigoPaquete);
        if (paquete == null) {
            throw new IllegalArgumentException("No existe el paquete: " + codigoPaquete);
        }
        ElementoContratable elemento = buscarElemento(codigoElemento);
        if (elemento != null) {
            eliminado = paquete.eliminarElemento(elemento);
        }
        return eliminado;
    }

    /**
     * Obtiene una copia de los paquetes registrados.
     * @return Lista de paquetes tecnológicos.
     */
    public List<PaqueteTecnologico> listarPaquetes() {
        return new ArrayList<>(paquetes);
    }

    /**
     * Obtiene los elementos agregados directamente
     * a un paquete tecnológico.
     * @param codigoPaquete Código del paquete.
     * @return Lista de elementos del paquete.
     */
    public List<ElementoContratable> listarElementosPaquete(String codigoPaquete) {
        PaqueteTecnologico paquete = buscarPaquete(codigoPaquete);
        if (paquete == null) {
            throw new IllegalArgumentException("No existe el paquete: " + codigoPaquete);
        }
        return paquete.getElementos();
    }

    /**
     * Consulta el costo total actual de un paquete.
     * El cálculo se realiza mediante Composite
     * y admite paquetes anidados.
     * @param codigoPaquete Código del paquete.
     * @return Costo del paquete tecnológico.
     */
    public double calcularCostoPaquete(String codigoPaquete) {
        PaqueteTecnologico paquete = buscarPaquete(codigoPaquete);
        if (paquete == null) {
            throw new IllegalArgumentException("No existe el paquete: " + codigoPaquete);
        }
        return paquete.calcularCosto();
    }
}