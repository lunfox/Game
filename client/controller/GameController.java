package client.controller;

import client.controller.elements.SnakeController;
import client.controller.map.GameMapController;
import client.model.elements.SnakeModel;
import client.model.map.GameMap;
import client.model.map.GameMapModel;
import client.view.elements.SnakeView;
import client.view.map.GameMapView;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

public class GameController {

    @FXML
    private AnchorPane gameRoot;

    @FXML
    private Canvas canvas;
    
    private MouseControl mouse;

    //private GameMap gameMap;
    private GraphicsContext gContext;

    private SnakeController snake;
    private GameMapController map;

    public void startGame(Scene scene) {
        gContext = canvas.getGraphicsContext2D();
        snake = new SnakeController(new SnakeModel(100, 100, 30), new SnakeView());
        map = new GameMapController(new GameMapModel(1366, 768), new GameMapView(canvas, 1366, 768));
        mouse = new MouseControl(scene);
        //gameMap = new GameMap(canvas, 1366, 768);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                //gameMap.update(snake);
                map.update(snake);
                map.render();
                snake.update();
                snake.render();
                binding();
            }
        };
        gameLoop.start();
    }

    private void binding() {
        double nx = (mouse.getX() + GameMapModel.view.getX()) * GameMapModel.scale;
        double ny = (mouse.getY() + GameMapModel.view.getY()) * GameMapModel.scale;
        snake.moveTo(nx, ny);
    }
}
