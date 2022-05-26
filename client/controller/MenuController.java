package client.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MenuController {
    
    public static int width = 1366;
    public static int height = 768;

    @FXML
    private Text textScore;

    @FXML
    private  AnchorPane mainRoot;

    public void setScore(int score) {
        textScore.setText(textScore.getText() + Integer.toString(score));
        textScore.setVisible(true);
    }

    @FXML
    public void onPlay() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Game.fxml"));
        AnchorPane pane = fxmlLoader.load();
        mainRoot.getChildren().setAll(pane);
    }
    
    @FXML 
    public void onSkin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Skin.fxml"));
        AnchorPane pane = fxmlLoader.load();
        mainRoot.getChildren().setAll(pane); 
    }

    @FXML
    public void onExit() throws IOException{
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void onScore() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Score.fxml"));
        AnchorPane pane = fxmlLoader.load();
        mainRoot.getChildren().setAll(pane);
    }
    @FXML
    public void onSettings() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Settings.fxml"));
        AnchorPane pane = fxmlLoader.load();
        mainRoot.getChildren().setAll(pane);
    }
}
