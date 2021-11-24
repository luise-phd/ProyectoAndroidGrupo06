package com.ingluise.ProyectoAndroidGrupo06;

public class Empleado {
    private String dni, nombre, apellidos;
    private int salario;

    public Empleado() {
        this.dni = "";
        this.nombre = "";
        this.apellidos = "";
        this.salario = 0;
    }

    public Empleado(String dni, String nom, String ape, int sal) {
        this.dni = dni;
        this.nombre = nom;
        this.apellidos = ape;
        this.salario = sal;
    }

    //Métodos de acceso

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getSalario() {
        return salario;
    }

    //Métodos modificadores

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}
