package uniquindio.edu.co.model;

public class Celular {

    public void cargar(CargadorUSBC cargador){
        System.out.println("Celular conectado");
        cargador.cargarUSBC();
        System.out.println("Celular cargando con USB-C");
    }

}
