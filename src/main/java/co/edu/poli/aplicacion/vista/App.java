package co.edu.poli.aplicacion.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.BorderPane;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root =(BorderPane) FXMLLoader.load(getClass().getResource("/co/edu/poli/aplicacion/vista/formulario2.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/co/edu/poli/aplicacion/vista/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
