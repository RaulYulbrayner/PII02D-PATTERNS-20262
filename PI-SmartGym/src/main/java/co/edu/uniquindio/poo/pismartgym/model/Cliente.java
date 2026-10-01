package co.edu.uniquindio.poo.pismartgym.model;

import java.time.LocalDate;

public class Cliente {

    private String nombreCompleto;
    private String documento;
    private String telefono;
    private String correoElectronico;
    private int edad;
    private LocalDate fechaRegistro;

    /**
     * Metodo que permite construir un nuevo cliente.
     * @param nombreCompleto nombre completo del cliente
     * @param documento documento de identidad
     * @param telefono número de teléfono
     * @param correoElectronico correo electrónico
     * @param edad edad del cliente
     * @param fechaRegistro fecha de registro
     */
    public Cliente(String nombreCompleto, String documento, String telefono, String correoElectronico, int edad, LocalDate fechaRegistro) {
        this.nombreCompleto = nombreCompleto;
        this.documento = documento;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", edad=" + edad +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}