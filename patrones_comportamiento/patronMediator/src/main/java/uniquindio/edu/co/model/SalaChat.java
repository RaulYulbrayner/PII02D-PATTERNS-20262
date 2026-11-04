package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public class SalaChat implements MediadorChat {

    private List<Usuario> listUsuariosSalaChat = new ArrayList<>();

    public void agregarUsuario(Usuario usuario){
        listUsuariosSalaChat.add(usuario);
    }

    @Override
    public void enviarMensaje(String mensaje, Usuario emisor) {
        for (Usuario usuario : listUsuariosSalaChat){
            if(usuario != emisor){
                usuario.recibirMensaje(mensaje, emisor.getNombre());
            }
        }
    }
}
