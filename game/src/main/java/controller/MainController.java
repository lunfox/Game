package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MainController {
    
    public static int width = 1366;
    public static int height = 768;

    @FXML
    private Pane mainRoot;

    @FXML
    private Scene mainScene;

    @FXML
    public void onPlay() throws Exception {
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        Pane pane = fxmlLoader.load();
        GameController gameController = fxmlLoader.<GameController>getController();
        gameController.startGame(mainScene);
        mainRoot.getChildren().setAll(pane);
        */
    }
}
