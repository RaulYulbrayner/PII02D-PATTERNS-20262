package co.edu.uniquindio.poo.piitechsolutions.model;

import java.util.ArrayList;
import java.util.List;

public class PaqueteTecnologico implements ElementoContratable {

    private final String codigo;
    private final String nombre;
    private final String descripcion;

    private final List<ElementoContratable> elementos;

    /**
     * Construye un paquete tecnológico inicialmente vacío.
     * @param codigo Código identificador del paquete.
     * @param nombre Nombre del paquete.
     * @param descripcion Descripción del paquete.
     * @throws IllegalArgumentException Si algún dato
     * obligatorio es nulo o vacío.
     */
    public PaqueteTecnologico(String codigo, String nombre, String descripcion) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código del paquete es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del paquete es obligatorio");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción del paquete es obligatoria");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.elementos = new ArrayList<>();
    }

    /**
     * Agrega un elemento contratable al paquete.
     * Puede recibir un servicio original,
     * un servicio personalizado u otro paquete.
     * Valida que:
     * - El elemento no sea nulo.
     * - No se agregue dos veces la misma instancia.
     * - Un paquete no se agregue a sí mismo.
     * - No se generen referencias circulares indirectas.
     * @param elemento Elemento que se desea agregar.
     * @throws IllegalArgumentException Si el elemento
     * es nulo o ya está agregado directamente.
     * @throws IllegalStateException Si se genera
     * una referencia circular.
     */
    public void agregarElemento(ElementoContratable elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser nulo");
        }
        if (elemento == this) {
            throw new IllegalStateException("Un paquete no puede contenerse a sí mismo");
        }
        if (contieneReferenciaDirecta(elemento)) {
            throw new IllegalArgumentException("El elemento ya pertenece al paquete");
        }
        if (elemento instanceof PaqueteTecnologico paquete) {
            if (paquete.contienePaquete(this)) {
                throw new IllegalStateException("No se permiten referencias circulares");
            }
        }
        elementos.add(elemento);
    }

    /**
     * Verifica si un elemento se encuentra agregado
     * directamente al paquete.
     * Utiliza comparación por identidad para reconocer
     * exactamente el mismo objeto.
     * @param elemento Elemento que se desea localizar.
     * @return true si el elemento ya está agregado.
     */
    private boolean contieneReferenciaDirecta(ElementoContratable elemento) {
        boolean encontrado = false;
        for (ElementoContratable actual : elementos) {
            if (actual == elemento) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    /**
     * Determina si este paquete contiene directa
     * o indirectamente otro paquete tecnológico.
     * El recorrido se realiza recursivamente
     * sobre los paquetes anidados.
     * También devuelve true cuando el paquete
     * buscado es la misma instancia actual.
     * @param paqueteBuscado Paquete que se desea localizar.
     * @return true si el paquete está contenido
     * directa o indirectamente.
     */
    public boolean contienePaquete(PaqueteTecnologico paqueteBuscado) {
        boolean encontrado = false;
        if (paqueteBuscado != null) {
            if (this == paqueteBuscado) {
                encontrado = true;
            } else {
                for (ElementoContratable elemento : elementos) {
                    if (elemento instanceof PaqueteTecnologico paquete) {
                        if (paquete.contienePaquete(paqueteBuscado)) {
                            encontrado = true;
                            break;
                        }
                    }
                }
            }
        }
        return encontrado;
    }

    /**
     * Elimina un elemento agregado directamente
     * al paquete.
     * No elimina el objeto del catálogo ni de
     * otros paquetes donde también esté incorporado.
     * @param elemento Elemento que se desea retirar.
     * @return true si se eliminó correctamente.
     */
    public boolean eliminarElemento(ElementoContratable elemento) {
        boolean eliminado = false;
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i) == elemento) {
                elementos.remove(i);
                eliminado = true;
                break;
            }
        }
        return eliminado;
    }

    /**
     * Obtiene una copia de los componentes directos
     * del paquete tecnológico.
     * Se devuelve una nueva lista para impedir
     * que otros componentes modifiquen la lista
     * interna sin pasar por agregarElemento().
     * @return Lista de elementos del paquete.
     */
    public List<ElementoContratable> getElementos() {
        return new ArrayList<>(elementos);
    }

    /**
     * Calcula recursivamente el costo del paquete.
     * Cada componente calcula su propio costo:
     * - Un servicio original devuelve su costo base.
     * - Un servicio personalizado incluye complementos.
     * - Un paquete anidado suma sus componentes.
     * No es necesario identificar el tipo concreto
     * de cada servicio para realizar el cálculo.
     *
     * @return Costo total del paquete.
     */
    @Override
    public double calcularCosto() {
        double total = 0;
        for (ElementoContratable elemento : elementos) {
            total += elemento.calcularCosto();
        }
        return total;
    }

    /**
     * Obtiene las especialidades técnicas necesarias
     * para prestar los servicios presenciales
     * contenidos en el paquete.
     * Recorre todos los elementos mediante
     * polimorfismo y evita especialidades repetidas
     * utilizando ciclos tradicionales.
     * @return Lista de especialidades sin duplicados.
     */
    @Override
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

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return codigo + " | " + nombre + " | $" + calcularCosto();
    }
}
