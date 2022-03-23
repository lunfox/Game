package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import player.Player;

public class GameController {

    private Player player;

    @FXML
    private Text stat;

    @FXML
    private Pane gameRoot;

    public void startGame(Scene scene) {
        player = new Player(scene);
        Circle circle = new Circle(25, Color.BLUE);
        gameRoot.getChildren().add(circle);
        scene.setOnMouseMoved(e -> {
            player.move(1, 1, e, circle);
         }); 

    }
}
