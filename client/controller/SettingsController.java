package client.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import client.App;

public class SettingsController{
    @FXML
    private Button Resulation_1366x768;

    @FXML
    private Button Resulation_1920x1080;


    @FXML
    private AnchorPane mainRoot = new AnchorPane();

    @FXML
    void onBack(MouseEvent event) throws IOException
     {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
        mainRoot.getChildren().setAll(pane);
    }

    @FXML
    void on_1366x768(MouseEvent event){
        App.stateStage.setWidth(1366);
        App.stateStage.setHeight(768);
    }

    @FXML
    void on_1920x1080(MouseEvent event) {
        App.stateStage.setWidth(1920);
        App.stateStage.setHeight(1080);
    }

}

    