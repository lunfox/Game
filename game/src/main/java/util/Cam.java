package util;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;

public class Cam {
    
    private Camera camera;
    private double lastX = 0, lastY = 0;
    private double x = 0, y = 0;

    public Cam(Scene scene) {
        camera = new PerspectiveCamera();
        scene.setCamera(camera);
        scene.setOnMouseMoved(event -> {
            lastX = event.getScreenX();
            lastY = event.getScreenY();
        });
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
        camera.setLayoutX(x);
        camera.setLayoutY(y);
    }

    public double getLastX() {
        return lastX;
    }

    public double getLastY() {
        return lastY;
    }
}
