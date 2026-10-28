package co.edu.uniquindio.poo.piitechsolutions.model.decorator;

import co.edu.uniquindio.poo.piitechsolutions.model.ServicioPersonalizable;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoComplemento;

public class AtencionPrioritaria extends DecoradorServicio {

    /**
     * Metodo que construye un servicio con atención prioritaria.
     * @param servicio Servicio que recibirá el complemento.
     * @param incremento Costo adicional de atención prioritaria.
     * el incremento es inválido o el complemento ya existe.
     */
    public AtencionPrioritaria(ServicioPersonalizable servicio, double incremento) {
        super(servicio, TipoComplemento.ATENCION_PRIORITARIA, incremento);
    }
}
