package com.example.bicicletas.model;

public class Bicicleta {
    private String serial;
    private String marca;
    private TipoBicicleta tipo;
    private String color;
    private int anio;
    private Cliente cliente;

    public Bicicleta(){    }
    public Bicicleta(String serial, String marca, TipoBicicleta tipo, String color, int anio, Cliente cliente) {
        this.serial = serial;
        this.marca = marca;
        this.tipo = tipo;
        this.color = color;
        this.anio = anio;
        this.cliente = cliente;
    }

    public Bicicleta(String numMarco, String marca, String tipo, String color, String modelo, String clienteAsignado) {
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public TipoBicicleta getTipo() {
        return tipo;
    }

    public void setTipo(TipoBicicleta tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getSerial() { return serial; }
    public Cliente getCliente() { return cliente; }
}