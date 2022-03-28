package viewmodel;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
/*
public class Player {
    
    private Group group;
    private Camera camera;
    private Snake snake;

    public Player(Scene scene, Pane world, Snake snake) {
        this.snake = snake;
        camera = new PerspectiveCamera();
        scene.setCamera(camera);
        group = new Group(camera);
        world.getChildren().add(group);
    }

    public void update(double dx, double dy) {
        snake.update(dx, dy);
    }

    public void render() {
        group.setLayoutX(snake.getX() - MainController.w);
        group.setLayoutY(snake.getY() - MainController.h);
        snake.render();
    }
}
*/
