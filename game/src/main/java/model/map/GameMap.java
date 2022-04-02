package model.map;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.view.View;

public class GameMap {
    
    private Canvas canvas;
    private GraphicsContext gContext;
    public static View view;

    private double scale = 1;
    
    public GameMap(Canvas canvas, double vWidth, double vHeight) {
        this.canvas = canvas;
        gContext = canvas.getGraphicsContext2D();
        canvas.setWidth(vWidth);
        canvas.setHeight(vHeight);
        view = new View(this);
    }

    public double getScale() {
        return scale;
    }
}
