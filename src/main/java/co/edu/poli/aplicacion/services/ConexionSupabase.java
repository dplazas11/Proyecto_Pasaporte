package co.edu.poli.aplicacion.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConexionSupabase {

    // Instancia única del Singleton
    private static ConexionSupabase instance;
    
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    static {
        Properties props = new Properties();
        try  {
            props.load(ConexionSupabase.class.getClassLoader().getResourceAsStream("conexionbd.properties"));
                       
            URL = props.getProperty("db.url");
            USER = System.getenv("usuarioSB");
            PASSWORD = System.getenv("passwordSB");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Constructor privado (patrón Singleton)
    private ConexionSupabase() {
        try {
            Class.forName("org.postgresql.Driver"); // carga driver una sola vez
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver PostgreSQL", e);
        }
    }

    // Método para obtener la instancia única
    public static synchronized ConexionSupabase getInstance() {
        if (instance == null) {
            instance = new ConexionSupabase();
        }
        return instance;
    }

    // 👉 Devuelve una conexión NUEVA cada vez que se llama
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
