package uniquindio.edu.co.model;

public class Archivo implements ElementoSistema {

    private String nombre;

    /**
     * Metodo constructor de la clase Archivo
     * @param nombre del archivo
     */
    public Archivo(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void mostrarElemento(String identacion) {
        System.out.println(identacion + " - Archivo: " + nombre);
    }

    @Override
    public void abrirArchivo() {
        System.out.println("Abriendo archivo " +  nombre);
    }
}
