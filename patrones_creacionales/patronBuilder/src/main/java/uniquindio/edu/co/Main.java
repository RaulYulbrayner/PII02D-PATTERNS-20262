package uniquindio.edu.co;

import uniquindio.edu.co.model.Persona;
import uniquindio.edu.co.model.PersonaBuilder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        //-----Forma de crear objetos sin el patron builder
        Persona persona1 = new Persona("Raul",
                30, "24/09/1996",
                "Masculino", 3144444,
                "raul-gmail.com");

        Persona persona2 = new Persona("Camilo");

        System.out.println(persona1);
        System.out.println(persona2);

        //-----Crear objetos de forma flexible con el patron builder de tipo persona

        PersonaBuilder persona3 = new PersonaBuilder.Builder()
                .email("juan@gmail.com")
                .edad(30)
                .nombre("Juan Soto")
                .build();

        PersonaBuilder persona4 = new PersonaBuilder.Builder()
                .nombre("Yulbranner").build();

        System.out.println(persona3);
        System.out.println(persona4);

    }

}