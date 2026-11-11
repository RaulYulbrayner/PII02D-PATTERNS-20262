package uniquindio.edu.co.model;

public class Cuidador implements VisitanteAnimal {
    @Override
    public void visitar(Perro perro) {
        System.out.println("El cuidador visita al perrito: " + perro.getNombre() +
                " le da comida y mucho cariño.");
    }

    @Override
    public void visitar(Gato gato) {
        System.out.println("El cuidador visita al gatico: " + gato.nombre() +
                " le da comida y mucho cariño.");
    }
}
