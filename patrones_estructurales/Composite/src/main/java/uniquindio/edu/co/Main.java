package uniquindio.edu.co;

import uniquindio.edu.co.model.Archivo;
import uniquindio.edu.co.model.Carpeta;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //Patron composite
    public static void main(String[] args) {

        Archivo parcial1 = new Archivo("Parcial I.pdf");
        Archivo notas = new Archivo("Notas.xlxs");
        Archivo guia = new Archivo("Guia.docxs");

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

    }
}