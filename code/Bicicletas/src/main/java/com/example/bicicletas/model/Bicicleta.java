package com.example.bicicletas.model;

public class Bicicleta {
    private String serial;
    private String marca;
    private TipoBicicleta tipo;
    private String color;
    private int anio;
    private Cliente cliente;

    public Bicicleta(String serial, String marca, TipoBicicleta tipo, String color, int anio, Cliente cliente) {
        this.serial = serial;
        this.marca = marca;
        this.tipo = tipo;
        this.color = color;
        this.anio = anio;
        this.cliente = cliente;
    }

    public String getSerial() { return serial; }
    public Cliente getCliente() { return cliente; }
}