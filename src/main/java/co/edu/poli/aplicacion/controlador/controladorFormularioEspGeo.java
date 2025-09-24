package co.edu.poli.aplicacion.controlador;

import co.edu.poli.aplicacion.services.AdaptadorCiudad;
import co.edu.poli.aplicacion.services.RegionComposite;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controladorFormularioEspGeo {

    @FXML
    private TextField Ciudad;

    @FXML
    private TextField Region;

    @FXML
    private Button bbuscar;

    @FXML
    private Button beliminar;

    @FXML
    private Button bguardar;

    @FXML
    private Button bmostrartodo;

    @FXML
    private Button bvolver;

    @FXML
    private TextArea TAContenedor;

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
    void clickMostrarTodo(ActionEvent event) {
           RegionComposite raiz = new RegionComposite();
        
        
        
        RegionComposite nivel1 = new RegionComposite("nivel 1");
        nivel1.add(new AdaptadorCiudad("a"));
        nivel1.add(new AdaptadorCiudad("b"));
        nivel1.add(new AdaptadorCiudad("c"));

        // Nivel 2
        
        RegionComposite nivel2 = new RegionComposite("nivel 2");
        nivel2.add(new AdaptadorCiudad("a"));
        nivel2.add(new AdaptadorCiudad("b"));
        nivel2.add(new AdaptadorCiudad("c"));
        
        raiz.add(nivel1);
        raiz.add(nivel2);

        String salida = raiz.getNombre();
        TAContenedor.setText(salida);
        //Ciudad.appendText(salida);
    }

    @FXML
    void clickvolver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/aplicacion/vista/formulario.fxml"));

        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(new Scene(root));
        nuevaVentana.show();

        // Cerrar la ventana actual
        Stage actual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        actual.close();
    }

    @FXML
    void filtrarId(ActionEvent event) {

    }

}
