package client.model.elements;

import client.model.map.GameMapModel;

public abstract class BaseModel {

    protected double x, y;
    protected double paintX, paintY;
    protected double width, height;
    protected double paintWidth;
    protected double paintHeight;

    public BaseModel(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
    }

    public abstract void action();

    private void prepare() {
        this.paintX = GameMapModel.view.relativeX(x);
        this.paintY = GameMapModel.view.relativeY(y);
        this.paintWidth = GameMapModel.view.relativeW(width);
        this.paintHeight = GameMapModel.view.relativeH(height);
    }

    public void update() {
        prepare();
        action();
    }
}
