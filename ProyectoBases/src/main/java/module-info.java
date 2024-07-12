module com.sebaescu.proyectobases {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sebaescu.proyectobases to javafx.fxml;
    exports com.sebaescu.proyectobases;
}
