package model.elements;

public abstract class Base {
    
    protected double x, y;
    protected double paintX, paintY;
    protected double width, height;
    protected double paintWidth;
    protected double paintHeight;

    public Base(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
