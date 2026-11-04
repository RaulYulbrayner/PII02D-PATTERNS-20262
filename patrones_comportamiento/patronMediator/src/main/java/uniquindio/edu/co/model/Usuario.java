package uniquindio.edu.co.model;

public class Usuario {

    private String nombre;
    private MediadorChat mediador;

    public Usuario(String nombre, MediadorChat mediador) {
        this.nombre = nombre;
        this.mediador = mediador;
    }

    public void enviarMensaje(String mensaje){
        System.out.println(nombre + " envia : " + mensaje);
        mediador.enviarMensaje(mensaje, this);
    }

    public void recibirMensaje(String mensaje, String remitente){
        System.out.println(nombre + " recibe de " + remitente +  " : " + mensaje);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MediadorChat getMediador() {
        return mediador;
    }

    public void setMediador(MediadorChat mediador) {
        this.mediador = mediador;
    }
}
