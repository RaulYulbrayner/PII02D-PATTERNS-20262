package uniquindio.edu.co;

import uniquindio.edu.co.model.SalaChat;
import uniquindio.edu.co.model.Usuario;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        // Sala
        SalaChat sala = new SalaChat();

        // Usuarios
        Usuario ana = new Usuario("Ana", sala);
        Usuario raul = new Usuario("Raul", sala);
        Usuario luisa = new Usuario("Luisa", sala);

        // Agregar los usuarios a las salas
        sala.agregarUsuario(ana);
        sala.agregarUsuario(raul);
        sala.agregarUsuario(luisa);

        // ana envie un mensaje por el mediator
        ana.enviarMensaje("Hola muchachos como estan?");
        System.out.println();
        luisa.enviarMensaje("Muy bien... :)");
        System.out.println();
        raul.enviarMensaje("Que pena, estaba ocupado");

    }

}