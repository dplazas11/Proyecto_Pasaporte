package co.edu.poli.aplicacion.controlador;

import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.repositorio.OperacionesPasaporte;
import co.edu.poli.aplicacion.services.CreadorPasaporte;
import co.edu.poli.aplicacion.services.FactoriaPDiplomatica;
import co.edu.poli.aplicacion.services.FactoriaPOrdinaria;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

            tablaId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tablaTitular.setCellValueFactory(new PropertyValueFactory<>("titular"));
            tablaPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
            tablaFechExp.setCellValueFactory(new PropertyValueFactory<>("fechaExp"));
            tablaDescr.setCellValueFactory(new PropertyValueFactory<>("tipopasaporte"));
            tablaDescr.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

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
            ((PasaporteOrdinario) pasaporte).setTipoPasaporte(seleccionado);
            ((PasaporteOrdinario) pasaporte).setDescripcion(descripcion);

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
            ((PasaporteDiplomatico) pasaporte).setTipoPasaporte(seleccionado);
            ((PasaporteDiplomatico) pasaporte).setDescripcion(descripcion);

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
                descr.setText(((PasaporteDiplomatico) coincidencia).getDescripcion());
            } else if (coincidencia instanceof PasaporteOrdinario) {
                titular.setText("pendiente");
                fechExp.setText(coincidencia.getFechaExp());
                pais.setText("pendiente");
                tipoPas.setValue("Ordinario");
                descr.setText(((PasaporteOrdinario) coincidencia).getDescripcion());
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

        // Si el pasaporte ya existe
        if (pasaporteExistente != null) {
            // Verificar si cambió de tipo
            boolean cambioTipo = false;
            if (pasaporteExistente instanceof PasaporteDiplomatico && seleccionado.equalsIgnoreCase("Ordinario")) {
                cambioTipo = true;
            } else if (pasaporteExistente instanceof PasaporteOrdinario && seleccionado.equalsIgnoreCase("Diplomatico")) {
                cambioTipo = true;
            }

            if (cambioTipo) {
                // Borrar el registro existente antes de crear uno nuevo
                repo.eliminar(id);
                pasaporteExistente = null; // forzar creación nueva
            }
        }

        // Crear el pasaporte según el tipo seleccionado
        Pasaporte nuevoPasaporte;
        if (seleccionado.equalsIgnoreCase("Diplomatico")) {
            CreadorPasaporte creador = new FactoriaPDiplomatica();
            nuevoPasaporte = creador.CrearPasaporte();
            ((PasaporteDiplomatico) nuevoPasaporte).setTipoPasaporte(seleccionado);
            ((PasaporteDiplomatico) nuevoPasaporte).setDescripcion(descripcionTxt);
        } else if (seleccionado.equalsIgnoreCase("Ordinario")) {
            CreadorPasaporte creador = new FactoriaPOrdinaria();
            nuevoPasaporte = creador.CrearPasaporte();
            ((PasaporteOrdinario) nuevoPasaporte).setTipoPasaporte(seleccionado);
            ((PasaporteOrdinario) nuevoPasaporte).setDescripcion(descripcionTxt);
        } else {
            crearAlerta("Tipo de pasaporte no válido");
            return;
        }

        // Asignar datos comunes
        nuevoPasaporte.setId(id);
        nuevoPasaporte.setTitular(null);
        nuevoPasaporte.setFechaExp(fechaExpTxt);
        nuevoPasaporte.setPais(null);

        // Guardar o actualizar
        String respuesta = repo.actualizar(nuevoPasaporte);
        crearAlerta(respuesta);

        // Limpiar formulario
        limpiarDatos(1);

    }

    @FXML
    void clickBuscar(ActionEvent event) {

    }

    @FXML
    void clickMostrarTodo(ActionEvent event) {

        OperacionesPasaporte repo = new OperacionesPasaporte();
        ArrayList<Pasaporte> totalPasaportes = new ArrayList<>();
        totalPasaportes = repo.selectAll();
        ObservableList<Pasaporte> lista = FXCollections.observableArrayList(totalPasaportes);
        tablaDatos.setItems(lista);

    }

    @FXML
    void clickEliminar(ActionEvent event) {
        String id = idPasaporte.getText();

        OperacionesPasaporte repo = new OperacionesPasaporte();
        String respuesta = repo.eliminar(id);
        crearAlerta(respuesta);
        limpiarDatos(1);

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

}
