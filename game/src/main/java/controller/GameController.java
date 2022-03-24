package controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import util.Cam;

public class GameController {

    private int w = MainController.width / 2;
    private int h = MainController.height / 2;
    private double speed = 2;
    private double dx, dy;

    @FXML
    private Pane gameRoot;

    private Cam camera;

    public void startGame(Scene scene) {
        camera = new Cam(scene);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                update();
                render();
            }
        };
        gameLoop.start();
    }

    public void update() {
        if(camera.getLastX() > w) {
            dx = speed;
        }
        else {
            dx = -speed;
        }
        if(camera.getLastY() > h) {
            dy = speed;
        }
        else {
            dy = -speed;
        }
    }

    public void render() {
        camera.move(dx, dy);
    }
}
