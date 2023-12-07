package com.academia0;

import java.sql.Connection;
import com.academia0.controlador.Acceso;

public class Main {
    public static void main(String[] args) {
        // Establecer una conexión con la base de datos.
        Connection conexion = Acceso.conectar();

        try {
            // Intentar realizar operaciones con la base de datos.

            // Mostrar todos los alumnos en la base de datos.
            // Este método ejecuta una consulta SQL y muestra los resultados.
            Acceso.mostrarAlumnos();

            // Contar y mostrar el número total de alumnos en la base de datos.
            // Este método ejecuta una consulta SQL para contar los alumnos y muestra este número.
            Acceso.cantidadAlumnos();

        } catch (Exception e) {
            // Capturar y manejar cualquier excepción que pueda ocurrir durante las operaciones de la base de datos.
            e.printStackTrace();
        } finally {
            // Asegurarse de cerrar la conexión con la base de datos una vez completadas todas las operaciones.
            // Esto es importante para evitar fugas de recursos.
            Acceso.desconectar();
        }
    }
}
