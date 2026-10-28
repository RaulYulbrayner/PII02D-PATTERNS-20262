package co.edu.uniquindio.poo.piitechsolutions.model;

public final class Cliente {

    private String documento;
    private String nombreCompleto;
    private String correoElectronico;

    /**
     * Construye un cliente con sus datos obligatorios.
     * @param documento Documento de identificación.
     * @param nombreCompleto Nombre completo del cliente.
     * @param correoElectronico Correo electrónico.
     * @throws IllegalArgumentException Si alguno de los
     * datos obligatorios es nulo o vacío, o si el correo
     * electrónico no contiene el carácter @.
     */
    public Cliente(String documento, String nombreCompleto, String correoElectronico) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("El documento del cliente es obligatorio");
        }
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("El nombre completo del cliente es obligatorio");
        }
        if (correoElectronico == null
                || correoElectronico.isBlank()
                || !correoElectronico.contains("@")) {
            throw new IllegalArgumentException("El correo electrónico no es válido");
        }
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene el documento de identificación del cliente.
     * @return Documento del cliente.
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Obtiene el nombre completo del cliente.
     * @return Nombre completo.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     * @return Correo electrónico.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Determina si otro cliente tiene el mismo
     * documento de identificación.
     * Permite comparar clientes sin sobrescribir
     * los métodos equals() y hashCode().
     * @param otroCliente Cliente que se desea comparar.
     * @return true si ambos clientes tienen
     * el mismo documento.
     */
    public boolean tieneMismoDocumento(Cliente otroCliente) {
        boolean mismoDocumento = false;
        if (otroCliente != null) {
            if (documento.equals(otroCliente.getDocumento())) {
                mismoDocumento = true;
            }
        }
        return mismoDocumento;
    }

    /**
     * Devuelve una representación textual del cliente.
     * Se utiliza para mostrar los clientes registrados
     * en los controles de selección de JavaFX.
     * @return Documento y nombre completo del cliente.
     */
    @Override
    public String toString() {
        return documento + " - " + nombreCompleto;
    }
}
