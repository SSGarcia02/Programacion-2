package com.example.bicicletas.ui;

import com.example.bicicletas.model.OrdenServicio;
import com.example.bicicletas.repository.DataStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrdenesPorFechaController {

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private TextArea areaResultados;

    @FXML
    private void buscarOrdenes() {
        LocalDate fecha = datePickerFecha.getValue();

        if (fecha == null) {
            mostrarAlerta("Por favor, seleccione una fecha.");
            return;
        }

        String fechaStr = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        StringBuilder reporte = new StringBuilder();
        reporte.append("Órdenes del día: ").append(fechaStr).append("\n");
        reporte.append("--------------------------------------------------\n");

        boolean encontrada = false;

        // Recorremos la lista REAL de órdenes guardadas
        for (OrdenServicio orden : DataStore.ordenes) {
            if (orden.getFecha().equals(fecha)) { // Compara LocalDate objects
                encontrada = true;
                reporte.append("• Hora: ").append(orden.getHora()).append("\n");
                reporte.append("  Cliente: ").append(orden.getBicicleta().getCliente().getNombre()).append("\n");
                reporte.append("  Bicicleta: ").append(orden.getBicicleta().getMarca())
                       .append(" (").append(orden.getBicicleta().getSerial()).append(")\n");
                reporte.append("  Mecánico: ").append(orden.getMecanico().getNombre()).append("\n");
                reporte.append("  Motivo: ").append(orden.getMotivo()).append("\n");
                reporte.append("  Costo: $").append(String.format("%.2f", orden.getCosto())).append("\n");
                reporte.append("--------------------------------------------------\n");
            }
        }

        if (!encontrada) {
            reporte.append("No hay órdenes registradas para esta fecha.\n");
        }

        areaResultados.setText(reporte.toString());
    }

    @FXML
    private void volverAlMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bicicletas/ui/MainView.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Taller de Bicicletas");
        } catch (IOException e) {
            mostrarAlerta("Error al volver al menú: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
