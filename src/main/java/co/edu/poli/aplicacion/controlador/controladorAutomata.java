package co.edu.poli.aplicacion.controlador;

import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.services.AdaptadorPais;
import co.edu.poli.aplicacion.services.Estado;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;

public class controladorAutomata {

    @FXML
    private TextField idPais;

    @FXML
    private TextField nomPais;

    @FXML
    private TextArea TAContenedor;

    @FXML
    private Button bguardar;

    @FXML
    private Button btnEstadoNormal;

    @FXML
    private Button btnEstadoRevision;

    @FXML
    private Button btncambiarestados;

    @FXML
    private Button btnfronteracerrada;

    @FXML
    private Button btnsolicitudvisa;

    @FXML
    private Button bvolver;

    //CREACION DE PAIS 
    String id_pais;
    String pais;

    Pais paisRecibido = new Pais(id_pais, pais, null);
    AdaptadorPais adaptpais = new AdaptadorPais(paisRecibido);

    @FXML
    void clicKEstadoNormal(ActionEvent event) {

    }

    @FXML
    void clickCambiarEstados(ActionEvent event) {

    }

    @FXML
    void clickEstadoRevision(ActionEvent event) {

        String actual = adaptpais.getEstadoActualNombre();
        String elecccion = "estado revision";
        List<String> posibles = adaptpais.posiblesTransiciones(actual);
        adaptpais.setDireccion(actual, elecccion);
        adaptpais.avanzar();

        TAContenedor.setText("El estado inicial es " + adaptpais.getEstadoActualNombre() + "\n" + "Te puedes mover a los siguientes estados: " + adaptpais.posiblesTransiciones(adaptpais.getEstadoActualNombre()));

    }

    @FXML
    void clickFronteraCerrada(ActionEvent event) {

    }

    @FXML
    void clickGuardar(ActionEvent event) {

        id_pais = idPais.getText();
        pais = nomPais.getText();

        for (String estado : Arrays.asList("estado normal", "estado revision", "solicitud visa", "frontera cerrada")) {
            String entrada = pedirEstados(estado).trim();
            List<String> destinos = entrada.isEmpty() ? new ArrayList<>()
                    : Arrays.asList(entrada.toUpperCase().split(","));

            adaptpais.agregarTransiciones(estado, destinos);
        }

        TAContenedor.setText("El estado inicial es " + adaptpais.getEstadoActualNombre() + "\n" + "Te puedes mover a los siguientes estados: " + adaptpais.posiblesTransiciones(adaptpais.getEstadoActualNombre()));

    }

    @FXML
    void clickMostrarTodo(ActionEvent event) {

    }

    @FXML
    void clickSolicitudVisa(ActionEvent event) {

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

    public String pedirEstados(String estado) {

        // Crear el diálogo
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Entrada de texto");
        dialogo.setHeaderText("Por favor ingresa a donde se dirige el estado: " + estado + "\n"
                + "Puede escoger entre estado normal, estado revision, solicitud visa, frontera cerrada pero no puede escoger el estado " + estado + " y deben ir separados por comas");
        //dialogo.setContentText("Nombre:");

        // Mostrar el diálogo y esperar la respuesta
        Optional<String> resultado = dialogo.showAndWait();

        // Verificar si el usuario ingresó algo
        if (resultado.isPresent()) {
            String textoIngresado = resultado.get();
            return textoIngresado;
        } else {
            System.out.println("Usuario canceló el diálogo");
        }
        return "";
    }

}
