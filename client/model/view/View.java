package client.model.view;

import client.model.map.GameMap;

public class View {
    
    private GameMap gameMap;
    private double width, height;
    private double x = 0;
    private double y = 0;

    public View(GameMap gameMap, double vWidth, double vHeight) {
        this.gameMap = gameMap;
        this.width = vWidth;
        this.height = vHeight;
    }

    public void trace(ViewTracker obj) {
        x = (obj.getX() / gameMap.getScale()) - width / 2;
        y = (obj.getY() / gameMap.getScale()) - height / 2;
    }
    
    public double absoluteX(double x) {
        return (x + this.x) * gameMap.getScale();
    }

    public double absoluteY(double y) {
        return (y + this.y) * gameMap.getScale();
    }

    public double relativeX(double x) {
        return (x / gameMap.getScale()) - this.x;
    }

    public double relativeY(double y) {
        return (y / gameMap.getScale()) - this.y;
    }

    public double relativeW(double width) {
        return width / gameMap.getScale();
    }

    public double relativeH(double height) {
        return height / gameMap.getScale();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
