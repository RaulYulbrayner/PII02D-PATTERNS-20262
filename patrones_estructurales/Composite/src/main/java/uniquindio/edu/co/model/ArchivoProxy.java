package uniquindio.edu.co.model;

public class ArchivoProxy implements ElementoSistema {

    public Archivo archivo;
    private Boolean autorizado;

    /**
     * Metodo constructor de la clase ArchivoProxy
     * @param archivo
     * @param autorizado
     */
    public ArchivoProxy(Archivo archivo, boolean autorizado){
        this.archivo = archivo;
        this.autorizado = autorizado;
    }

    @Override
    public void mostrarElemento(String identacion) {
        System.out.println(identacion + " - Archivo: " + archivo.getNombre());
    }

    @Override
    public void abrirArchivo() {
        if(autorizado){
            archivo.abrirArchivo();
        }else {
            System.out.println("Acceso denegado");
        }
    }
}
