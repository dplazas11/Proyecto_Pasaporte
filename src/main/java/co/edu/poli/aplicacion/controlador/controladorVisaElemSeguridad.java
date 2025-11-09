package co.edu.poli.aplicacion.controlador;

import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.Visa;
import co.edu.poli.aplicacion.services.AdaptadorElemSeguridad;
import co.edu.poli.aplicacion.services.AdaptadorVisa;
import co.edu.poli.aplicacion.services.Comando;
import co.edu.poli.aplicacion.services.ComandoEmitirVisa;
import co.edu.poli.aplicacion.services.ComandoInvocador;
import co.edu.poli.aplicacion.services.ComandoSuspenderVisa;
import co.edu.poli.aplicacion.services.StrategyBiometrico;
import co.edu.poli.aplicacion.services.StrategyBlockChain;
import co.edu.poli.aplicacion.services.StrategyChip;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controladorVisaElemSeguridad {

    @FXML
    private Button btnEmitirVisa;

    @FXML
    private Button btnSuspenderVisa;

    @FXML
    private Button btnvolver;

    @FXML
    private TextField descrElem;

    @FXML
    private TextField detalleElem;

    @FXML
    private TextField hora;

    @FXML
    private TextField idElemSeg;

    @FXML
    private TextField idVisa;

    @FXML
    private TextField multEntry;

    @FXML
    private TextField paisOrigen;

    @FXML
    private ChoiceBox<String> tipoElem;

    //Variables globales
    AdaptadorVisa adapVisa;
    private static final ComandoInvocador gestor = new ComandoInvocador();
    private AdaptadorElemSeguridad adapElemSeg = new AdaptadorElemSeguridad();
    Comando emitir;
    Comando Cancelar;

    @FXML
    public void initialize() {
        tipoElem.getItems().addAll("(Seleccione una opción)", "Biometrico", "Chip", "Blockchain");

        idVisa.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                filtrarIdVisa(null);
            }
        });
    }

    @FXML
    void clickEmitirVisa(ActionEvent event) {

        String id_Visa = idVisa.getText();
        String pais = paisOrigen.getText();
        int MultiEntry = Integer.parseInt(multEntry.getText());
        String hora1 = hora.getText();

        Pais objPais = new Pais(null, pais, null);
        Visa visa = new Visa(id_Visa, objPais, MultiEntry, null, hora1);
        adapVisa = new AdaptadorVisa(visa);

        emitir = new ComandoEmitirVisa(adapVisa);
        String mensaje = gestor.ejecutarComando(emitir, adapVisa);
        crearAlerta(mensaje);
        limpiarDatos(1);

    }

    @FXML
    void clickSuspenderVisa(ActionEvent event) {

        String id_Visa = idVisa.getText();
        String pais = paisOrigen.getText();
        int MultiEntry = Integer.parseInt(multEntry.getText());
        String hora1 = hora.getText();

        Pais objPais = new Pais(null, pais, null);
        Visa visa = new Visa(id_Visa, objPais, MultiEntry, null, hora1);
        adapVisa = new AdaptadorVisa(visa);
        Cancelar = new ComandoSuspenderVisa(adapVisa);

        String mensaje = gestor.ejecutarComando(Cancelar, adapVisa);
        crearAlerta(mensaje);
        limpiarDatos(1);

    }

    @FXML
    void clickVolver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/aplicacion/vista/formulario2.fxml"));

        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(new Scene(root));
        nuevaVentana.show();

        // Cerrar la ventana actual
        Stage actual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        actual.close();

    }

    @FXML
    void clickCrearElemSeg(ActionEvent event) {
        
        String id_ElemSeg = idElemSeg.getText();
        String tipo = tipoElem.getValue();
        String descrp = descrElem.getText();
        String detlle = detalleElem.getText();
        
        if ("Biometrico".equalsIgnoreCase(tipo)){
            adapElemSeg.setEstrategia(new StrategyBiometrico());
            String mensaje = adapElemSeg.crearElemSeg(id_ElemSeg, tipo, descrp, detlle);
            crearAlerta(mensaje);
        }else if ("Chip".equalsIgnoreCase(tipo)){
            adapElemSeg.setEstrategia(new StrategyChip());
            String mensaje = adapElemSeg.crearElemSeg(id_ElemSeg, tipo, descrp, detlle);
            crearAlerta(mensaje);
            
        }else if ("Blockchain".equalsIgnoreCase(tipo)){
            adapElemSeg.setEstrategia(new StrategyBlockChain());
            String mensaje = adapElemSeg.crearElemSeg(id_ElemSeg, tipo, descrp, detlle);
            crearAlerta(mensaje);
        }
        else {
            crearAlerta("Debe escoger un tipo de Elemento de Seguridad");
        }

    }

    @FXML
    void filtrarIdVisa(ActionEvent event) {

        String id = idVisa.getText();
        AdaptadorVisa adapVisa1 = gestor.getVisa(id);

        if (adapVisa1 != null) {
            paisOrigen.setText(adapVisa1.getPais());
            multEntry.setText(String.valueOf(adapVisa1.getMultEntry()));
            hora.setText(adapVisa1.getHora());
        }

    }

    void limpiarDatos(int tipo) {
        switch (tipo) {
            case 1:
                idVisa.setText("");
                paisOrigen.setText("");
                multEntry.setText("");
                hora.setText("");
            case 2:
                idElemSeg.setText("");
                tipoElem.setValue(tipoElem.getItems().get(0));
                descrElem.setText("");
                detalleElem.setText("");
                break;
            default:
                throw new AssertionError();
        }

    }

    void crearAlerta(String respuesta) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Estado");
        alerta.setContentText(respuesta); // directamente en el cuerpo
        alerta.getDialogPane().setMinWidth(500);
        alerta.showAndWait();

    }

    void diseñoBoton(Button boton) {
        boton.setOnMousePressed(e -> boton.setScaleX(0.95)); // se hace más pequeño
        boton.setOnMouseReleased(e -> boton.setScaleX(1));
    }

}
