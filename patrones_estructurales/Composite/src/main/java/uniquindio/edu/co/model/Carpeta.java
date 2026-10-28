package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public class Carpeta implements ElementoSistema {

    private String nombre;
    private List<ElementoSistema> elementoSistema;

    /**
     * Metodo constructor de la clase Carpeta
     * @param nombre de la carpeta
     */
    public Carpeta(String nombre){
        this.nombre = nombre;
        this.elementoSistema = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que permite agregar elementos
     * @param elemento
     */
    public void agregarElemento(ElementoSistema elemento){
        elementoSistema.add(elemento);
    }

    @Override
    public void mostrarElemento(String identacion) {
        System.out.println(identacion + " + Carpeta: " + nombre);
        for(ElementoSistema elemento : elementoSistema){
            elemento.mostrarElemento(identacion + "  ");
        }
    }

    @Override
    public void abrirArchivo() {
        System.out.println("Abriendo carpeta " +  nombre);
    }

}
