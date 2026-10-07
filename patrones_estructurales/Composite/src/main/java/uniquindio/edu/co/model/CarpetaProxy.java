package uniquindio.edu.co.model;

public class CarpetaProxy implements ElementoSistema {

    public Carpeta carpeta;
    private Boolean autorizado;

    /**
     * Metodo constructor de la clase CarpetaProxy
     * @param carpeta
     * @param autorizado
     */
    public CarpetaProxy(Carpeta carpeta, boolean autorizado){
        this.carpeta = carpeta;
        this.autorizado = autorizado;
    }

    @Override
    public void mostrarElemento(String identacion) {
        System.out.println(identacion + " - Archivo: " + carpeta.getNombre());
    }

    @Override
    public void abrirArchivo() {
        if(autorizado){
            carpeta.abrirArchivo();
        }else {
            System.out.println("Acceso denegado");
        }
    }

}
