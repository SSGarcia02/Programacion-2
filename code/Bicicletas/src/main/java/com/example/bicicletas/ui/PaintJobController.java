package com.example.bicicletas.ui;
import java.io.IOException;
import com.example.bicicletas.model.Bicicleta;
import com.example.bicicletas.model.Cliente;
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


public class PaintJobController {

    @FXML private TableView<Bicicleta> tableBicicleta;
    @FXML private TableColumn<Bicicleta, String> tcClienteAsignado;
    @FXML private TableColumn<Bicicleta, String> tcColor;
    @FXML private TableColumn<Bicicleta, String> tcMarca;
    @FXML private TableColumn<Bicicleta, String> tcModelo;
    @FXML private TableColumn<Bicicleta, String> tcNumMarco;
    @FXML private TableColumn<Bicicleta, String> tcTipo;

    @FXML private TextField txtBuscarNumMarco;
    @FXML private TextField txtNuevoColor;
    @FXML private TextArea txtDescripcionBicicleta;

    private final ObservableList<Bicicleta> bicicletasObs = FXCollections.observableArrayList();

    @FXML
    void initialize() {
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
        bicicletasObs.setAll(DataStore.bicicletas);
        tableBicicleta.setItems(bicicletasObs);
    }

    @FXML
    void buscarBicicleta(ActionEvent event) {
        String numMarcoBuscar = txtBuscarNumMarco.getText().trim();

        if (numMarcoBuscar.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Campo vacío", "Por favor ingresa un número de marco para buscar.");
            return;
        }
        Bicicleta bicicletaEncontrada = null;

        for (Bicicleta bici : DataStore.bicicletas) {
            if (bici.getSerial().equalsIgnoreCase(numMarcoBuscar)) {
                bicicletaEncontrada = bici;
                break;
            }
        }
        if (bicicletaEncontrada != null) {
            String descripcion = DescripcionBicicleta(bicicletaEncontrada);

            txtDescripcionBicicleta.setText(descripcion);

            alerta(Alert.AlertType.INFORMATION, "Éxito", "Bicicleta encontrada.");
        } else {
            txtDescripcionBicicleta.setText("No se encontró ninguna bicicleta con el número de marco: " + numMarcoBuscar);
            alerta(Alert.AlertType.ERROR, "No encontrada", "No existe una bicicleta con el número de marco: " + numMarcoBuscar);
        }

    }

    @FXML
    void pintarBicicleta(ActionEvent event) {
        String numMarco = txtBuscarNumMarco.getText().trim();
        String nuevoColor = txtNuevoColor.getText().trim();

        if (numMarco.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Campo vacío",
                    "Primero debes buscar una bicicleta ingresando un número de marco.");
            return;
        }
        if (nuevoColor.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Color vacío",
                    "Por favor ingresa el nuevo color para la bicicleta.");
            return;
        }

        Bicicleta bicicletaAPintar = null;
        for (Bicicleta bici : DataStore.bicicletas) {
            if (bici.getSerial().equalsIgnoreCase(numMarco)) {
                bicicletaAPintar = bici;
                break;
            }
        }
        if (bicicletaAPintar == null) {
            alerta(Alert.AlertType.ERROR, "Bicicleta no encontrada",
                    "No existe una bicicleta con el número de marco: " + numMarco);
            return;
        }
        if (bicicletaAPintar.getColor().equalsIgnoreCase(nuevoColor)) {
            alerta(Alert.AlertType.WARNING, "Mismo color",
                    "La bicicleta ya tiene el color " + nuevoColor + ". Ingresa un color diferente.");
            return;
        }
        String colorAnterior = bicicletaAPintar.getColor();

        bicicletaAPintar.setColor(nuevoColor);

        actualizarVista(bicicletaAPintar, colorAnterior, nuevoColor);
    }

    private String DescripcionBicicleta(Bicicleta bicicletaEncontrada) {

        String descripcion = "=== DATOS DE LA BICICLETA ===\n" +
                "Número de marco: " + bicicletaEncontrada.getSerial() + "\n" +
                "Marca: " + bicicletaEncontrada.getMarca() + "\n" +
                "Tipo: " + bicicletaEncontrada.getTipo() + "\n" +
                "Color: " + bicicletaEncontrada.getColor() + "\n" +
                "Modelo/Año: " + bicicletaEncontrada.getAnio();

        return descripcion;
    }
    private void actualizarVista(Bicicleta bicicleta, String colorAnterior, String nuevoColor) {
        bicicletasObs.setAll(DataStore.bicicletas);
        tableBicicleta.refresh();

        String descripcionActualizada = DescripcionBicicleta(bicicleta);
        txtDescripcionBicicleta.setText(descripcionActualizada);

        txtNuevoColor.clear();

        alerta(Alert.AlertType.INFORMATION, "¡Bicicleta pintada!",
                "La bicicleta con marco " + bicicleta.getSerial() + " ha sido repintada.\n" +
                        "Color anterior: " + colorAnterior + "\n" +
                        "Color nuevo: " + nuevoColor);
    }
    @FXML
    void volverAlMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bicicletas/ui/MainView.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);

            Stage stage = (Stage) txtBuscarNumMarco.getScene().getWindow();
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
