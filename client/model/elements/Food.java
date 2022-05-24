package client.model.elements;

import client.model.map.GameMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Food extends Base {

    private double point;
    private double lightSize;
    private boolean lightDirection = true;
    private Color color = Color.color(Math.random(), Math.random(), Math.random());

    public Food(double x, double y, double size, double point) {
        super(x, y, size, size);
        this.point = point;
        lightSize = size / 2;
    }

    @Override
    public void action() {
      
        double lightSpeed = 1;
        lightSize += lightDirection ? lightSpeed : -lightSpeed;

        if (lightSize > width || lightSize < width / 2) {
            lightDirection = !lightDirection;
        }
    }

    public void render() {
        GraphicsContext gContext = GameMap.gContext;
        gContext.save();
        gContext.setEffect(new DropShadow(lightSize, color));
        gContext.setFill(color);
        gContext.beginPath();
        gContext.fillOval(paintX, paintY, 
        this.paintWidth / 2, 
        this.paintHeight / 2);
        gContext.restore();
    }     

    public double getPoint() {
        return point;
    }
}
