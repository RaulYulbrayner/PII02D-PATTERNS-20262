package co.edu.uniquindio.poo.piitechsolutions.viewController;

import co.edu.uniquindio.poo.piitechsolutions.controller.TechSolutionsController;
import co.edu.uniquindio.poo.piitechsolutions.model.EspecialidadTecnica;
import co.edu.uniquindio.poo.piitechsolutions.model.adapter.InformacionTecnico;

import co.edu.uniquindio.poo.piitechsolutions.services.TecnicoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;


public class TecnicosViewController {

    @FXML
    private ComboBox<EspecialidadTecnica> cmbEspecialidades;

    @FXML
    private Label lblEspecialidad;

    @FXML
    private Label lblCodigoTecnico;

    @FXML
    private Label lblDisponibilidad;

    @FXML
    private Label lblCostoDesplazamiento;

    @FXML
    private ListView<String> listaResultados;

    private TechSolutionsController techSolutionsController;

    /**
     * Inicializa los controles visuales.
     *
     * Carga las especialidades definidas en el enum
     * y establece los valores iniciales de los campos.
     *
     * La consulta al proveedor se realizará después
     * de recibir el controlador principal.
     */
    @FXML
    private void initialize() {

        List<EspecialidadTecnica> especialidades =
                new ArrayList<>();

        for (EspecialidadTecnica especialidad
                : EspecialidadTecnica.values()) {

            especialidades.add(especialidad);
        }

        cmbEspecialidades.setItems(
                FXCollections.observableArrayList(
                        especialidades
                )
        );

        if (!especialidades.isEmpty()) {

            cmbEspecialidades.getSelectionModel()
                    .selectFirst();
        }

        limpiarInformacion();
    }

    /**
     * Recibe el controlador compartido
     * de la aplicación TechSolutions.
     *
     * Una vez asignado, consulta el técnico
     * de la especialidad seleccionada.
     *
     * @param techSolutionsController Controlador
     * principal de la aplicación.
     * @throws IllegalArgumentException Si es nulo.
     */
    public void setTechSolutionsController(
            TechSolutionsController techSolutionsController) {

        if (techSolutionsController == null) {
            throw new IllegalArgumentException(
                    "El controlador de aplicación es obligatorio"
            );
        }

        this.techSolutionsController =
                techSolutionsController;

        consultarTecnico();
    }

    /**
     * Consulta el técnico correspondiente
     * a la especialidad seleccionada.
     *
     * Recibe un objeto InformacionTecnico
     * y actualiza los controles de la vista.
     *
     * La consulta se realiza mediante
     * TechSolutionsController.
     */
    @FXML
    private void consultarTecnico() {

        try {

            if (techSolutionsController == null) {
                throw new IllegalStateException(
                        "El controlador de aplicación "
                                + "no está configurado"
                );
            }

            EspecialidadTecnica especialidad =
                    cmbEspecialidades.getSelectionModel()
                            .getSelectedItem();

            if (especialidad == null) {
                throw new IllegalArgumentException(
                        "Debe seleccionar una especialidad"
                );
            }

            InformacionTecnico tecnico =
                    techSolutionsController.consultarTecnico(
                            especialidad
                    );

            mostrarInformacionTecnico(
                    especialidad,
                    tecnico
            );

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            limpiarInformacion();

            Ui.mostrarError(
                    "Error al consultar el técnico",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Presenta la información de un técnico
     * consultado mediante el proveedor externo.
     *
     * @param especialidad Especialidad consultada.
     * @param tecnico Información del técnico.
     */
    private void mostrarInformacionTecnico(
            EspecialidadTecnica especialidad,
            InformacionTecnico tecnico) {

        lblEspecialidad.setText(
                especialidad.name()
        );

        lblCodigoTecnico.setText(
                tecnico.codigoTecnico()
        );

        if (tecnico.disponible()) {

            lblDisponibilidad.setText(
                    "DISPONIBLE"
            );

        } else {

            lblDisponibilidad.setText(
                    "NO DISPONIBLE"
            );
        }

        lblCostoDesplazamiento.setText(
                Ui.formatoMoneda(
                        tecnico.costoDesplazamiento()
                )
        );
    }

    /**
     * Consulta los técnicos correspondientes
     * a todas las especialidades del sistema.
     *
     * Utiliza una lista de especialidades
     * y un recorrido tradicional.
     *
     * Los resultados se presentan
     * en un ListView de cadenas de texto.
     */
    @FXML
    private void consultarTodos() {

        try {

            if (techSolutionsController == null) {
                throw new IllegalStateException(
                        "El controlador de aplicación "
                                + "no está configurado"
                );
            }

            List<EspecialidadTecnica> especialidades =
                    new ArrayList<>();

            for (EspecialidadTecnica especialidad
                    : EspecialidadTecnica.values()) {

                especialidades.add(especialidad);
            }

            List<TecnicoService.ConsultaTecnico> consultas =
                    techSolutionsController.consultarTecnicos(
                            especialidades
                    );

            List<String> resultados =
                    new ArrayList<>();

            for (TecnicoService.ConsultaTecnico consulta
                    : consultas) {

                EspecialidadTecnica especialidad =
                        consulta.getEspecialidad();

                InformacionTecnico tecnico =
                        consulta.getInformacion();

                String estado = "NO DISPONIBLE";

                if (tecnico.disponible()) {
                    estado = "DISPONIBLE";
                }

                String resultado =
                        especialidad.name()
                                + " | "
                                + tecnico.codigoTecnico()
                                + " | "
                                + estado
                                + " | "
                                + Ui.formatoMoneda(
                                tecnico.costoDesplazamiento()
                        );

                resultados.add(resultado);
            }

            listaResultados.setItems(
                    FXCollections.observableArrayList(
                            resultados
                    )
            );

        } catch (IllegalArgumentException
                 | IllegalStateException excepcion) {

            Ui.mostrarError(
                    "Error al consultar los técnicos",
                    excepcion.getMessage()
            );
        }
    }

    /**
     * Actualiza la información cuando el usuario
     * cambia la especialidad seleccionada.
     *
     * Este método se asocia al evento onAction
     * del ComboBox en el archivo FXML.
     */
    @FXML
    private void seleccionarEspecialidad() {

        if (techSolutionsController != null) {
            consultarTecnico();
        }
    }

    /**
     * Actualiza las consultas realizadas
     * al proveedor de técnicos.
     *
     * Permite visualizar los resultados
     * disponibles en ese momento.
     */
    @FXML
    private void actualizarDatos() {

        if (techSolutionsController != null) {

            consultarTecnico();
            consultarTodos();
        }
    }

    /**
     * Limpia los campos utilizados
     * para presentar la información
     * de un técnico especializado.
     */
    private void limpiarInformacion() {

        lblEspecialidad.setText("-");

        lblCodigoTecnico.setText("-");

        lblDisponibilidad.setText("-");

        lblCostoDesplazamiento.setText(
                Ui.formatoMoneda(0.0)
        );
    }
}
