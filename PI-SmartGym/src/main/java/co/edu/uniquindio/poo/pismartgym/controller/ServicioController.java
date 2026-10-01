package co.edu.uniquindio.poo.pismartgym.controller;

import co.edu.uniquindio.poo.pismartgym.model.Gimnasio;
import co.edu.uniquindio.poo.pismartgym.model.ServicioAdicional;

import java.util.List;

public class ServicioController {

    private final Gimnasio gimnasio;

    public ServicioController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    public String agregarServicio(ServicioAdicional servicio) {
        return gimnasio.agregarServicioAdicional(servicio);
    }

    public boolean actualizarServicio(String codigo, ServicioAdicional servicio) {
        return gimnasio.actualizarServicioAdicional(codigo, servicio);
    }

    public boolean eliminarServicio(String codigo) {
        return gimnasio.eliminarServicioAdicional(codigo);
    }

    public List<ServicioAdicional> obtenerServicios() {
        return gimnasio.getServiciosAdicionales();
    }
}
