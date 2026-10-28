package co.edu.uniquindio.poo.piitechsolutions.model.adapter;

public record InformacionTecnico(String codigoTecnico, boolean disponible, double costoDesplazamiento) {

    /**
     * Valida los datos recibidos del proveedor externo.
     * Cuando el técnico está disponible, debe tener
     * un código de identificación válido.
     * El costo de desplazamiento siempre debe
     * ser un número finito y no negativo.
     * Un técnico no disponible debe tener un costo
     * de desplazamiento igual a cero.
     * @throws IllegalArgumentException Si los datos
     * recibidos no cumplen las condiciones establecidas.
     */
    public InformacionTecnico {
        if (codigoTecnico == null || codigoTecnico.isBlank()) {
            throw new IllegalArgumentException("El código del técnico es obligatorio");
        }
        if (!Double.isFinite(costoDesplazamiento)
                || costoDesplazamiento < 0) {
            throw new IllegalArgumentException("El costo de desplazamiento no es válido");
        }
        if (!disponible && costoDesplazamiento != 0) {
            throw new IllegalArgumentException("Un técnico no disponible no puede " + "tener costo de desplazamiento");
        }
        codigoTecnico = codigoTecnico.trim();
    }
}
