package com.example.bicicletas.ui;

import com.example.bicicletas.TallerBicicletasApp;
import com.example.bicicletas.model.Bicicleta;
import com.example.bicicletas.model.Mecanico;
import com.example.bicicletas.model.OrdenServicio;
import com.example.bicicletas.repository.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrdenServicioController {

    @FXML private DatePicker dpFecha;
    @FXML private TextField txtHora;
    @FXML private ComboBox<Bicicleta> cbBicicleta;
    @FXML private ComboBox<Mecanico> cbMecanico;
    @FXML private TextArea txtMotivo;
    @FXML private TextArea txtDiagnostico;
    @FXML private TextArea txtTrabajo;
    @FXML private TextField txtCosto;

    @FXML private TableView<OrdenServicio> tblOrdenes;
    @FXML private TableColumn<OrdenServicio, String> colFecha;
    @FXML private TableColumn<OrdenServicio, String> colHora;
    @FXML private TableColumn<OrdenServicio, String> colBicicleta;
    @FXML private TableColumn<OrdenServicio, String> colCliente;
    @FXML private TableColumn<OrdenServicio, String> colMecanico;
    @FXML private TableColumn<OrdenServicio, String> colCosto;

    private final ObservableList<OrdenServicio> ordenesObs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbBicicleta.setItems(FXCollections.observableArrayList(DataStore.bicicletas));
        cbBicicleta.setConverter(new javafx.util.StringConverter<Bicicleta>() {
            @Override
            public String toString(Bicicleta b) {
                if (b == null) return "";
                return b.getSerial() + " - " + b.getMarca() + " - " + b.getTipo() +
                        " (" + b.getCliente().getNombre() + ")";
            }

            @Override
            public Bicicleta fromString(String string) {
                return null;
            }
        });


        cbMecanico.setItems(FXCollections.observableArrayList(DataStore.mecanicos));
        cbMecanico.setConverter(new javafx.util.StringConverter<Mecanico>() {
            @Override
            public String toString(Mecanico m) {
                if (m == null) return "";
                return m.getNombre() + " - " + m.getEspecialidad().toString();
            }

            @Override
            public Mecanico fromString(String string) {
                return null;
            }
        });


        colFecha.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getFecha().toString()));

        colHora.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getHora().toString()));

        colBicicleta.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getBicicleta().getSerial() + " - " +
                                cell.getValue().getBicicleta().getMarca()));

        colCliente.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getBicicleta().getCliente().getNombre()));

        colMecanico.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getMecanico().getNombre()));

        colCosto.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        String.format("$%.2f", cell.getValue().getCosto())));

        ordenesObs.setAll(DataStore.ordenes);
        tblOrdenes.setItems(ordenesObs);


        dpFecha.setValue(LocalDate.now());
    }

    @FXML
    private void guardarOrden() {
        LocalDate fecha = dpFecha.getValue();
        String horaStr = txtHora.getText().trim();
        Bicicleta bicicleta = cbBicicleta.getValue();
        Mecanico mecanico = cbMecanico.getValue();
        String motivo = txtMotivo.getText().trim();
        String diagnostico = txtDiagnostico.getText().trim();
        String trabajo = txtTrabajo.getText().trim();
        String costoStr = txtCosto.getText().trim();


        if (fecha == null) {
            alerta(Alert.AlertType.WARNING, "Campo incompleto", "Por favor selecciona una fecha.");
            return;
        }


        if (horaStr.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Campo incompleto",
                    "Por favor ingresa la hora en formato HH:MM\nEjemplo: 14:30");
            return;
        }


        if (bicicleta == null) {
            alerta(Alert.AlertType.WARNING, "Campo incompleto",
                    "Por favor selecciona una bicicleta.\n\n" +
                            "💡 Si no aparecen bicicletas, primero debes:\n" +
                            "   1. Registrar un cliente\n" +
                            "   2. Registrar una bicicleta asociada a ese cliente");
            return;
        }


        if (mecanico == null) {
            alerta(Alert.AlertType.WARNING, "Campo incompleto",
                    "Por favor selecciona un mecánico.\n\n" +
                            "💡 Si no aparecen mecánicos, ve a 'Registrar Mecánico' primero.");
            return;
        }


        if (motivo.isEmpty() || diagnostico.isEmpty() || trabajo.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Campos incompletos",
                    "Por favor llena todos los campos:\n" +
                            "• Motivo del servicio\n" +
                            "• Diagnóstico\n" +
                            "• Trabajo realizado");
            return;
        }


        if (costoStr.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Campo incompleto", "Por favor ingresa el costo del servicio.");
            return;
        }


        LocalTime hora;
        try {
            String[] partes = horaStr.split(":");
            if (partes.length != 2) {
                alerta(Alert.AlertType.ERROR, "Error en hora",
                        "Formato de hora inválido.\n\nUsa el formato HH:MM\nEjemplo: 14:30");
                return;
            }

            int horas = Integer.parseInt(partes[0].trim());
            int minutos = Integer.parseInt(partes[1].trim());

            if (horas < 0 || horas > 23) {
                alerta(Alert.AlertType.ERROR, "Error en hora",
                        "Las horas deben estar entre 00 y 23");
                return;
            }

            if (minutos < 0 || minutos > 59) {
                alerta(Alert.AlertType.ERROR, "Error en hora",
                        "Los minutos deben estar entre 00 y 59");
                return;
            }

            hora = LocalTime.of(horas, minutos);

        } catch (NumberFormatException e) {
            alerta(Alert.AlertType.ERROR, "Error en hora",
                    "Formato de hora inválido.\n\nUsa solo números en formato HH:MM\nEjemplo: 14:30");
            return;
        } catch (Exception e) {
            alerta(Alert.AlertType.ERROR, "Error en hora",
                    "Error al procesar la hora:\n" + e.getMessage());
            return;
        }


        double costo;
        try {
            costo = Double.parseDouble(costoStr.trim());

            if (costo < 0) {
                alerta(Alert.AlertType.ERROR, "Error en costo",
                        "El costo no puede ser negativo.");
                return;
            }

        } catch (NumberFormatException e) {
            alerta(Alert.AlertType.ERROR, "Error en costo",
                    "El costo debe ser un número válido.\n\nEjemplos válidos: 50  o  150.50");
            return;
        }


        OrdenServicio nueva = new OrdenServicio(
                fecha, hora, bicicleta, mecanico,
                motivo, diagnostico, trabajo, costo
        );

        DataStore.ordenes.add(nueva);
        ordenesObs.setAll(DataStore.ordenes);
        limpiarFormulario();


        alerta(Alert.AlertType.INFORMATION, "✅ Orden Guardada",
                "Orden de servicio registrada correctamente.\n\n" +
                        "Fecha: " + fecha + "\n" +
                        "Hora: " + hora + "\n" +
                        "Bicicleta: " + bicicleta.getSerial() + " - " + bicicleta.getMarca() + "\n" +
                        "Cliente: " + bicicleta.getCliente().getNombre() + "\n" +
                        "Mecánico: " + mecanico.getNombre() + "\n" +
                        "Costo: $" + String.format("%.2f", costo));
    }

    @FXML
    private void limpiarFormulario() {
        dpFecha.setValue(LocalDate.now());
        txtHora.clear();
        cbBicicleta.setValue(null);
        cbMecanico.setValue(null);
        txtMotivo.clear();
        txtDiagnostico.clear();
        txtTrabajo.clear();
        txtCosto.clear();
        dpFecha.requestFocus();
    }

    @FXML
    private void volverAlMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    TallerBicicletasApp.class.getResource("ui/MainView.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);

            Stage stage = (Stage) dpFecha.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Taller de Bicicletas");

        } catch (IOException e) {
            alerta(Alert.AlertType.ERROR, "Error",
                    "No se pudo volver al menú.\n" + e.getMessage());
            e.printStackTrace();
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
