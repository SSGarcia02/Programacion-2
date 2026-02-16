package com.example.bicicletas.ui;

import com.example.bicicletas.model.Cliente;
import com.example.bicicletas.repository.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;

    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colIdentificacion;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colDireccion;

    private final ObservableList<Cliente> clientesObs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Conectar columnas con getters del modelo (propiedades)
        colNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        colIdentificacion.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getIdentificacion()));
        colTelefono.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTelefono()));
        colDireccion.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDireccion()));

        clientesObs.setAll(DataStore.clientes);
        tblClientes.setItems(clientesObs);
    }

    @FXML
    private void guardarCliente() {
        String nombre = txtNombre.getText().trim();
        String id = txtIdentificacion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (nombre.isEmpty() || id.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Campos incompletos", "Por favor llena todos los campos.");
            return;
        }

        // Validación simple: no duplicar identificación
        boolean yaExiste = DataStore.clientes.stream()
                .anyMatch(c -> c.getIdentificacion().equalsIgnoreCase(id));

        if (yaExiste) {
            alerta(Alert.AlertType.ERROR, "Duplicado", "Ya existe un cliente con esa identificación.");
            return;
        }

        Cliente nuevo = new Cliente(nombre, id, telefono, direccion);
        DataStore.clientes.add(nuevo);

        clientesObs.setAll(DataStore.clientes);
        limpiarFormulario();

        alerta(Alert.AlertType.INFORMATION, "Guardado", "Cliente registrado correctamente.");
    }

    @FXML
    private void limpiarFormulario() {
        txtNombre.clear();
        txtIdentificacion.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtNombre.requestFocus();
    }

    @FXML
    private void volverAlMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bicicletas/ui/MainView.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);

            Stage stage = (Stage) txtNombre.getScene().getWindow();
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
