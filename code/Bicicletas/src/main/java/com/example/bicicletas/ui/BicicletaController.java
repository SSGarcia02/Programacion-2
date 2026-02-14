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

    @FXML private TableView<Bicicleta> tableBicicleta;
    @FXML private TableColumn<Bicicleta, String> tcClienteAsignado;
    @FXML private TableColumn<Bicicleta, String> tcColor;
    @FXML private TableColumn<Bicicleta, String> tcMarca;
    @FXML private TableColumn<Bicicleta, String> tcModelo;
    @FXML private TableColumn<Bicicleta, String> tcNumMarco;
    @FXML private TableColumn<Bicicleta, String> tcTipo;

    @FXML private ComboBox<String> ComboBoxTipo;
    @FXML private ComboBox<Cliente> ComboBoxClienteAsignado;
    @FXML private TextField txtColor;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtNumMarco;

    private final ObservableList<Bicicleta> bicicletasObs = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        tcMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        tcTipo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTipo())));
        tcColor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColor()));
        tcNumMarco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSerial()));
        tcModelo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAnio())));
        tcClienteAsignado.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue().getCliente();
            String nombreCliente = (cliente != null) ? cliente.getNombre() : "Sin asignar";
            return new SimpleStringProperty(nombreCliente);
        });

        llenarComboBoxTipo();
        llenarComboBoxClientes();
        
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
        Cliente clienteAsignado = ComboBoxClienteAsignado.getValue();

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
        Bicicleta nuevaBici = new Bicicleta();
        nuevaBici.setMarca(marca);
        nuevaBici.setTipo(TipoBicicleta.valueOf(tipo));
        nuevaBici.setColor(color);
        nuevaBici.setSerial(numMarco);
        nuevaBici.setAnio(Integer.parseInt(modelo));
        nuevaBici.setCliente(clienteAsignado);

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
    private void llenarComboBoxTipo() {
        // Obtener todos los valores del Enum y convertirlos a String
        ObservableList<String> tipos = FXCollections.observableArrayList();

        for (TipoBicicleta tipo : TipoBicicleta.values()) {
            tipos.add(tipo.toString());
        }
        ComboBoxTipo.setItems(tipos);
    }

    private void llenarComboBoxClientes() {
        if (DataStore.clientes != null) {
            // Crear ObservableList de tipo Cliente (no String)
            ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(DataStore.clientes);
            ComboBoxClienteAsignado.setItems(listaClientes);

            // Configurar cómo se muestran los clientes
            ComboBoxClienteAsignado.setCellFactory(param -> new ListCell<Cliente>() {
                @Override
                protected void updateItem(Cliente cliente, boolean empty) {
                    super.updateItem(cliente, empty);
                    if (empty || cliente == null) {
                        setText(null);
                    } else {
                        setText(cliente.getNombre()); // o cliente.getNombreCompleto()
                    }
                }
            });

        }
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
