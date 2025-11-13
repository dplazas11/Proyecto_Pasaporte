package co.edu.poli.aplicacion.controlador;

import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.modelo.Titular;
import co.edu.poli.aplicacion.repositorio.OperacionesPasaporte;
import co.edu.poli.aplicacion.services.AdaptadorPasaporte;
import co.edu.poli.aplicacion.services.ConcreteMediator;
import co.edu.poli.aplicacion.services.CorAprobador;
import co.edu.poli.aplicacion.services.CreadorPasaporte;
import co.edu.poli.aplicacion.services.FactoriaPDiplomatica;
import co.edu.poli.aplicacion.services.FactoriaPOrdinaria;
import co.edu.poli.aplicacion.services.MementoCaretaker;
import co.edu.poli.aplicacion.services.ObserverPublisher;
import co.edu.poli.aplicacion.services.Suscriber;
import co.edu.poli.aplicacion.services.SuscriberCancilleria;
import co.edu.poli.aplicacion.services.SuscriberMigracion;
import co.edu.poli.aplicacion.services.SuscriberPolicia;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
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

    //ATRIBUTOS DEL PASAPORTE
    @FXML
    private TextField DocTitular;//OK

    @FXML
    private TextField descr;//OK 

    @FXML
    private TextField fechExp;//OK 

    @FXML
    private TextField idPasaporte;//OK 

    @FXML
    private TextField pais;//ok 

    @FXML
    private TextField titular;//ok 

    //BOTONES
    @FXML
    private Button bactualizar;//OK 

    @FXML
    private Button bbuscar;//OK 

    @FXML
    private Button beliminar;//OK 

    @FXML
    private Button bguardar;//OK 

    @FXML
    private Button bmostrartodo;

    @FXML
    private Button barbolespgeo;//OK

    @FXML
    private Button btnAutomata;//OK 

    @FXML
    private Button btneliminarsusc;//OK 

    @FXML
    private Button btnsuscribir;//OK 

    @FXML
    private Button btnRestaurar1;//ok 

    @FXML
    private Button btnVisaElemSeg;//OK

    @FXML
    private Button btnenviarcancilleria;//OK

    @FXML
    private Button btnenviarmigracion;//OK

    @FXML
    private Button btnenviarpolicia;//OK

    //CAMPOS DE LA TABLA 
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

    //COMBO BOX TIPO DE PASAPORTE
    @FXML
    private ComboBox<String> tipoPas;//ok 

    //SUSCRIPTORES
    @FXML
    private CheckBox selectCancilleria;//ok 

    @FXML
    private CheckBox selectmigra;//ok 

    @FXML
    private CheckBox selectpolicia;//ok 

    @FXML
    private Button btnverlista;//OK 

    @FXML
    private TextArea mensajeMediator;//OK 

    //INFORMACION ESTADOS
    @FXML
    private ComboBox<Integer> estados;//OK 

    @FXML
    private TextArea textAreaMementos;//ok 

    //OBJETOS GLOBALES 
    public static final ObserverPublisher publisher = new ObserverPublisher();
    public static final MementoCaretaker gestor = new MementoCaretaker();
    CorAprobador AprobadorCancilleria = new SuscriberCancilleria();
    CorAprobador AprobadorPolicia = new SuscriberPolicia();
    CorAprobador AprobadorMigracion = new SuscriberMigracion();
    ConcreteMediator mediator = new ConcreteMediator();

    @FXML
    public void initialize() {
        tipoPas.getItems().addAll("(Seleccione una opción)", "Ordinario", "Diplomatico");

        diseñoBoton(bactualizar);
        diseñoBoton(bbuscar);
        diseñoBoton(beliminar);
        diseñoBoton(bguardar);
        diseñoBoton(btnRestaurar1);
        diseñoBoton(btnsuscribir);
        diseñoBoton(btneliminarsusc);
        diseñoBoton(btnverlista);

        idPasaporte.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                filtrarId(null);
            }
        });

        //llenarTabla();
    }

    //metodos de la ventana 
    @FXML
    void clickGuardar(ActionEvent event) {

        String notificacion = publisher.notificarSuscribers();

        if (AprobadorCancilleria == null) {
            crearAlerta("Debe suscribir las entidades antes de guardar el pasaporte.");
            return;
        }

        String id = idPasaporte.getText();
        String nom = titular.getText();
        String docTitular = DocTitular.getText();
        String fechaExp = fechExp.getText();
        String pais = this.pais.getText();
        String descripcion = descr.getText();
        String seleccionado = tipoPas.getValue();

        Map<String, Object> estadoAprobacion = AprobadorCancilleria.aprobar(docTitular);
        boolean estado = (boolean) estadoAprobacion.get("estado");

        if (estado) {

            CreadorPasaporte creador;
            Pasaporte pasaporte;
            OperacionesPasaporte repo = new OperacionesPasaporte();

            if ("Ordinario".equalsIgnoreCase(seleccionado)) {
                creador = new FactoriaPOrdinaria();
                pasaporte = creador.CrearPasaporte();

                // llenar atributos desde el controlador
                pasaporte.setId(id);
                Titular t = new Titular(null, nom, null);
                pasaporte.setTitular(t);
                pasaporte.setFechaExp(fechaExp);
                Pais p = new Pais(null, pais, null);
                pasaporte.setPais(p);
                ((PasaporteOrdinario) pasaporte).setMotivoViaje(descripcion);

                //Crear AdaptadorPasaporte y Memento
                AdaptadorPasaporte AdapPasaporte = new AdaptadorPasaporte(pasaporte);
                int indice = gestor.agregarMemento(AdapPasaporte.guardar());
                estados.getItems().add(indice);
                textAreaMementos.setText(gestor.verElementos());

                //Enviar pasaporte al repositorio 
                String respuesta = repo.insertar(pasaporte);
                Object respAprobacion = estadoAprobacion.get("mensaje");
                crearAlerta("Respuesta repositorio:" + respuesta + "\n" + (String) respAprobacion + "\n" + notificacion);

            } else if ("Diplomatico".equalsIgnoreCase(seleccionado)) {
                creador = new FactoriaPDiplomatica();
                pasaporte = creador.CrearPasaporte();
                // llenar atributos desde el controlador
                pasaporte.setId(id);
                Titular t = new Titular(null, nom, null);
                pasaporte.setTitular(t);
                pasaporte.setFechaExp(fechaExp);
                Pais p = new Pais(null, pais, null);
                pasaporte.setPais(p);
                ((PasaporteDiplomatico) pasaporte).setMision(descripcion);

                //Crear AdaptadorPasaporte y Memento
                AdaptadorPasaporte AdapPasaporte = new AdaptadorPasaporte(pasaporte);
                int indice = gestor.agregarMemento(AdapPasaporte.guardar());
                estados.getItems().add(indice);
                textAreaMementos.setText(gestor.verElementos());

                //Enviar pasaporte al repositorio
                String respuesta = repo.insertar(pasaporte);
                Object respAprobacion = estadoAprobacion.get("mensaje");
                crearAlerta(respuesta + "\n" + (String) respAprobacion + "\n" + notificacion);

                //JOptionPane.showMessageDialog(vista, respuesta);
            } else {
                crearAlerta("Seleccione un tipo de pasaporte válido");

            }

            limpiarDatos(1);

        } else {
            Object resp = estadoAprobacion.get("mensaje");
            crearAlerta((String) resp);
        }

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

        String notificacion = publisher.notificarSuscribers();

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
                Titular t = new Titular(null, titularTxt, null);
                Pais p = new Pais(null, paisTxt, null);
                pasaporteExistente.setTitular(t);
                pasaporteExistente.setPais(p);
                pasaporteExistente.setFechaExp(fechaExpTxt);

                if (pasaporteExistente instanceof PasaporteDiplomatico) {
                    ((PasaporteDiplomatico) pasaporteExistente).setMision(descripcionTxt);
                } else if (pasaporteExistente instanceof PasaporteOrdinario) {
                    ((PasaporteOrdinario) pasaporteExistente).setMotivoViaje(descripcionTxt);
                }

                //CREAR ADAPTADOR Y MEMENTO
                AdaptadorPasaporte AdapPasaporte = new AdaptadorPasaporte(pasaporteExistente);
                int indice = gestor.agregarMemento(AdapPasaporte.guardar());
                estados.getItems().add(indice);
                textAreaMementos.setText(gestor.verElementos());

                //ENVIAR PASAPORTE AL REPOSITORIO
                String respuesta = repo.actualizar(pasaporteExistente);                
                mediator.agregarComponente(AdapPasaporte);
                String mediatorRespuesta = AdapPasaporte.procesar(mensajeMediator.getText());
                crearAlerta(respuesta + "\n" + notificacion + "\n" + mediatorRespuesta);
                
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
        Titular ti = new Titular(null, titularTxt, null);
        Pais pa = new Pais(null, paisTxt, null);
        nuevoPasaporte.setId(id);
        nuevoPasaporte.setTitular(ti);
        nuevoPasaporte.setPais(pa);
        nuevoPasaporte.setFechaExp(fechaExpTxt);

        // Insertar nuevo registro
        String respuestaInsert;
        if (nuevoPasaporte instanceof PasaporteDiplomatico) {
            respuestaInsert = repo.insertar((PasaporteDiplomatico) nuevoPasaporte);
        } else {
            respuestaInsert = repo.insertar((PasaporteOrdinario) nuevoPasaporte);
        }

        //CREAR ADAPTADOR Y MEMENTO 
        AdaptadorPasaporte AdapPasaporte = new AdaptadorPasaporte(nuevoPasaporte);
        int indice = gestor.agregarMemento(AdapPasaporte.guardar());
        estados.getItems().add(indice);
        textAreaMementos.setText(gestor.verElementos());

        mediator.agregarComponente(AdapPasaporte);
        String mediatorRespuesta = AdapPasaporte.procesar(mensajeMediator.getText());
        crearAlerta(respuestaInsert + "\n" + notificacion + "\n" + mediatorRespuesta);

        limpiarDatos(1);
    }

    @FXML
    void clickRestaurar(ActionEvent event) {
        Integer index = estados.getValue();
        int indice = index;
        AdaptadorPasaporte pasapRestaurado = new AdaptadorPasaporte();
        Pasaporte p = pasapRestaurado.restaurar(gestor.obtenerMemento(indice));

        if (p instanceof PasaporteDiplomatico) {
            idPasaporte.setText(p.getId());
            titular.setText(p.getTitular().getNombre());
            fechExp.setText(p.getFechaExp());
            pais.setText(p.getPais().getNombre());
            tipoPas.setValue("Diplomatico");
            descr.setText(((PasaporteDiplomatico) p).getMision());

        } else if (p instanceof PasaporteOrdinario) {
            idPasaporte.setText(p.getId());
            titular.setText(p.getTitular().getNombre());
            fechExp.setText(p.getFechaExp());
            pais.setText(p.getPais().getNombre());
            tipoPas.setValue("Ordinario");
            descr.setText(((PasaporteOrdinario) p).getMotivoViaje()
            );

        }
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
    void clickcambiarventana1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/aplicacion/vista/formulario Arbol.fxml"));

        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(new Scene(root));
        nuevaVentana.show();

        // Cerrar la ventana actual
        Stage actual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        actual.close();

    }

    @FXML
    void clickcambiarventana2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/aplicacion/vista/formularioVisayBiometrico.fxml"));

        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(new Scene(root));
        nuevaVentana.show();

        // Cerrar la ventana actual
        Stage actual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        actual.close();

    }

    @FXML
    void clickSuscribir(ActionEvent event) {

        String mensaje = "";
        boolean seleccionados = false;

        AprobadorCancilleria = null;
        AprobadorPolicia = null;
        AprobadorMigracion = null;

        if (selectCancilleria.isSelected()) {
            Suscriber cancilleria = new SuscriberCancilleria();
            AprobadorCancilleria = new SuscriberCancilleria();
            mediator.agregarComponente(AprobadorCancilleria);
            String respuesta1 = publisher.suscribir(cancilleria);
            mensaje = respuesta1 + "\n";
            seleccionados = true;
        }
        if (selectpolicia.isSelected()) {
            Suscriber policia = new SuscriberPolicia();
            AprobadorPolicia = new SuscriberPolicia();
            mediator.agregarComponente(AprobadorPolicia);
            String respuesta2 = publisher.suscribir(policia);
            mensaje = mensaje + respuesta2 + "\n";
            seleccionados = true;
        }
        if (selectmigra.isSelected()) {
            Suscriber migracion = new SuscriberMigracion();
            AprobadorMigracion = new SuscriberMigracion();
            mediator.agregarComponente(AprobadorMigracion);
            String respuesta3 = publisher.suscribir(migracion);
            mensaje = mensaje + respuesta3;
            seleccionados = true;
        }

        if (!seleccionados) {
            crearAlerta("No se ha selecionado niguna opción.");
            return;
        }

        if (AprobadorCancilleria != null && AprobadorPolicia != null) {
            AprobadorCancilleria.setSiguiente(AprobadorPolicia);
        }
        if (AprobadorPolicia != null && AprobadorMigracion != null) {
            AprobadorPolicia.setSiguiente(AprobadorMigracion);
        }
        crearAlerta(mensaje);

    }

    @FXML
    void clickEliminarSuscripcion(ActionEvent event) {

        String mensaje = "";
        boolean seleccionados = false;

        if (selectCancilleria.isSelected()) {
            Suscriber cancilleria = new SuscriberCancilleria();
            String respuesta1 = publisher.desuscribir(cancilleria);
            mensaje = respuesta1 + "\n";
            seleccionados = true;
        }
        if (selectpolicia.isSelected()) {
            Suscriber policia = new SuscriberPolicia();
            String respuesta2 = publisher.desuscribir(policia);
            mensaje = mensaje + respuesta2 + "\n";
            seleccionados = true;
        }
        if (selectmigra.isSelected()) {
            Suscriber migracion = new SuscriberMigracion();
            String respuesta3 = publisher.desuscribir(migracion);
            mensaje = mensaje + respuesta3;
            seleccionados = true;
        }

        if (!seleccionados) {
            crearAlerta("No se ha selecionado niguna opción");
        } else {
            crearAlerta(mensaje);
        }

    }

    @FXML
    void clickverlistasuscriptores(ActionEvent event) {
        crearAlerta(publisher.verLista());
    }

    @FXML
    void clickEnviarCancilleria(ActionEvent event) {

        String mensaje = AprobadorCancilleria.procesar(mensajeMediator.getText());
        crearAlerta(mensaje);

    }

    @FXML
    void clickEnviarMigracion(ActionEvent event) {
        String mensaje = AprobadorMigracion.procesar(mensajeMediator.getText());
        crearAlerta(mensaje);

    }

    @FXML
    void clickEnviarPolicia(ActionEvent event) {
        String mensaje = AprobadorPolicia.procesar(mensajeMediator.getText());
        crearAlerta(mensaje);

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
                DocTitular.setText("");
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
        alerta.setContentText(respuesta); // directamente en el cuerpo
        alerta.getDialogPane().setMinWidth(500);
        alerta.showAndWait();

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
