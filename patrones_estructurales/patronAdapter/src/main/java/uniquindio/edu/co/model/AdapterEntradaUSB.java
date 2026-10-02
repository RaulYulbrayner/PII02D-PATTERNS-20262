package uniquindio.edu.co.model;

public class AdapterEntradaUSB implements CargadorUSBC {

    private EntradaUSB entradaUSB;

    public AdapterEntradaUSB(EntradaUSB entradaUSB){
        this.entradaUSB = entradaUSB;
    }

    @Override
    public void cargarUSBC() {
        System.out.println("Convirtiendo de USB A USB-C");
        entradaUSB.cargarUSB();
    }

}
