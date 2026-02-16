package com.example.bicicletas.ui;

import com.example.bicicletas.TallerBicicletasApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private void registrarCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TallerBicicletasApp.class.getResource("ui/ClienteView.fxml"));
            Scene scene = new Scene(loader.load(), 720, 450);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Registrar Cliente");

        } catch (Exception e) {
            mostrar("Error abriendo ClienteView: " + e.getMessage());
        }
    }


    @FXML
    private void registrarBicicleta(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TallerBicicletasApp.class.getResource("ui/BicicletaView.fxml"));
            Scene scene = new Scene(loader.load(), 720, 450);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Registrar Cliente");

        } catch (Exception e) {
            mostrar("Error abriendo ClienteView: " + e.getMessage());
        }
    }

    @FXML
    private void registrarMecanico(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TallerBicicletasApp.class.getResource("ui/MecanicoView.fxml"));
            Scene scene = new Scene(loader.load(), 720, 450);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Registrar Mecánico");

        } catch (Exception e) {
            mostrar("Error abriendo MecanicoView: " + e.getMessage());
        }
    }
    @FXML
    private void nuevaOrden(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TallerBicicletasApp.class.getResource("ui/OrdenServicioView.fxml"));
            Scene scene = new Scene(loader.load(), 1000, 750);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Nueva Orden de Servicio - Taller de Bicicletas");

        } catch (Exception e) {
            mostrar("Error abriendo OrdenServicioView: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void historial() {
        mostrar("Historial por Bicicleta (pendiente)");
    }

    @FXML
    private void ordenesPorFecha() {
        mostrar("Órdenes por Fecha (pendiente)");
    }

    private void mostrar(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
