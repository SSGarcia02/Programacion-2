package com.example.bicicletas.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import com.example.bicicletas.repository.DataStore;

public class HistorialController {

    @FXML
    private TextField txtBicicletaId;

    @FXML
    private TextArea areaResultados;

    @FXML
    private void buscarHistorial() {
        String id = txtBicicletaId.getText();

        if (id == null || id.trim().isEmpty()) {
            mostrarAlerta("Por favor, ingrese un ID de bicicleta.");
            return;
        }

        StringBuilder historial = new StringBuilder();
        historial.append("Historial para la Bicicleta ID: ").append(id).append("\n");
        historial.append("--------------------------------------------------\n");

        boolean encontrada = false;
        for (com.example.bicicletas.model.OrdenServicio orden : DataStore.ordenes) {
            if (orden.getBicicleta().getSerial().equals(id)) {
                historial.append("Fecha: ").append(orden.getFecha()).append(" - Mecánico: ").append(orden.getMecanico().getNombre()).append("\n");
                historial.append("   - Motivo: ").append(orden.getMotivo()).append("\n");
                historial.append("   - Costo: $").append(orden.getCosto()).append("\n\n");
                encontrada = true;
            }
        }
        if (!encontrada) {
            historial.append("No se encontraron registros para esta bicicleta.\n");
            historial.append("Intente con ID 123 o 456 para ver ejemplos.");
        }

        areaResultados.setText(historial.toString());
    }

    @FXML
    private void volverAlMenu(ActionEvent event) {
        try {
            // Usamos getClass().getResource() en lugar de TallerBicicletasApp.class.getResource()
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
