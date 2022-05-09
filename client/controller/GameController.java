package client.controller;

import java.io.IOException;

import client.model.elements.SnakeModel;
import client.model.gameloop.GameLoop;
import client.model.map.GameMapModel;
import client.view.elements.SnakeView;
import client.view.map.GameMapView;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

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
        canvas.setWidth(MenuController.width);
        canvas.setHeight(MenuController.height);

        GameMapModel mapModel = new GameMapModel(1366, 768);
        GameMapView mapView = new GameMapView(gContext, mapModel);
        SnakeModel snakeModel = new SnakeModel(1000, 1000, 30);
        SnakeView snakeView = new SnakeView(snakeModel, SkinController.color, gContext);

        gameLoop = new GameLoop(snakeModel, snakeView, mapModel, mapView);
        
        mouse = new MouseController(canvas);
        javafxLoop = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                gameLoop.setControlInfo(mouse.getX(), mouse.getY(), mouse.getIsBoost());
                if (!GameLoop.running) endGame(snakeModel.getPoint());
            }
        };
        javafxLoop.start();

        gameLoop.start();
    }

    private void endGame(int score) {
        javafxLoop.stop();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
            AnchorPane pane;
            pane = fxmlLoader.load();
            gameRoot.getChildren().setAll(pane);
            MenuController controller = fxmlLoader.getController();
            controller.setScore(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
