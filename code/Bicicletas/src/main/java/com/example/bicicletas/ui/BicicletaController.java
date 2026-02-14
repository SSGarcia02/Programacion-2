package com.example.bicicletas.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.bicicletas.TallerBicicletasApp;
import com.example.bicicletas.model.Bicicleta;
import com.example.bicicletas.model.Cliente;
import com.example.bicicletas.model.TipoBicicleta;
import com.example.bicicletas.repository.DataStore;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class BicicletaController {
    @FXML private ResourceBundle resources;

    @FXML private URL location;

    private final ObservableList<Bicicleta> bicicletasObs = FXCollections.observableArrayList();

    @FXML private TableView<Bicicleta> tableBicicleta;
    @FXML private TableColumn<Bicicleta, String> tcClienteAsignado;
    @FXML private TableColumn<Bicicleta, String> tcColor;
    @FXML private TableColumn<Bicicleta, String> tcMarca;
    @FXML private TableColumn<Bicicleta, String> tcModelo;
    @FXML private TableColumn<Bicicleta, String> tcNumMarco;
    @FXML private TableColumn<Bicicleta, String> tcTipo;

    @FXML private ComboBox<String> ComboBoxTipo;
    @FXML private ComboBox<String> ComboBoxClienteAsignado;
    @FXML private TextField txtColor;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtNumMarco;

    @FXML
    public void initialize() {

        tcMarca.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getMarca()));
        tcTipo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getTipo())));
        tcColor.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getColor()));
        tcNumMarco.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getSerial()));
        tcModelo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getAnio())));
        tcClienteAsignado.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getCliente())));

        bicicletasObs.setAll(DataStore.bicicletas);
        tableBicicleta.setItems(bicicletasObs);
    }

    @FXML
    private void agregarBicicleta() {
        String marca = txtMarca.getText().trim();
        String tipo = ComboBoxTipo.getValue();
        String color = txtColor.getText().trim();
        String numMarco = txtNumMarco.getText().trim();
        String modelo = txtModelo.getText().trim();
        String clienteAsignado = ComboBoxClienteAsignado.getValue();

        if (marca.isEmpty() || tipo == null || color.isEmpty() || numMarco.isEmpty()
                || modelo.isEmpty() || clienteAsignado == null){
            alerta(Alert.AlertType.WARNING, "Campos incompletos", "Por favor llena todos los campos.");
            return;
        }
        boolean yaExiste = DataStore.bicicletas.stream()
                .anyMatch(c -> c.getSerial().equalsIgnoreCase(numMarco));
        if (yaExiste){
            alerta(Alert.AlertType.ERROR, "Duplicado", "Ya existe una bicicleta con este numero de marco.");
            return;
        }
        Bicicleta nuevaBici = new Bicicleta(numMarco, marca, tipo, color, modelo, clienteAsignado);
        DataStore.bicicletas.add(nuevaBici);

        bicicletasObs.setAll(DataStore.bicicletas);
        LimpiarCampos();

        alerta(Alert.AlertType.INFORMATION, "Guardado", "Bicicleta registrada correctamente.");

    }

    @FXML
    private void ActualizarBicicleta() {

    }

    @FXML
    private void BorrarBicicleta() {

    }

    @FXML
    private void LimpiarCampos() {
        txtMarca.clear();
        ComboBoxTipo.setValue(null);
        txtColor.clear();
        txtNumMarco.clear();
        txtModelo.clear();
        ComboBoxClienteAsignado.setValue(null);
    }

    @FXML
    private void volverAlMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(TallerBicicletasApp.class.getResource("ui/MainView.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);

            Stage stage = (Stage) txtMarca.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Taller de Bicicletas");
        } catch (IOException e) {
            alerta(Alert.AlertType.ERROR, "Error", "No se pudo volver al menú.\n" + e.getMessage());
        }
    }
    private void alerta(Alert.AlertType type, String titulo, String msg) {
        Alert a = new Alert(type);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }


}
