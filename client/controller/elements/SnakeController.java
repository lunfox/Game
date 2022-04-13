package client.controller.elements;

import client.model.elements.SnakeModel;
import client.model.view.ViewTracker;
import client.view.elements.SnakeView;

public class SnakeController implements ViewTracker {
    
    private SnakeView view;
    private SnakeModel model;

    public SnakeController(SnakeModel model, SnakeView view) {
        this.view = view;
        this.model = model;
    }

    public void update() {
        model.update();
    }

    public void moveTo(double nx, double ny) {
        model.moveTo(nx, ny);
    }

    public void render() {
        view.render(model.getBody(), model.getLength(), model.getSize());
    }

    public double getX() {
        return model.getX();
    }

    public double getY() {
        return model.getY();
    }
}
