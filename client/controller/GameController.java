package client.controller;

import client.model.elements.SnakeModel;
import client.model.gameloop.GameLoop;
import client.model.map.GameMapModel;
import client.view.elements.SnakeView;
import client.view.map.GameMapView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class GameController {

    @FXML
    private AnchorPane gameRoot;

    @FXML
    private Canvas canvas;
    
    private GraphicsContext gContext;

    private MouseController mouse;

    private GameLoop gameLoop;

    private AnimationTimer javafxLoop;


    @FXML
    public void initialize() {
        gContext = canvas.getGraphicsContext2D();
        canvas.setWidth(MainController.width);
        canvas.setHeight(MainController.height);

        GameMapModel mapModel = new GameMapModel(1366, 768);
        GameMapView mapView = new GameMapView(gContext, mapModel);
        SnakeModel snakeModel = new SnakeModel(1000, 1000, 30);
        SnakeView snakeView = new SnakeView(snakeModel, Color.ALICEBLUE, gContext);

        gameLoop = new GameLoop(snakeModel, snakeView, mapModel, mapView);
        
        mouse = new MouseController(canvas);
        javafxLoop = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                gameLoop.setControlInfo(mouse.getX(), mouse.getY(), mouse.getIsBoost());
                if (!GameLoop.running) endGame();
            }
        };
        javafxLoop.start();

        gameLoop.start();
    }

    private void endGame() {
        javafxLoop.stop();
        System.out.println("Конец игры");
        Platform.exit();
    }
}
