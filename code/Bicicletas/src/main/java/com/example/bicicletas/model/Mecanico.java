package com.example.bicicletas.model;

public class Mecanico {
    private String nombre;
    private EspecialidadMecanico especialidad;
    private String codigo;

    public Mecanico(String nombre, EspecialidadMecanico especialidad, String codigo) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.codigo = codigo;
    }

    public String getNombre() { return nombre; }
    public EspecialidadMecanico getEspecialidad() { return especialidad; }
    public String getCodigo() { return codigo; }
}