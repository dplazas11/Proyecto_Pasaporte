package co.edu.poli.aplicacion.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSupabase {

    // Instancia única del Singleton
    private static ConexionSupabase instance;

    // Credenciales de conexión
    private static final String URL = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:5432/postgres";
    private static final String USER = "postgres.xvmzjfegsbnyuxesymol";
    private static final String PASSWORD = "Pw8SFO00OJx5dJ7k";

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