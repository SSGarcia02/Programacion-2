module com.example.bicicletas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.bicicletas to javafx.fxml;
    opens com.example.bicicletas.model to javafx.fxml;
    opens com.example.bicicletas.service to javafx.fxml;
    opens com.example.bicicletas.repository to javafx.fxml;
    opens com.example.bicicletas.ui to javafx.fxml;


    exports com.example.bicicletas;
    exports com.example.bicicletas.model;
    exports com.example.bicicletas.service;
    exports com.example.bicicletas.repository;
    exports com.example.bicicletas.ui;
}