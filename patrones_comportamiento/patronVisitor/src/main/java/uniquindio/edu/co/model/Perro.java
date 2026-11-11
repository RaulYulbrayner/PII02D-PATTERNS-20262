package uniquindio.edu.co.model;

public class Perro implements Animal {

    private String nombre;

    public Perro(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void recibirVisita(VisitanteAnimal visitante) {
        visitante.visitar(this);
    }

    public String getNombre() {
        return nombre;
    }
}
