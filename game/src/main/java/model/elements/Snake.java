package model.elements;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/*
public class Snake {

    private int length;
    private double toAngle;

    private double vx, vy;

    private Circle circle;

    public Snake(Pane world, double x, double y) {
        circle = new Circle(50, Color.RED);
        world.getChildren().add(circle);
        this.x = x;
        this.y = y;
    }
    public void move(double nx, double ny) {
        double dx = nx - x;
        double dy = y - ny;
        double angle = Math.atan(Math.abs(dx / dy));

        if (dx > 0 && dy < 0) {
            angle = Math.PI - angle;
        } 
        else if (dx < 0 && dy < 0) {
            angle = Math.PI + angle;
        } 
        else if (dx < 0 && dy > 0) {
            angle = Math.PI * 2 - angle;
        }
          
        double oldAngle = Math.abs(toAngle % (Math.PI * 2));

        int rounds = (int)Math.floor(toAngle / (Math.PI * 2));

        toAngle = angle;

        if (oldAngle >= Math.PI * 3 / 2 && this.toAngle <= Math.PI / 2) {
            rounds++;
        } 
        else if (oldAngle <= Math.PI / 2 && this.toAngle >= Math.PI * 3 / 2) {
            rounds--;
        }

        this.toAngle += rounds * Math.PI * 2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void update(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void render() {
        circle.setLayoutX(x);
        circle.setLayoutY(y);
    }
}
*/
