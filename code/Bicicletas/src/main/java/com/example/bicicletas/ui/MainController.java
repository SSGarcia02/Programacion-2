package com.example.bicicletas.ui;

import com.example.bicicletas.TallerBicicletasApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

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
        }    }

    @FXML
    private void registrarBicicleta(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TallerBicicletasApp.class.getResource("ui/BicicletaView.fxml"));
            Scene scene = new Scene(loader.load(), 720, 450);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Registrar Bicicleta");

        } catch (Exception e) {
            mostrar("Error abriendo ClienteView: " + e.getMessage());
        }    }

    @FXML
    private void registrarMecanico(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bicicletas/ui/MecanicoView.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bicicletas/ui/OrdenServicioView.fxml"));
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
    private void historial(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TallerBicicletasApp.class.getResource("ui/HistorialView.fxml"));
            Scene scene = new Scene(loader.load(), 720, 450);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Historial");

        } catch (Exception e) {
            mostrar("Error abriendo ClienteView: " + e.getMessage());
        }
       
    }
    @FXML
    private void ordenesPorFecha(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TallerBicicletasApp.class.getResource("ui/OrdenesPorFechaView.fxml"));
            Scene scene = new Scene(loader.load(), 720, 450);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Paint Job");

        } catch (Exception e) {
            mostrar("Error abriendo ClienteView: " + e.getMessage());
        }
    }
    @FXML
    private void paintJob(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(TallerBicicletasApp.class.getResource("ui/PaintJobView.fxml"));
            Scene scene = new Scene(loader.load(), 720, 450);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Registrar Cliente");

        } catch (Exception e) {
            mostrar("Error abriendo ClienteView: " + e.getMessage());
        }
    }
    private void cambiarEscena(ActionEvent event, String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), 720, 450); // Usamos el mismo tamaño para todas

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(titulo);

        } catch (IOException e) {
            mostrar("Error abriendo la vista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrar(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
