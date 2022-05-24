package client.model.elements;

public class FoodModel extends Base {
    
    private double point;
    private double lightSize;
    private boolean lightDirection = true;

    public FoodModel(double x, double y, double size, double point) {
        super(x, y, size, size);
        this.point = point;
        lightSize = size / 2;
    }

    @Override
    public void action() {
        if (!this.visible) {
            return;
        }
        double lightSpeed = 1;
        lightSize += lightDirection ? lightSpeed : -lightSpeed;
        if (lightSize > width || lightSize < width / 2) {
            lightDirection = !lightDirection;
        }
    }

    public double getPoint() {
        return point;
    }

    public double getLightSize() {
        return lightSize;
    }

    public boolean getVisible() {
        return visible;
    }
}
