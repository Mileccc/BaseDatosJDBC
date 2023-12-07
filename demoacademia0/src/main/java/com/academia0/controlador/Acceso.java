package com.academia0.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Acceso {
    // Datos de conexión a la base de datos.
    private static final String USER = "root";
    private static final String PWD = "8586";
    private static final String URL = "jdbc:mysql://localhost:55000/academia";
    private static Connection conexion = null;

    // Método para establecer una conexión con la base de datos.
    public static Connection conectar() {
        try {
            // Intentar establecer la conexión.
            conexion = DriverManager.getConnection(URL, USER, PWD);
            return conexion;
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error en la conexión.
            e.printStackTrace();
            return null;
        }
    }

    // Método para cerrar la conexión con la base de datos.
    public static void desconectar() {
        try {
            // Verificar si la conexión está abierta antes de cerrarla.
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al cerrar la conexión.
            e.printStackTrace();
        }
    }

    // Método para mostrar los datos de todos los alumnos.
    public static void mostrarAlumnos() {
        // Consulta SQL para obtener todos los alumnos.
        String query = "SELECT * FROM alumnos";
        try (PreparedStatement instruccion = conexion.prepareStatement(query);
             ResultSet resultado = instruccion.executeQuery()) {

            // Procesar el conjunto de resultados.
            while (resultado.next()) {
                // Mostrar los datos de cada alumno.
                System.out.println("ID: " + resultado.getInt("id"));
                System.out.println("Nombre: " + resultado.getString("nombre"));
                System.out.println("Apellido: " + resultado.getString("apellido"));
                System.out.println("Edad: " + resultado.getInt("edad"));
                System.out.println("-------------------------------------------------------\n");
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al procesar la consulta.
            e.printStackTrace();
        }
    }

    // Método para contar y mostrar el número total de alumnos.
    public static void cantidadAlumnos() {
        // Consulta SQL para contar el total de alumnos.
        String query = "SELECT COUNT(*) FROM alumnos";
        try (PreparedStatement instruccion = conexion.prepareStatement(query);
             ResultSet resultado = instruccion.executeQuery()) {

            // Verificar si hay resultado.
            if (resultado.next()) {
                // Mostrar la cantidad de alumnos.
                System.out.println("La cantidad de alumnos es: " + resultado.getInt(1));
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al procesar la consulta.
            e.printStackTrace();
        }
    }
}
