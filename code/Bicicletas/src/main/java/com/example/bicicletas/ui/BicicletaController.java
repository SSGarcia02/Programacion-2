package com.example.bicicletas.ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.bicicletas.model.Bicicleta;
import com.example.bicicletas.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    @FXML private TextField txtClienteAsignado;
    @FXML private TextField txtColor;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtNumMarco;

    @FXML
    void initialize() {

    }
    @FXML
    void ActualizarBicicleta(ActionEvent event) {

    }

    @FXML
    void AgregarBicicleta(ActionEvent event) {

    }

    @FXML
    void BorrarBicicleta(ActionEvent event) {

    }

    @FXML
    void LimpiarCampos(ActionEvent event) {

    }

    @FXML
    void volverAlMenu(ActionEvent event) {

    }


}
