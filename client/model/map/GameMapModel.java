package client.model.map;

import client.model.view.View;
import client.model.view.ViewTracker;

public class GameMapModel {
    
    public static double scale;
    public static View view;
    private double width = 2000;
    private double height = 2000;
    private double paintWidth;
    private double paintHeight;
    private double toScale = 1;

    public GameMapModel(double vWidth, double vHeight) {
        paintSizeReset();
        view = new View(vWidth, vHeight);
    }

    private void paintSizeReset() {
        paintWidth = relative(width);
        paintHeight = relative(height);
    }

    public static double relative(double value) {
        return value / scale;
    }

    public void setScale(double scale) {
        if (GameMapModel.scale == scale) {
            return;
        }
        GameMapModel.scale = scale < 1 ? 1 : scale;
        paintSizeReset();
    }

    public void setToScale(double toScale) {
        this.toScale = toScale;
    }

    public void update(ViewTracker obj) {
        if (toScale > 0 && scale != toScale) {     
            setScale(toScale);
        }
        view.trace(obj);
    }

    public double getPaintWidth() {
        return paintWidth;
    }

    public double getPaintHeight() {
        return paintHeight;
    }

    public boolean limit(ViewTracker obj) {
        double x = obj.getX();
        double y = obj.getY();
        if (x < 0 || x > width || y < 0 || y > height) {
            return true;
        } else return false;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
