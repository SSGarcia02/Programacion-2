package com.example.bicicletas.repository;

import com.example.bicicletas.model.Bicicleta;
import com.example.bicicletas.model.Cliente;
import com.example.bicicletas.model.Mecanico;
import com.example.bicicletas.model.OrdenServicio;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static List<Cliente> clientes = new ArrayList<>();
    public static List<Bicicleta> bicicletas = new ArrayList<>();
    public static List<Mecanico> mecanicos = new ArrayList<>();
    public static List<OrdenServicio> ordenes = new ArrayList<>();
}

