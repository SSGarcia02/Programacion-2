package com.example.bicicletas.service;

import com.example.bicicletas.model.Bicicleta;
import com.example.bicicletas.model.Cliente;
import com.example.bicicletas.model.Mecanico;
import com.example.bicicletas.model.OrdenServicio;
import com.example.bicicletas.repository.DataStore;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TallerService {

    public void registrarCliente(Cliente c) {
        DataStore.clientes.add(c);
    }

    public void registrarBicicleta(Bicicleta b) {
        DataStore.bicicletas.add(b);
    }

    public void registrarMecanico(Mecanico m) {
        DataStore.mecanicos.add(m);
    }

    public void crearOrden(OrdenServicio o) {
        DataStore.ordenes.add(o);
    }

    public List<OrdenServicio> historialPorSerial(String serial) {
        return DataStore.ordenes.stream()
                .filter(o -> o.getBicicleta().getSerial().equals(serial))
                .collect(Collectors.toList());
    }

    public List<OrdenServicio> ordenesPorFecha(LocalDate fecha) {
        return DataStore.ordenes.stream()
                .filter(o -> o.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }
}

