package com.example.bicicletas.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrdenServicio {

    private LocalDate fecha;
    private LocalTime hora;
    private Bicicleta bicicleta;
    private Mecanico mecanico;
    private String motivo;
    private String diagnostico;
    private String trabajo;
    private double costo;

    public OrdenServicio(LocalDate fecha, LocalTime hora, Bicicleta bicicleta, Mecanico mecanico,
                         String motivo, String diagnostico, String trabajo, double costo) {
        this.fecha = fecha;
        this.hora = hora;
        this.bicicleta = bicicleta;
        this.mecanico = mecanico;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.trabajo = trabajo;
        this.costo = costo;
    }

    public Bicicleta getBicicleta() { return bicicleta; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public Mecanico getMecanico() { return mecanico; }
    public String getMotivo() { return motivo; }
    public String getDiagnostico() { return diagnostico; }
    public String getTrabajo() { return trabajo; }
    public double getCosto() { return costo; }
}
