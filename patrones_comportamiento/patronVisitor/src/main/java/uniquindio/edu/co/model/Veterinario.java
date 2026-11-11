package uniquindio.edu.co.model;

public class Veterinario implements VisitanteAnimal {
    @Override
    public void visitar(Perro perro) {
        System.out.println("El veterinario visita al perrito: " + perro.getNombre() +
                " y lo atiende clinicamente.");
    }

    @Override
    public void visitar(Gato gato) {
        System.out.println("El veterinario visita al gatico: " + gato.nombre() +
                " y lo atiende clinicamente.");
    }
}
