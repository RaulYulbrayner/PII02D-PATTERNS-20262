package uniquindio.edu.co.model;

public record Gato(String nombre) implements Animal {
    @Override
    public void recibirVisita(VisitanteAnimal visitante) {
        visitante.visitar(this);
    }
}
