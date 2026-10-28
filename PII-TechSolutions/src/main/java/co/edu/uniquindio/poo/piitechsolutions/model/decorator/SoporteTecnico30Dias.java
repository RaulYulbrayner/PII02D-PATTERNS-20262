package co.edu.uniquindio.poo.piitechsolutions.model.decorator;

import co.edu.uniquindio.poo.piitechsolutions.model.ServicioPersonalizable;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoComplemento;

public class SoporteTecnico30Dias extends DecoradorServicio {

    /**
     * Metodo que construye un servicio con soporte técnico
     * durante 30 días.
     * @param servicio Servicio que recibirá el complemento.
     * @param incremento Costo adicional del soporte.
     * @throws IllegalArgumentException Si los datos son
     * inválidos o el complemento ya fue aplicado.
     */
    public SoporteTecnico30Dias(ServicioPersonalizable servicio, double incremento) {
        super(servicio, TipoComplemento.SOPORTE_30_DIAS, incremento);
    }
}
