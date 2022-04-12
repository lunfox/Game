package client.controller;

import client.model.elements.Snake;
import client.model.map.GameMap;
//import client.model.view.Observer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

public class GameController {

    @FXML
    private AnchorPane gameRoot;

    @FXML
    private Canvas canvas;
    
    private MouseControl mouse;

    private GameMap gameMap;

    //private Observer player;
    private Snake playerSnake;

    public void startGame(Scene scene) {
        mouse = new MouseControl(scene);
        gameMap = new GameMap(canvas, 1366, 768);
        //player = new Observer(GameMap.width / 2, GameMap.height / 2);
        playerSnake = new Snake(100, 100, 30);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                gameMap.update(playerSnake);
                playerSnake.update();
                binding();
            }
        };
        gameLoop.start();
    }

    private void binding() {
        double nx = (mouse.getX() + GameMap.view.getX()) * GameMap.scale;
        double ny = (mouse.getY() + GameMap.view.getY()) * GameMap.scale;
        playerSnake.moveTo(nx, ny);
    }
}
