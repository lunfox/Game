package viewmodel;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameController {

    
    private double speed = 3;
    private double dx, dy;

    @FXML
    private Pane world;

    private MouseControl mouse;

    public void startGame(Scene scene) {

        mouse = new MouseControl(scene);
    }
}
