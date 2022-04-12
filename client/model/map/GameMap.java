package client.model.map;

import client.model.view.View;
import client.model.view.ViewTracker;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameMap {

    public static GraphicsContext gContext;
    public static View view;
    public static double scale = 1;
    private double width = 10000;
    public static double height = 10000;

    private double paintWidth;
    private double paintHeight;

    private double toScale = 1;
    private Image tileImage = new Image("client/view/images/back.jpg");
    
    public GameMap(Canvas canvas, double vWidth, double vHeight) {
        gContext = canvas.getGraphicsContext2D();
        canvas.setWidth(vWidth);
        canvas.setHeight(vHeight);
        paintSizeReset();
        view = new View(vWidth, vHeight);
    }

    public double relative(double value) {
        return value / scale;
    }

    public void setScale(double scale) {
        if (GameMap.scale == scale) {
          return;
        }
        GameMap.scale = scale < 1 ? 1 : scale;
        this.paintSizeReset();
    }

    private void paintSizeReset() {
        paintWidth = relative(width);
        paintHeight = relative(height);
    }

    public void update(ViewTracker obj) {
        if (toScale  > 0 && scale != toScale) {     
            setScale(toScale);
        }
        gContext.clearRect(0, 0, view.getWidth(), view.getHeight());
        view.trace(obj);
        render();
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
                gContext.drawImage(this.tileImage, 0, 0, w * GameMap.scale, h * GameMap.scale, x, y, w, h);
            }
        }
    }
}
