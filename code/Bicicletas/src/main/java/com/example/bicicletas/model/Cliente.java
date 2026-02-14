package com.example.bicicletas.model;

public class Cliente {
    private String nombre;
    private String identificacion;
    private String telefono;
    private String direccion;

    public Cliente(String nombre, String identificacion, String telefono, String direccion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
}