module co.edu.poli.aplicacion {
    
    //requires org.postgresql.jdbc;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    

 
    
    opens co.edu.poli.aplicacion.vista to javafx.fxml;
    opens co.edu.poli.aplicacion.controlador to javafx.fxml;
    exports co.edu.poli.aplicacion.vista;
    exports co.edu.poli.aplicacion.controlador;
    
}
