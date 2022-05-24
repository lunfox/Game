package client.model.elements;

import client.model.map.GameMapModel;

public abstract class Base {

    protected double x, y;
    protected double paintX, paintY;
    protected double width, height;
    protected double paintWidth;
    protected double paintHeight;
    protected boolean visible;

    public Base(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void action();

    private void prepare() {
        this.paintX = GameMapModel.view.relativeX(x);
        this.paintY = GameMapModel.view.relativeY(y);
        this.paintWidth = GameMapModel.view.relativeW(width);
        this.paintHeight = GameMapModel.view.relativeH(height);
        double halfWidth = paintWidth / 2;
        double halfHeight = paintHeight / 2;
        visible = (paintX + halfWidth > 0) && 
        (paintX - halfWidth < GameMapModel.view.getWidth()) &&
        (paintY + halfHeight > 0) &&
        (paintY - halfHeight < GameMapModel.view.getHeight());
    }

    public void update() {
        prepare();
        action();
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

    public double getPaintX() {
        return paintX;
    }

    public double getPaintY() {
        return paintY;
    }

    public double getPaintHeight() {
        return paintHeight;
    }

    public double getPaintWidth() {
        return paintWidth;
    }
}
