
package co.edu.poli.aplicacion.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSupabase {

    // Instancia única del Singleton
    private static ConexionSupabase instance;

    // Conexión a la base de datos
    private Connection connection;

    // Constructor privado para implementar Singleton
    private ConexionSupabase() {
        try {

            // Constantes de conexión
            String URL = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:5432/postgres";
            String USER = "postgres.xvmzjfegsbnyuxesymol";
            String PASSWORD = "Pw8SFO00OJx5dJ7k";

            // Cargar driver PostgreSQL
            Class.forName(
                    "org.postgresql.Driver");

            // Establecer la conexión
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println(
                    "Conexion exitosa a Supabase (Singleton)");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion en constructor: " + e.getMessage());
        }
    }

    // Método para obtener la instancia única (Thread-safe)
    public static synchronized ConexionSupabase getInstance() {
        if (instance == null) {
            instance = new ConexionSupabase();
        }
        return instance;
    }

    // Método para obtener la conexión
    public Connection getConnection() {
        return this.connection;
    }
}
