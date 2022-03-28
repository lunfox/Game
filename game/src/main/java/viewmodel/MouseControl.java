package viewmodel;

import javafx.scene.Scene;

public class MouseControl {
    
    private double x, y;
    private boolean isBoost;

    public MouseControl(Scene scene) {
        scene.setOnMouseMoved(event -> {
            x = event.getScreenX();
            y = event.getScreenY();
        });
        scene.setOnMousePressed(event -> {
            isBoost = true;
        });
        scene.setOnMouseReleased(event -> {
            isBoost = false;
        });
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean getIsBoost() {
        return isBoost;
    }
}
