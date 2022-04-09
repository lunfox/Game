package model.elements;

import model.map.GameMap;

public abstract class Base {
    
    protected double x, y;
    protected double paintX, paintY;
    protected double width, height;
    protected double paintWidth;
    protected double paintHeight;

    public Base(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
    }

    public abstract void action();

    public abstract void render();

    private void prepare() {
        this.paintX = GameMap.view.relativeX(x);
        this.paintY = GameMap.view.relativeY(y);
        this.paintWidth = GameMap.view.relativeW(width);
        this.paintHeight = GameMap.view.relativeH(height);
    }

    public void update() {
        prepare();
        action();
        render();
    }
}
