package client.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ScoreController {
    @FXML 
    private AnchorPane mainRoot;

    @FXML
    private void onBack() throws IOException{
    AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
    mainRoot.getChildren().setAll(pane);
}
}
