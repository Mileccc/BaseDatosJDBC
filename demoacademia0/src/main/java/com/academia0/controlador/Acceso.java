package com.academia0.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.academia0.modelo.Alumno;

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
    public static void listarAlumnos() {
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

    public static void insertarAlumno(Alumno a) {
        // Consulta SQL para insertar un nuevo alumno.
        String query = "INSERT INTO alumnos (nombre, apellido, edad) VALUES (?, ?, ?)";
        try {
            // Crear un PreparedStatement para ejecutar la consulta SQL.
            // Se usa Statement.RETURN_GENERATED_KEYS para poder recuperar el ID autoincrementado después de la inserción.
            PreparedStatement instruccion = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // Configurar los parámetros del PreparedStatement con los datos del alumno.
            instruccion.setString(1, a.getNombre());
            instruccion.setString(2, a.getApellido());
            instruccion.setInt(3, a.getEdad());
    
            // Ejecutar la actualización, que inserta el nuevo alumno en la base de datos.
            instruccion.executeUpdate();
    
            // Recuperar las claves generadas por la inserción (en este caso, el ID autoincrementado).
            ResultSet generatedKeys = instruccion.getGeneratedKeys();
            if (generatedKeys.next()) {
                // Asignar el ID autoincrementado al objeto alumno.
                a.setId(generatedKeys.getInt(1));
            }
    
            // Cerrar ResultSet y PreparedStatement.
            generatedKeys.close();
            instruccion.close();
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al ejecutar la consulta o al recuperar el ID.
            e.printStackTrace();
        }
    }
    
    

    // Método para eliminar un alumno de la base de datos.
    public static void eliminarAlumno(Alumno a){
        String query = "DELETE FROM alumnos WHERE id = ?";
        try (PreparedStatement instruccion = conexion.prepareStatement(query)) {
            // Configurar el parámetro ID para el PreparedStatement.
            instruccion.setInt(1, a.getId());
            // Ejecutar la eliminación.
            instruccion.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al procesar la consulta.
            e.printStackTrace();
        }
    }
}
