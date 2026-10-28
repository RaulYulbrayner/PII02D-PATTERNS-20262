package co.edu.uniquindio.poo.piitechsolutions.model.decorator;

import co.edu.uniquindio.poo.piitechsolutions.model.ServicioPersonalizable;
import co.edu.uniquindio.poo.piitechsolutions.model.TipoComplemento;
import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;

import java.util.ArrayList;
import java.util.List;

public class AtencionDomicilio extends DecoradorServicio {

    /**
     * Construye un servicio tecnológico con el
     * complemento de atención a domicilio.
     * @param servicio Servicio que recibirá el complemento.
     * @param incremento Costo adicional de atención
     * a domicilio en pesos colombianos.
     * @throws IllegalArgumentException Si el servicio
     * es nulo, el incremento es inválido o el
     * complemento ya se encuentra aplicado.
     */
    public AtencionDomicilio(ServicioPersonalizable servicio, double incremento) {
        super(servicio, TipoComplemento.ATENCION_DOMICILIO, incremento);
    }

    /**
     * Obtiene las especialidades técnicas necesarias
     * para prestar el servicio a domicilio.
     * Conserva las especialidades presenciales que
     * ya requiere el servicio decorado e incorpora
     * su especialidad técnica de referencia.
     * Evita registrar especialidades repetidas.
     * Por ejemplo, si un mantenimiento correctivo
     * ya requiere un técnico de mantenimiento,
     * agregar atención a domicilio no duplicará
     * dicha especialidad.
     * @return Lista de especialidades presenciales
     * requeridas para prestar el servicio.
     */
    @Override
    public List<EspecialidadTecnica> obtenerEspecialidadesPresenciales() {
        List<EspecialidadTecnica> especialidades = new ArrayList<>(super.obtenerEspecialidadesPresenciales());
        EspecialidadTecnica especialidad = getEspecialidadReferencia();
        if (!especialidades.contains(especialidad)) {
            especialidades.add(especialidad);
        }
        return especialidades;
    }

}