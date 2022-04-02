package viewmodel;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import model.map.GameMap;

public class GameController {

    
    private double speed = 3;
    private double dx, dy;

    @FXML
    private AnchorPane gameRoot;

    @FXML
    private Canvas canvas;

    private GraphicsContext gContext;

    private MouseControl mouse;

    public void startGame(Scene scene) {
        /*
        gContext = canvas.getGraphicsContext2D();
        mouse = new MouseControl(scene);
        Snake snake = new Snake(100, 100);
        AnimationTimer a = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                gContext.clearRect(0, 0, 1366, 768);
                snake.moveTo(100, 100);
                snake.action();
                snake.render(gContext);
            }
        };
        a.start();
        */
        GameMap gameMap = new GameMap(canvas, 1366, 768);

    }
}
