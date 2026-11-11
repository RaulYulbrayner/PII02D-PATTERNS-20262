package uniquindio.edu.co;

import uniquindio.edu.co.model.*;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Animal> refugio = List.of(new Perro("Max"), new Gato("Milu"),
                new Perro("Rex"), new Gato("Tom"));

        VisitanteAnimal ciudador = new Cuidador();
        VisitanteAnimal veterinario = new Veterinario();
        VisitanteAnimal vacunador = new Vacunador();

        System.out.println("---- Comida - cuidador ----");
        for (Animal animal : refugio){
            animal.recibirVisita(ciudador);
        }

        System.out.println("---- Atencion - veterinario ----");
        for (Animal animal : refugio){
            animal.recibirVisita(veterinario);
        }

        System.out.println("---- Vacunacion - vacunador ----");
        for (Animal animal : refugio){
            animal.recibirVisita(vacunador);
        }

    }
}