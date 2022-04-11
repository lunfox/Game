package client.model.map;

import client.model.view.View;
import client.model.view.ViewTracker;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameMap {

    private Canvas canvas;
    public static GraphicsContext gContext;
    public static View view;
    public static double scale = 1;
    public static double width, height;

    private double paintWidth;
    private double paintHeight;

    private double toScale = 1;
    private Image tileImage = new Image("client/view/images/back.png", 100, 100, false, false);
    
    public GameMap(Canvas canvas, double vWidth, double vHeight) {
        this.canvas = canvas;
        gContext = canvas.getGraphicsContext2D();
        canvas.setWidth(vWidth);
        canvas.setHeight(vHeight);
        paintSizeReset();
        view = new View(this, vWidth, vHeight);
    }

    public double getScale() {
        return scale;
    }

    public double relative(double value) {
        return value / scale;
    }

    public void setScale(double scale) {
        if (this.scale == scale) {
          return;
        }
        this.scale = scale < 1 ? 1 : scale;
        this.paintSizeReset();
    }

    private void paintSizeReset() {
        paintWidth = relative(width);
        paintHeight = relative(height);
    }

    public void update(ViewTracker obj) {
        if (toScale  > 0 && scale != toScale) {     
            this.setScale(this.toScale);
        }
        gContext.clearRect(0, 0, view.getWidth(), view.getHeight());
        render();
        view.trace(obj);
    }

    public void render() {
        double tileWidth = relative(tileImage.getWidth());
        double tileHeight = relative(tileImage.getHeight());
        double beginX = (view.getX() < 0) ? -view.getX() : (-view.getX() % tileWidth);
        double beginY = (view.getY() < 0) ? -view.getY() : (-view.getY() % tileHeight);
        double endX = (view.getX() + view.getWidth() > paintWidth) ? (paintWidth - view.getX()) : (beginX + view.getWidth() + tileWidth);
        double endY = (view.getY() + view.getHeight() > paintHeight) ? (paintHeight - view.getY()) : (beginY + view.getHeight() + tileHeight);
        
        for (double x = beginX; x <= endX; x += tileWidth) {
            for (double y = beginY; y <= endY; y += tileHeight) {
                double cx = endX - x;
                double cy = endY - y;
                double w = cx < tileWidth ? cx : tileWidth;
                double h = cy < tileHeight ? cy : tileHeight;
                //System.out.println(cx + " " + cy + " " + endX + " " + endY);
                //gContext.drawImage(this.tileImage, 0, 0, w * this.scale, h * this.scale, x, y, w, h);
                gContext.drawImage(this.tileImage, 0, 0, 200, 200, x, y, 100, 100);
            }
        }
    }
}
