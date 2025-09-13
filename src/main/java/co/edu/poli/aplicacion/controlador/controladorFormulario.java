package co.edu.poli.aplicacion.controlador;

import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.repositorio.OperacionesPasaporte;
import co.edu.poli.aplicacion.services.CreadorPasaporte;
import co.edu.poli.aplicacion.services.FactoriaPDiplomatica;
import co.edu.poli.aplicacion.services.FactoriaPOrdinaria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class controladorFormulario {

    @FXML
    private Button bactualizar;

    @FXML
    private Button bbuscar;

    @FXML
    private Button beliminar;

    @FXML
    private Button bguardar;

    @FXML
    private TextField descr;

    @FXML
    private TextField fechExp;

    @FXML
    private TextField idPasaporte;

    @FXML
    private TextField pais;

    @FXML
    private ComboBox<String> tipoPas;

    @FXML
    private TextField titular;

    @FXML
    public void initialize() {
        tipoPas.getItems().addAll("(Seleccione una opción)", "Ordinario", "Diplomatico");
        idPasaporte.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                filtrarId(null);
            }
        });
    }

    //metodos de la ventana 
    @FXML
    void clickGuardar(ActionEvent event) {
        String id = idPasaporte.getText();
        String nom = titular.getText();
        String fechaExp = fechExp.getText();
        String pais = this.pais.getText();
        String descripcion = descr.getText();

        CreadorPasaporte creador;
        Pasaporte pasaporte;
        OperacionesPasaporte repo = new OperacionesPasaporte();
        String seleccionado = tipoPas.getValue();

        if ("Ordinario".equalsIgnoreCase(seleccionado)) {
            creador = new FactoriaPOrdinaria();
            pasaporte = creador.CrearPasaporte();
            // llenar atributos desde el controlador
            pasaporte.setId(id);
            pasaporte.setTitular(null);
            pasaporte.setFechaExp(fechaExp);
            pasaporte.setPais(null);
            ((PasaporteOrdinario) pasaporte).setMotivoDeViaje(descripcion);

            String respuesta = repo.insertar(pasaporte);
            crearAlerta(respuesta);

        } else if ("Diplomatico".equalsIgnoreCase(seleccionado)) {
            creador = new FactoriaPDiplomatica();
            pasaporte = creador.CrearPasaporte();
            // llenar atributos desde el controlador
            pasaporte.setId(id);
            pasaporte.setTitular(null);
            pasaporte.setFechaExp(fechaExp);
            pasaporte.setPais(null);
            ((PasaporteDiplomatico) pasaporte).setMision(descripcion);

            String respuesta = repo.insertar(pasaporte);
            crearAlerta(respuesta);

            //JOptionPane.showMessageDialog(vista, respuesta);
        } else {
            crearAlerta("Seleccione un tipo de pasaporte válido");

        }

        limpiarDatos(1);

    }

    @FXML
    void filtrarId(ActionEvent event) {

        String id = idPasaporte.getText();
        OperacionesPasaporte repo = new OperacionesPasaporte();
        Pasaporte coincidencia = repo.selectId(id);

        if (coincidencia != null) {
            if (coincidencia instanceof PasaporteDiplomatico) {
                titular.setText("pendiente");
                fechExp.setText(coincidencia.getFechaExp());
                pais.setText("pendiente");
                tipoPas.setValue(tipoPas.getItems().get(2));
                descr.setText(((PasaporteDiplomatico) coincidencia).getMision());
            } else if (coincidencia instanceof PasaporteOrdinario) {
                titular.setText("pendiente");
                fechExp.setText(coincidencia.getFechaExp());
                pais.setText("pendiente");
                tipoPas.setValue(tipoPas.getItems().get(1));
                descr.setText(((PasaporteOrdinario) coincidencia).getMotivoDeViaje());
            }
        } else {
            limpiarDatos(2);
        }

    }

    @FXML
    void clickActualizar(ActionEvent event) {

    }

    @FXML
    void clickBuscar(ActionEvent event) {

    }

    @FXML
    void clickEliminar(ActionEvent event) {

    }

    void limpiarDatos(int tipo) {
        switch (tipo) {
            case 1:
                idPasaporte.setText("");
                titular.setText("");
                fechExp.setText("");
                pais.setText("");
                tipoPas.setValue(tipoPas.getItems().get(0));
                descr.setText("");
            case 2:
                titular.setText("");
                fechExp.setText("");
                pais.setText("");
                tipoPas.setValue(tipoPas.getItems().get(0));
                descr.setText("");
                break;
            default:
                throw new AssertionError();
        }

    }

    void crearAlerta(String respuesta) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setHeaderText("Estado");
        alerta.setContentText(respuesta);
        alerta.show();

    }

}
