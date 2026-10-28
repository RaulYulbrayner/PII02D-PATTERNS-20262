package co.edu.uniquindio.poo.piitechsolutions.model.adapter;

import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;

import java.util.ArrayList;
import java.util.List;


public class LegacySupportAdapter implements ProveedorTecnicos {
    private final LegacySupport legacySupport;
    private final List<EquivalenciaEspecialidad> equivalencias;

    /**
     * Construye el adaptador utilizando el sistema externo.
     * @param legacySupport Proveedor externo de técnicos.
     * @throws IllegalArgumentException Si el proveedor es nulo.
     */
    public LegacySupportAdapter(LegacySupport legacySupport) {
        if (legacySupport == null) {
            throw new IllegalArgumentException("El proveedor externo es obligatorio");
        }
        this.legacySupport = legacySupport;
        this.equivalencias = new ArrayList<>();
    }

    /**
     * Registra la equivalencia entre una especialidad
     * de TechSolutions y un código numérico externo.
     * Si la especialidad ya está registrada, actualiza
     * su código numérico.
     * No permite asignar un mismo código externo
     * a dos especialidades diferentes.
     * @param especialidad Especialidad de TechSolutions.
     * @param codigoExterno Código utilizado por LegacySupport.
     * @throws IllegalArgumentException Si los datos son
     * inválidos o el código pertenece a otra especialidad.
     */
    public void registrarEquivalencia(EspecialidadTecnica especialidad, int codigoExterno) {
        if (especialidad == null) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (codigoExterno <= 0) {
            throw new IllegalArgumentException("El código externo debe ser positivo");
        }
        for (EquivalenciaEspecialidad equivalencia : equivalencias) {
            if (equivalencia.getCodigoExterno() == codigoExterno && equivalencia.getEspecialidad() != especialidad) {
                throw new IllegalArgumentException("El código externo ya pertenece " + "a otra especialidad");
            }
        }
        int posicion = buscarPosicion(especialidad);
        if (posicion >= 0) {
            equivalencias.get(posicion).setCodigoExterno(codigoExterno);
        } else {
            EquivalenciaEspecialidad nuevaEquivalencia = new EquivalenciaEspecialidad(especialidad, codigoExterno);
            equivalencias.add(nuevaEquivalencia);
        }
    }

    /**
     * Busca la posición de una especialidad
     * dentro de las equivalencias registradas.
     * @param especialidad Especialidad solicitada.
     * @return Posición encontrada o -1 si no existe.
     */
    private int buscarPosicion(EspecialidadTecnica especialidad) {
        int posicion = -1;
        for (int i = 0; i < equivalencias.size(); i++) {
            if (equivalencias.get(i).getEspecialidad() == especialidad) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    /**
     * Obtiene el código numérico utilizado por
     * LegacySupport para una especialidad.
     * @param especialidad Especialidad técnica.
     * @return Código numérico del sistema externo.
     * @throws IllegalArgumentException Si no existe
     * una equivalencia registrada.
     */
    private int obtenerCodigoExterno(EspecialidadTecnica especialidad) {
        int posicion = buscarPosicion(especialidad);
        if (posicion == -1) {
            throw new IllegalArgumentException("No existe equivalencia para: " + especialidad);
        }
        return equivalencias.get(posicion).getCodigoExterno();
    }

    /**
     * Consulta un técnico utilizando una especialidad
     * propia de TechSolutions.
     * Convierte la especialidad en un código externo,
     * consulta LegacySupport e interpreta la respuesta
     * para obtener un objeto InformacionTecnico.
     * @param especialidad Especialidad requerida.
     * @return Información estructurada del técnico.
     * @throws IllegalArgumentException Si la especialidad
     * es nula o no tiene una equivalencia registrada.
     * @throws IllegalStateException Si la respuesta
     * externa tiene un formato inválido.
     */
    @Override
    public InformacionTecnico consultarTecnico(EspecialidadTecnica especialidad) {
        if (especialidad == null) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        int codigoExterno = obtenerCodigoExterno(especialidad);

        String respuesta = legacySupport.consultarTecnico(codigoExterno);

        return interpretarRespuesta(respuesta);
    }

    /**
     * Convierte una respuesta textual de LegacySupport
     * en un objeto InformacionTecnico.
     * El formato esperado es:
     * CODIGO_TECNICO|ESTADO|COSTO_DESPLAZAMIENTO
     * El estado puede ser DISPONIBLE o NO_DISPONIBLE.
     * @param respuesta Respuesta del proveedor externo.
     * @return Información estructurada del técnico.
     * @throws IllegalStateException Si la respuesta
     * es nula, incompleta o contiene valores inválidos.
     */
    private InformacionTecnico interpretarRespuesta(String respuesta) {
        if (respuesta == null || respuesta.isBlank()) {
            throw new IllegalStateException("El proveedor devolvió una respuesta vacía");
        }

        String[] datos = respuesta.split("\\|", -1);

        if (datos.length != 3) {
            throw new IllegalStateException(
                    "La respuesta del proveedor tiene "
                            + "un formato inválido");
        }

        String codigoTecnico = datos[0].trim();
        String estado = datos[1].trim();
        String costoTexto = datos[2].trim();

        boolean disponible;

        if (estado.equals("DISPONIBLE")) {
            disponible = true;

        } else if (estado.equals("NO_DISPONIBLE")) {
            disponible = false;

        } else {
            throw new IllegalStateException("El estado del técnico no es válido");
        }

        double costoDesplazamiento;

        try {
            costoDesplazamiento = Double.parseDouble(costoTexto);

        } catch (NumberFormatException excepcion) {
            throw new IllegalStateException("El costo recibido del proveedor " + "no es numérico", excepcion);
        }
        try {
            return new InformacionTecnico(codigoTecnico, disponible, costoDesplazamiento
            );

        } catch (IllegalArgumentException excepcion) {
            throw new IllegalStateException("Los datos recibidos del proveedor " + "no son válidos",excepcion);
        }
    }

    /**
     * Representa la equivalencia entre una
     * especialidad técnica y un código externo.
     * Es una clase interna privada porque solamente
     * el adaptador necesita administrar estos datos.
     */
    private static class EquivalenciaEspecialidad {
        private final EspecialidadTecnica especialidad;
        private int codigoExterno;

        /**
         * Construye una equivalencia de especialidad.
         * @param especialidad Especialidad de TechSolutions.
         * @param codigoExterno Código de LegacySupport.
         */
        private EquivalenciaEspecialidad(EspecialidadTecnica especialidad, int codigoExterno) {
            this.especialidad = especialidad;
            this.codigoExterno = codigoExterno;
        }

        /**
         * Obtiene la especialidad registrada.
         * @return Especialidad técnica.
         */
        private EspecialidadTecnica getEspecialidad() {
            return especialidad;
        }

        /**
         * Obtiene el código numérico externo.
         * @return Código de LegacySupport.
         */
        private int getCodigoExterno() {
            return codigoExterno;
        }

        /**
         * Actualiza el código externo asociado
         * a la especialidad.
         * @param codigoExterno Nuevo código numérico.
         */
        private void setCodigoExterno(int codigoExterno) {
            this.codigoExterno = codigoExterno;
        }
    }
}
