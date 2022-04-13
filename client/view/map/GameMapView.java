package client.view.map;

import client.model.map.GameMapModel;
import client.model.view.View;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameMapView {
    
    public static GraphicsContext gContext;
    private Image tileImage = new Image("client/view/images/back.jpg");

    public GameMapView(Canvas canvas, double vWidth, double vHeight) {
        gContext = canvas.getGraphicsContext2D();
        canvas.setWidth(vWidth);
        canvas.setHeight(vHeight);
    }

    public void render(double paintWidth, double paintHeight, double tileWidth, double tileHeight) {
        View view = GameMapModel.view;
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
                gContext.drawImage(this.tileImage, 0, 0, w * GameMapModel.scale, h * GameMapModel.scale, x, y, w, h);
            }
        }
    }

    public double getTileWidth() {
        return tileImage.getWidth();
    }

    public double getTileHeight() {
        return tileImage.getHeight();
    }

    public void clear(double width, double height) {
        gContext.clearRect(0, 0, width, height);
    }
}
