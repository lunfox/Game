package model.view;

public abstract class IObserver {
    
    protected double x, y;
    public abstract void moveTo(double nx, double ny);
    public abstract void update();

    public IObserver(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
