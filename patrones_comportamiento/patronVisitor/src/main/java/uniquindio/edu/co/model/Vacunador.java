package uniquindio.edu.co.model;

public class Vacunador implements VisitanteAnimal {
    @Override
    public void visitar(Perro perro) {
        System.out.println("El vacunador visita al perrito: " + perro.getNombre() +
                " y vacuna.");
    }

    @Override
    public void visitar(Gato gato) {
        System.out.println("El vacunador visita al gatico: " + gato.nombre() +
                " y lo vacuna.");
    }
}
