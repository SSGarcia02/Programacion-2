package com.example.bicicletas.ui;

import com.example.bicicletas.TallerBicicletasApp;
import com.example.bicicletas.model.EspecialidadMecanico;
import com.example.bicicletas.model.Mecanico;
import com.example.bicicletas.repository.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MecanicoController {

    @FXML private TextField txtNombre;
    @FXML private ComboBox<EspecialidadMecanico> cbEspecialidad;
    @FXML private TextField txtCodigo;

    @FXML private TableView<Mecanico> tblMecanicos;
    @FXML private TableColumn<Mecanico, String> colNombre;
    @FXML private TableColumn<Mecanico, String> colEspecialidad;
    @FXML private TableColumn<Mecanico, String> colCodigo;

    private final ObservableList<Mecanico> mecanicosObs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbEspecialidad.setItems(FXCollections.observableArrayList(EspecialidadMecanico.values()));

        // Conectar columnas con getters del modelo
        colNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        colEspecialidad.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEspecialidad().toString()));
        colCodigo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCodigo()));

        mecanicosObs.setAll(DataStore.mecanicos);
        tblMecanicos.setItems(mecanicosObs);
    }

    @FXML
    private void guardarMecanico() {
        String nombre = txtNombre.getText().trim();
        EspecialidadMecanico especialidad = cbEspecialidad.getValue();
        String codigo = txtCodigo.getText().trim();

        if (nombre.isEmpty() || especialidad == null || codigo.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Campos incompletos", "Por favor llena todos los campos.");
            return;
        }

        boolean yaExiste = DataStore.mecanicos.stream()
                .anyMatch(m -> m.getCodigo().equalsIgnoreCase(codigo));

        if (yaExiste) {
            alerta(Alert.AlertType.ERROR, "Duplicado", "Ya existe un mecánico con ese código.");
            return;
        }

        Mecanico nuevo = new Mecanico(nombre, especialidad, codigo);
        DataStore.mecanicos.add(nuevo);

        mecanicosObs.setAll(DataStore.mecanicos);
        limpiarFormulario();

        alerta(Alert.AlertType.INFORMATION, "Guardado", "Mecánico registrado correctamente.");
    }

    @FXML
    private void limpiarFormulario() {
        txtNombre.clear();
        cbEspecialidad.setValue(null);
        txtCodigo.clear();
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