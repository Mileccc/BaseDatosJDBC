package com.academia0.modelo;

public class Alumno {
    // Campos privados para representar las propiedades del alumno.
    private int id;
    private String nombre;
    private String apellido;
    private int edad;

    // Constructor para crear un nuevo alumno con nombre, apellido y edad.
    // El ID se asignará automáticamente en la base de datos de forma autoIncrementada.
    public Alumno(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    // Método getters y setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
