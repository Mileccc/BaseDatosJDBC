package com.academia0;

import java.sql.Connection;
import com.academia0.controlador.Acceso;
import com.academia0.modelo.Alumno;

public class Main {
    public static void main(String[] args) {
        // Establecer una conexión con la base de datos.
        Connection conexion = Acceso.conectar();

        // Crear instancias de la clase Alumno.
        Alumno al1 = new Alumno("Pepe", "Perez", 20);
        Alumno al2 = new Alumno("Luis", "Lopez", 21);
        Alumno al3 = new Alumno("Juan", "Jimenez", 22);

        try {
            // Insertar los alumnos en la base de datos.
            Acceso.insertarAlumno(al1);
            Acceso.insertarAlumno(al2);
            Acceso.insertarAlumno(al3);

            // Eliminar el primer alumno de la base de datos.
            Acceso.eliminarAlumno(al1);
            
            // Listar todos los alumnos en la base de datos.
            Acceso.listarAlumnos();

            // Mostrar la cantidad total de alumnos en la base de datos.
            Acceso.cantidadAlumnos();

        } catch (Exception e) {
            // Capturar y manejar cualquier excepción que ocurra.
            e.printStackTrace();
        } finally {
            // Cerrar la conexión con la base de datos, independientemente de si se produjeron excepciones.
            Acceso.desconectar();
        }
    }
}
