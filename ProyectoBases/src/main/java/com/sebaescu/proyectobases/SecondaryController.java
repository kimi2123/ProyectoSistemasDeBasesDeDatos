package com.sebaescu.proyectobases;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {
    @FXML
    private Button agregarButton;
    @FXML
    private Button editarButton;
    @FXML
    private Button consultarButton;
    @FXML
    private Button eliminarButton;
    @FXML
    private Button regresarButton;
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void agregar()  {
        System.out.println("Logica para agregar Elemento");;
    }
    @FXML
    private void editar()  {
        System.out.println("Logica para editar Elemento");;
    }
    @FXML
    private void eliminar()  {
        System.out.println("Logica para eliminar Elemento");;
    }
    @FXML
    private void consultar()  {
        System.out.println("Logica para consultar Elemento");;
    }
    
}