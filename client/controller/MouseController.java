package client.controller;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

public class MouseController {
    
    private double x, y;
    private boolean isBoost;

    public MouseController(Canvas scene) {
        scene.setCursor(Cursor.NONE);
        scene.setOnMouseMoved(event -> {
            x = event.getScreenX();
            y = event.getScreenY();
        });
        scene.setOnMousePressed(event -> {
            isBoost = true;
            x = event.getScreenX();
            y = event.getScreenY();
        });
        scene.setOnMouseReleased(event -> {
            isBoost = false;
            x = event.getScreenX();
            y = event.getScreenY();
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
