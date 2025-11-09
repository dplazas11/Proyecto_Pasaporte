package co.edu.poli.aplicacion.controlador;

import co.edu.poli.aplicacion.services.AdaptadorCiudad;
import co.edu.poli.aplicacion.services.CompEspacioGeografico;
import co.edu.poli.aplicacion.services.CompositeRegion;
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
    private TextField Dept;

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
        CompositeRegion raiz = new CompositeRegion("R1");

        CompositeRegion region1 = new CompositeRegion("R2");
        CompositeRegion region2 = new CompositeRegion("R3");
        CompositeRegion region3 =new CompositeRegion("R4");

        CompositeRegion dept1 = new CompositeRegion("Cundinamarca");
        dept1.add(new AdaptadorCiudad("Bogotá"));
        dept1.add(new AdaptadorCiudad("Madrid"));
        dept1.add(new AdaptadorCiudad("Chia"));

        CompositeRegion dept2 = new CompositeRegion("Valle del Cauca");

        dept2.add(new AdaptadorCiudad("Cali"));
        dept2.add(new AdaptadorCiudad("Palmira"));
        dept2.add(new AdaptadorCiudad("Cartago "));
        
        CompositeRegion dept3 = new CompositeRegion("Casanare");

        dept3.add(new AdaptadorCiudad("Yopal"));
        dept3.add(new AdaptadorCiudad("Aguazul"));
        dept3.add(new AdaptadorCiudad("Mani"));
        
        CompositeRegion dept4 = new CompositeRegion("C1");
        CompositeRegion dept5 = new CompositeRegion("C2");
        
        
        region1.add(region2);
        region2.add(region3);
        region3.add(dept4);
        region3.add(dept5);
        
        
        raiz.add(region1);
        

        TAContenedor.setText(raiz.getNombre());
        //Ciudad.appendText(salida);
    }

    @FXML
    void clickvolver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/aplicacion/vista/formulario2.fxml"));

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
