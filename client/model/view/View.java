package client.model.view;

import client.model.map.GameMapModel;

public class View {

    private double width, height;
    private double x = 0;
    private double y = 0;

    public View(double vWidth, double vHeight) {
        this.width = vWidth;
        this.height = vHeight;
    }

    public void trace(ViewTracker obj) {
        x = (obj.getX() / GameMapModel.scale) - width / 2;
        y = (obj.getY() / GameMapModel.scale) - height / 2;
    }
    
    public double absoluteX(double x) {
        return (x + this.x) * GameMapModel.scale;
    }

    public double absoluteY(double y) {
        return (y + this.y) * GameMapModel.scale;
    }

    public double relativeX(double x) {
        return (x / GameMapModel.scale) - this.x;
    }

    public double relativeY(double y) {
        return (y / GameMapModel.scale) - this.y;
    }

    public double relativeW(double width) {
        return width / GameMapModel.scale;
    }

    public double relativeH(double height) {
        return height / GameMapModel.scale;
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
