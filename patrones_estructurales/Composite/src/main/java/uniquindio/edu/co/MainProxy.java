package uniquindio.edu.co;

import uniquindio.edu.co.model.Archivo;
import uniquindio.edu.co.model.ArchivoProxy;
import uniquindio.edu.co.model.Carpeta;
import uniquindio.edu.co.model.ElementoSistema;

public class MainProxy {

    public static void main(String[] args) {

        Archivo parcial1 = new Archivo("Parcial I.pdf");
        ElementoSistema parcial = new ArchivoProxy(parcial1, false);
        ElementoSistema notas = new Archivo("Notas.xlxs");
        ElementoSistema guia = new Archivo("Guia.docxs");

        Carpeta programacionII = new Carpeta("Programación II");
        Carpeta proyecto = new Carpeta("Proyecto final PGII");

        programacionII.agregarElemento(parcial1);
        programacionII.agregarElemento(notas);
        programacionII.agregarElemento(proyecto);

        Carpeta programacion = new Carpeta("Programación");
        programacion.agregarElemento(programacionII);

        Carpeta programacionI = new Carpeta("Programación I");
        programacion.agregarElemento(programacionI);

        proyecto.agregarElemento(guia);

        programacion.mostrarElemento(" ");

        //proxy
        parcial.abrirArchivo();
        notas.abrirArchivo();
        guia.abrirArchivo();

    }

}
