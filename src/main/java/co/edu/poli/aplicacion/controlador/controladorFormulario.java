
package co.edu.poli.aplicacion.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public void initialize() {
        tipoPas.getItems().addAll("Ordinario", "Diplomatico");
    }

    @FXML
    private TextField titular;

    @FXML
    void clickActualizar(ActionEvent event) {

    }

    @FXML
    void clickBuscar(ActionEvent event) {

    }

    @FXML
    void clickEliminar(ActionEvent event) {

    }

    @FXML
    void clickGuardar(ActionEvent event) {

    }

    @FXML
    void filtrarId(ActionEvent event) {

    }

}
