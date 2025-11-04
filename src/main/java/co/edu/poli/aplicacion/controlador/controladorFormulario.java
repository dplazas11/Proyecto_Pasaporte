package co.edu.poli.aplicacion.controlador;

import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.repositorio.OperacionesPasaporte;
import co.edu.poli.aplicacion.services.CreadorPasaporte;
import co.edu.poli.aplicacion.services.FactoriaPDiplomatica;
import co.edu.poli.aplicacion.services.FactoriaPOrdinaria;
import co.edu.poli.aplicacion.services.ObserverPublisher;
import co.edu.poli.aplicacion.services.Suscriber;
import co.edu.poli.aplicacion.services.SuscriberCancilleria;
import co.edu.poli.aplicacion.services.SuscriberPolicia;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private Button bmostrartodo;

    @FXML
    private TextField descr;

    @FXML
    private TextField fechExp;

    @FXML
    private TextField idPasaporte;

    @FXML
    private TextField pais;

    @FXML
    private TableView<Pasaporte> tablaDatos;

    @FXML
    private TableColumn<Pasaporte, String> tablaDescr;

    @FXML
    private TableColumn<Pasaporte, String> tablaFechExp;

    @FXML
    private TableColumn<Pasaporte, String> tablaId;

    @FXML
    private TableColumn<Pasaporte, String> tablaPais;

    @FXML
    private TableColumn<Pasaporte, String> tablaTitular;

    @FXML
    private TableColumn<Pasaporte, String> tablatipopasp;

    @FXML
    private ComboBox<String> tipoPas;

    @FXML
    private TextField titular;

    @FXML
    private Button barbolespgeo;
    
    @FXML
    private Button btneliminarsusc;

    @FXML
    private Button btnsuscribir;
    
    @FXML
    private CheckBox selectCansilleria;

    @FXML
    private CheckBox selectmigra;

    @FXML
    private CheckBox selectpolicia;

    @FXML
    public void initialize() {
        tipoPas.getItems().addAll("(Seleccione una opción)", "Ordinario", "Diplomatico");
        idPasaporte.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                filtrarId(null);
            }
            diseñoBoton(bactualizar);
            diseñoBoton(bbuscar);
            diseñoBoton(beliminar);
            diseñoBoton(bguardar);
            diseñoBoton(bmostrartodo);

            llenarTabla();

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
            ((PasaporteOrdinario) pasaporte).setMotivoViaje(descripcion);

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
                tipoPas.setValue("Diplomatico");
                descr.setText(((PasaporteDiplomatico) coincidencia).getMision()
                );
            } else if (coincidencia instanceof PasaporteOrdinario) {
                titular.setText("pendiente");
                fechExp.setText(coincidencia.getFechaExp());
                pais.setText("pendiente");
                tipoPas.setValue("Ordinario");
                descr.setText(((PasaporteOrdinario) coincidencia).getMotivoViaje());
            }
        } else {
            limpiarDatos(2);
        }

    }

    @FXML
    void clickActualizar(ActionEvent event) {
        // Obtener datos del formulario
        String id = idPasaporte.getText();
        String titularTxt = titular.getText();
        String fechaExpTxt = fechExp.getText();
        String paisTxt = this.pais.getText();
        String seleccionado = tipoPas.getValue();
        String descripcionTxt = descr.getText();

        if (seleccionado == null || seleccionado.isEmpty()) {
            crearAlerta("Seleccione un tipo de pasaporte válido");
            return;
        }

        OperacionesPasaporte repo = new OperacionesPasaporte();
        Pasaporte pasaporteExistente = repo.selectId(id);

        // Si el pasaporte existe
        if (pasaporteExistente != null) {
            boolean tipoCambia = false;

            if (pasaporteExistente instanceof PasaporteDiplomatico && seleccionado.equalsIgnoreCase("Ordinario")) {
                tipoCambia = true;
            } else if (pasaporteExistente instanceof PasaporteOrdinario && seleccionado.equalsIgnoreCase("Diplomatico")) {
                tipoCambia = true;
            }

            if (!tipoCambia) {
                // Tipo igual → solo actualizar
                pasaporteExistente.setTitular(null);
                pasaporteExistente.setPais(null);
                pasaporteExistente.setFechaExp(fechaExpTxt);

                if (pasaporteExistente instanceof PasaporteDiplomatico) {
                    ((PasaporteDiplomatico) pasaporteExistente).setMision(descripcionTxt);
                } else if (pasaporteExistente instanceof PasaporteOrdinario) {
                    ((PasaporteOrdinario) pasaporteExistente).setMotivoViaje(descripcionTxt);
                }

                String respuesta = repo.actualizar(pasaporteExistente);
                crearAlerta(respuesta);
                limpiarDatos(1);
                return;
            } else {
                // Tipo cambia → eliminar registros antiguos
                repo.eliminar(id);
            }
        }

        // Crear nuevo pasaporte según el tipo seleccionado
        Pasaporte nuevoPasaporte;

        if (seleccionado.equalsIgnoreCase("Diplomatico")) {
            CreadorPasaporte creador = new FactoriaPDiplomatica();
            nuevoPasaporte = creador.CrearPasaporte();
            ((PasaporteDiplomatico) nuevoPasaporte).setMision(descripcionTxt);
        } else if (seleccionado.equalsIgnoreCase("Ordinario")) {
            CreadorPasaporte creador = new FactoriaPOrdinaria();
            nuevoPasaporte = creador.CrearPasaporte();
            ((PasaporteOrdinario) nuevoPasaporte).setMotivoViaje(descripcionTxt);
        } else {
            crearAlerta("Tipo de pasaporte no válido");
            return;
        }

        // Asignar datos comunes
        nuevoPasaporte.setId(id);
        nuevoPasaporte.setTitular(null);
        nuevoPasaporte.setPais(null);
        nuevoPasaporte.setFechaExp(fechaExpTxt);

        // Insertar nuevo registro
        String respuestaInsert;
        if (nuevoPasaporte instanceof PasaporteDiplomatico) {
            respuestaInsert = repo.insertar((PasaporteDiplomatico) nuevoPasaporte);
        } else {
            respuestaInsert = repo.insertar((PasaporteOrdinario) nuevoPasaporte);
        }

        crearAlerta(respuestaInsert);
        limpiarDatos(1);
    }

    @FXML
    void clickBuscar(ActionEvent event) {

    }

    @FXML
    void clickMostrarTodo(ActionEvent event) {

        OperacionesPasaporte repo = new OperacionesPasaporte();
        ArrayList<Pasaporte> totalPasaportes = repo.selectAll();
        ObservableList<Pasaporte> datosTabla = FXCollections.observableArrayList(totalPasaportes);
        tablaDatos.setItems(datosTabla);
    }

    @FXML
    void clickEliminar(ActionEvent event) {
        String id = idPasaporte.getText();

        OperacionesPasaporte repo = new OperacionesPasaporte();
        String respuesta = repo.eliminar(id);
        crearAlerta(respuesta);
        limpiarDatos(1);

    }

    @FXML
    void clickcambiarventana(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/aplicacion/vista/formulario Arbol.fxml"));

        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(new Scene(root));
        nuevaVentana.show();

        // Cerrar la ventana actual
        Stage actual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        actual.close();

    }
    
    @FXML
    void clickSuscribir(ActionEvent event) {
        
        ObserverPublisher  publisher = new ObserverPublisher();
        String mensaje="";
        boolean seleccionados = false;
        
        if (selectCansilleria.isSelected()){
            Suscriber cancilleria = new SuscriberCancilleria();
            String respuesta1 = publisher.suscribir(cancilleria); 
            mensaje = respuesta1 + "\n";
            seleccionados = true;
        }
        if (selectpolicia.isSelected()){
            Suscriber policia = new SuscriberPolicia();
            String respuesta2 = publisher.suscribir(policia); 
            mensaje = mensaje + respuesta2 + "\n";
            seleccionados = true;
        }
        if (selectmigra.isSelected()){
            Suscriber migracion = new SuscriberCancilleria();
            String respuesta3 = publisher.suscribir(migracion); 
            mensaje = mensaje + respuesta3;
            seleccionados = true;
        }
        
        if (!seleccionados) crearAlerta("No se ha selecionado niguna opción.");        
        else crearAlerta(mensaje);

    }
    
    @FXML
    void clickEliminarSuscripcion(ActionEvent event) {
        
        ObserverPublisher  publisher = new ObserverPublisher();
        String mensaje="";
        boolean seleccionados = false;
        
        if (selectCansilleria.isSelected()){
            Suscriber cancilleria = new SuscriberCancilleria();
            String respuesta1 = publisher.desuscribir(cancilleria); 
            mensaje = respuesta1 + "\n";
            seleccionados = true;
        }
        if (selectpolicia.isSelected()){
            Suscriber policia = new SuscriberPolicia();
            String respuesta2 = publisher.desuscribir(policia); 
            mensaje = mensaje + respuesta2 + "\n";
            seleccionados = true;
        }
        if (selectmigra.isSelected()){
            Suscriber migracion = new SuscriberCancilleria();
            String respuesta3 = publisher.desuscribir(migracion); 
            mensaje = mensaje + respuesta3;
            seleccionados = true;
        }
        
        if (!seleccionados) crearAlerta("No se ha selecionado niguna opción");        
        else crearAlerta(mensaje);        

    }
    
    
    

    //METODOS EXTRA 
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
        // Área de texto expandible
        TextArea textArea = new TextArea(respuesta);
        textArea.setWrapText(true);
        textArea.setEditable(false);

        // Expandir automáticamente
        alerta.getDialogPane().setExpandableContent(textArea);
        alerta.getDialogPane().setExpanded(true);

        // Ajustar ancho de la ventana
        alerta.getDialogPane().setMinWidth(500);
        alerta.show();

    }

    void diseñoBoton(Button boton) {
        boton.setOnMousePressed(e -> boton.setScaleX(0.95)); // se hace más pequeño
        boton.setOnMouseReleased(e -> boton.setScaleX(1));
    }

    void llenarTabla() {
        tablaId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        tablaFechExp.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaExp()));

        // titular y país no están en BD, se usa valor fijo
        tablaTitular.setCellValueFactory(data -> new SimpleStringProperty("pendiente"));
        tablaPais.setCellValueFactory(data -> new SimpleStringProperty("pendiente"));

        // tipo pasaporte depende del instanceof
        tablatipopasp.setCellValueFactory(data -> {
            if (data.getValue() instanceof PasaporteOrdinario) {
                return new SimpleStringProperty("Ordinario");
            } else {
                return new SimpleStringProperty("Diplomático");
            }
        });

        // descripción depende de la subclase
        tablaDescr.setCellValueFactory(data -> {
            Pasaporte p = data.getValue();
            if (p instanceof PasaporteOrdinario) {
                PasaporteOrdinario o = (PasaporteOrdinario) p;
                return new SimpleStringProperty(o.getMotivoViaje());
            } else {
                PasaporteDiplomatico d = (PasaporteDiplomatico) p;
                return new SimpleStringProperty(d.getMision());
            }

        });
    }

}
