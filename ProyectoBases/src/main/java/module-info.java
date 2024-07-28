module com.sebaescu.proyectobases {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires java.sql;

    opens com.sebaescu.proyectobases to javafx.fxml;
    exports com.sebaescu.proyectobases;
}
