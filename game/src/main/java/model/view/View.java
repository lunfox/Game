package model.view;

import model.map.GameMap;

public class View {
    
    private GameMap gameMap;
    private double width, height;
    private double x, y;

    public View(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void trace(double x, double y) {
        this.x = (x / gameMap.getScale()) - width / 2;
        this.y = (y / gameMap.getScale()) - height / 2;
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
}
