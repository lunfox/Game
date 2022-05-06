package client.view.map;

import client.model.map.GameMapModel;
import client.model.view.View;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameMapView {
    
    private GameMapModel map;
    private GraphicsContext gContext;
    private Image tileImage = new Image("client/view/images/back.jpg");

    public GameMapView(GraphicsContext gContext, GameMapModel model) {
        this.map = model;
        this.gContext = gContext;
    }

    public void render() {
        clear();
        double tileHeight = tileImage.getHeight();
        double tileWidth = tileImage.getWidth();
        double paintHeight = map.getPaintHeight();
        double paintWidth = map.getPaintWidth();
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

    public void clear() {
        gContext.clearRect(0, 0, GameMapModel.view.getWidth(), GameMapModel.view.getHeight());
    }
}
