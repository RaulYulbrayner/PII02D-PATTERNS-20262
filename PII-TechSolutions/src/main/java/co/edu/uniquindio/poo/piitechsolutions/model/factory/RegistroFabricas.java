package co.edu.uniquindio.poo.piitechsolutions.model.factory;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.ServicioTecnologico;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoServicio;

import java.util.ArrayList;
import java.util.List;

public class RegistroFabricas {
    private final List<TipoServicio> tipos;
    private final List<CreadorServicio> fabricas;

    /**
     * Construye un registro de fábricas vacío.
     * Inicializa las listas encargadas de relacionar
     * cada tipo de servicio con su fábrica.
     */
    public RegistroFabricas() {
        this.tipos = new ArrayList<>();
        this.fabricas = new ArrayList<>();
    }

    /**
     * Registra una fábrica asociada a un tipo de servicio.
     * Si el tipo ya se encuentra registrado,
     * reemplaza la fábrica anterior.
     * Si el tipo no existe, incorpora un nuevo
     * registro en las dos listas.
     * @param tipo Tipo de servicio tecnológico.
     * @param fabrica Fábrica encargada de crearlo.
     * @throws IllegalArgumentException Si alguno
     * de los parámetros es nulo.
     */
    public void registrar(TipoServicio tipo, CreadorServicio fabrica) {
        if (tipo == null || fabrica == null) {
            throw new IllegalArgumentException("El tipo y la fábrica son obligatorios");
        }
        int posicion = buscarPosicion(tipo);
        if (posicion >= 0) {
            fabricas.set(posicion, fabrica);
        } else {
            tipos.add(tipo);
            fabricas.add(fabrica);
        }
    }

    /**
     * Busca la posición de un tipo de servicio
     * dentro de la lista de tipos registrados.
     * @param tipo Tipo de servicio que se desea buscar.
     * @return Posición encontrada o -1 si no existe.
     */
    private int buscarPosicion(TipoServicio tipo) {
        int posicion = -1;
        for (int i = 0; i < tipos.size(); i++) {
            if (tipos.get(i) == tipo) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    /**
     * Obtiene la fábrica registrada para
     * un tipo de servicio tecnológico.
     * @param tipo Tipo de servicio solicitado.
     * @return Fábrica correspondiente.
     * @throws IllegalArgumentException Si el tipo
     * es nulo o no tiene una fábrica registrada.
     */
    public CreadorServicio obtenerFabrica(TipoServicio tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de servicio es obligatorio");
        }
        int posicion = buscarPosicion(tipo);
        if (posicion == -1) {
            throw new IllegalArgumentException("No existe una fábrica registrada para: " + tipo);
        }
        return fabricas.get(posicion);
    }

    /**
     * Crea un servicio tecnológico utilizando
     * la fábrica asociada al tipo solicitado.
     * Primero localiza la fábrica y posteriormente
     * delega la construcción del servicio mediante
     * el método crearServicio().
     * @param tipo Tipo de servicio tecnológico.
     * @param codigo Código identificador del servicio.
     * @param nombre Nombre del servicio.
     * @param descripcion Descripción del servicio.
     * @param costoBase Costo base en pesos colombianos.
     * @param especialidad Especialidad técnica asociada.
     * @param presencial Indica si requiere atención presencial.
     * @return Servicio tecnológico creado.
     * @throws IllegalArgumentException Si los datos son
     * inválidos o no existe una fábrica registrada.
     */
    public ServicioTecnologico crearServicio(TipoServicio tipo, String codigo, String nombre, String descripcion, double costoBase, EspecialidadTecnica especialidad, boolean presencial) {
        CreadorServicio fabrica = obtenerFabrica(tipo);
        return fabrica.crearServicio(codigo, nombre, descripcion, costoBase, especialidad, presencial);
    }
}

