package model.view;

import model.map.GameMap;

public class Observer extends IObserver{
    private double vx, vy;
    private double SPEED = 4;

    public Observer(double x, double y) {
        super(x, y);
    }

    public void stop() {
        vx = 0;
        vy = 0;
    }

    @Override
    public void moveTo(double nx, double ny) {
        double mx = GameMap.view.relativeX(nx);
        double my = GameMap.view.relativeY(ny);
        double ox = GameMap.view.relativeX(x);
        double oy = GameMap.view.relativeY(y);
        double xc = mx - ox;
        double yc = my - oy;
        double hyp = Math.sqrt(xc * xc + yc * yc);
        double ratio = SPEED * GameMap.scale / hyp;

        vx = xc * ratio;
        vy = yc * ratio;
    }

    @Override
    public void update() {
        x += vx;
        y += vy;
    }
}
