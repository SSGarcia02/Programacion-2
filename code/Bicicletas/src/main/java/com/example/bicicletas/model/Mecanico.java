package com.example.bicicletas.model;

public class Mecanico {
    private String nombre;
    private String especialidad;
    private String codigo;

    public Mecanico(String nombre, String especialidad, String codigo) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.codigo = codigo;
    }

    public String getNombre() { return nombre; }
}

