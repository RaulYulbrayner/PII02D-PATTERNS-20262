package co.edu.uniquindio.poo.piitechsolutions.model.adapter;

import java.util.ArrayList;
import java.util.List;

public class LegacySupport {

    private final List<RespuestaLegacy> respuestas;

    /**
     * Construye un sistema externo sin respuestas
     * inicialmente registradas.
     */
    public LegacySupport() {
        this.respuestas = new ArrayList<>();
    }

    /**
     * Registra una respuesta asociada al código
     * numérico de una especialidad técnica.
     * Si el código ya existe, reemplaza la
     * respuesta previamente registrada.
     * @param codigoEspecialidad Código numérico
     * utilizado por el sistema externo.
     * @param respuesta Información del técnico
     * en el formato del proveedor externo.
     * @throws IllegalArgumentException Si el código
     * o la respuesta no son válidos.
     */
    public void registrarRespuesta(int codigoEspecialidad, String respuesta) {
        if (codigoEspecialidad <= 0) {
            throw new IllegalArgumentException("El código de especialidad debe ser positivo");
        }
        if (respuesta == null || respuesta.isBlank()) {
            throw new IllegalArgumentException("La respuesta del proveedor es obligatoria");
        }
        int posicion = buscarPosicion(codigoEspecialidad);
        if (posicion >= 0) {
            respuestas.get(posicion).setRespuesta(respuesta);
        } else {
            RespuestaLegacy nuevaRespuesta = new RespuestaLegacy(codigoEspecialidad, respuesta);
            respuestas.add(nuevaRespuesta);
        }
    }

    /**
     * Busca la posición de una especialidad técnica
     * dentro de las respuestas registradas.
     * @param codigoEspecialidad Código que se desea buscar.
     * @return Posición encontrada o -1 si no existe.
     */
    private int buscarPosicion(int codigoEspecialidad) {
        int posicion = -1;
        for (int i = 0; i < respuestas.size(); i++) {
            RespuestaLegacy actual = respuestas.get(i);
            if (actual.getCodigoEspecialidad() == codigoEspecialidad) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    /**
     * Consulta la información de un técnico utilizando
     * el código numérico del sistema externo.
     * Devuelve la respuesta registrada como String
     * para que el adaptador pueda interpretarla.
     * Cuando no existe una respuesta para el código,
     * devuelve un resultado de técnico no disponible.
     * @param codigoEspecialidad Código numérico
     * de la especialidad solicitada.
     * @return Respuesta textual del proveedor externo.
     */
    public String consultarTecnico(int codigoEspecialidad) {
        String resultado = "SIN_TECNICO|NO_DISPONIBLE|0";
        int posicion = buscarPosicion(codigoEspecialidad);
        if (posicion >= 0) {
            resultado = respuestas.get(posicion).getRespuesta();
        }
        return resultado;
    }

    /**
     * Representa la relación entre un código
     * de especialidad y su respuesta textual.
     * Es una clase interna privada porque solamente
     * LegacySupport necesita administrar estos datos.
     */
    private static class RespuestaLegacy {
        private final int codigoEspecialidad;
        private String respuesta;

        /**
         * Construye un registro del sistema externo.
         * @param codigoEspecialidad Código numérico.
         * @param respuesta Respuesta asociada.
         */
        private RespuestaLegacy(int codigoEspecialidad, String respuesta) {
            this.codigoEspecialidad = codigoEspecialidad;
            this.respuesta = respuesta;
        }

        /**
         * Obtiene el código de la especialidad.
         * @return Código numérico.
         */
        private int getCodigoEspecialidad() {
            return codigoEspecialidad;
        }

        /**
         * Obtiene la respuesta registrada.
         * @return Respuesta textual.
         */
        private String getRespuesta() {
            return respuesta;
        }

        /**
         * Actualiza la respuesta de una especialidad.
         * @param respuesta Nueva respuesta textual.
         */
        private void setRespuesta(String respuesta) {
            this.respuesta = respuesta;
        }
    }
}