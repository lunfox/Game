package player;

import controller.MainController;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Player {
    
    private Camera camera;
    private double targetX, targetY;

    public Player(Scene scene) {
        camera = new PerspectiveCamera();
        scene.setCamera(camera);
    }

    public void move(double dx, double dy, MouseEvent event, Circle circle) {
        int w = MainController.width / 2;
        int h = MainController.height / 2;
        if(event.getX() - (camera.getLayoutX() + w) > 0) {
            targetX = camera.getLayoutX() + dx;
        }
        else {
            targetX = camera.getLayoutX() - dx;
        }
        if(event.getY() - (camera.getLayoutY() + h) > 0) {
            targetY = camera.getLayoutY() + dy;
        }
        else {
            targetY = camera.getLayoutY() - dy;
        }
        camera.setLayoutX(targetX);
        circle.setLayoutX(targetX + w);
        camera.setLayoutY(targetY);
        circle.setLayoutY(targetY + h);
    }
}
