package client.model.gameloop;

import java.util.ArrayList;
import client.controller.elements.FoodController;
import client.model.elements.Base;
import client.model.elements.SnakeModel;
import client.model.map.GameMapModel;
import client.view.elements.SnakeView;
import client.view.map.GameMapView;
import javafx.application.Platform;

public class GameLoop implements Runnable {
    
    private Thread gameThread;
    public static boolean running = false;
    private double nx, ny;

    private SnakeModel snakeModel;
    private SnakeView snakeView;
    private GameMapModel mapModel;
    private GameMapView mapView;

    private ArrayList<FoodController> foods= new ArrayList<>();

    public GameLoop(SnakeModel snakeModel, SnakeView snakeView, GameMapModel mapModel, GameMapView mapView, ArrayList<FoodController> foods) {
        this.snakeModel = snakeModel;
        this.snakeView = snakeView;
        this.mapModel = mapModel;
        this.mapView = mapView;
        this.foods = foods;
    }

    public synchronized void start() {
        gameThread = new Thread(this, "Gameloop");
        gameThread.start();
        running = true;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double fps = 60.0;
        double ns = 1000000000 / fps;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				update();
				delta--;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        render();
                    }
                });
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		stop();
    }

    private void update() {
        mapModel.update(snakeModel);
        snakeModel.update();
        for (int i = 0; i < foods.size(); i++) {
            FoodController food = foods.get(i);
            food.getModel().update();
            if (food.getModel().getVisible() && collision(snakeModel, food.getModel())) {
                double added = snakeModel.eat(food.getModel());
                foods.remove(i);
                double newScale = GameMapModel.scale + added / (snakeModel.getWidth() * 4);
                if (newScale < 1.4) {
                    mapModel.setToScale(newScale);
                }
            }
        }
        if (mapModel.limit(snakeModel)) snakeModel.kill();
        if (!snakeModel.getIsAlive()) running = false;
    }

    private void render() {
        mapView.render();
        for (int i = 0; i < foods.size(); i++) {
            FoodController food = foods.get(i);
            food.getView().render();
        }
        snakeView.render();
    }

    private void stop() {
        gameThread.interrupt();
    }

    public void setControlInfo(double x, double y, boolean isSpeedUp) {
        if (isSpeedUp) snakeModel.speedUp();
        else snakeModel.speedDown();
        nx = (x + GameMapModel.view.getX()) * GameMapModel.scale;
        ny = (y + GameMapModel.view.getY()) * GameMapModel.scale;
        snakeModel.moveTo(nx, ny);
    }

    public boolean collision(Base obj1, Base obj2) {
        double distanceX = Math.abs(obj1.getX() - obj2.getX());
        double distanceY = Math.abs(obj1.getY() - obj2.getY());
        double distanceWidth = Math.abs(obj1.getWidth() - obj2.getWidth());
        double distanceHeight = Math.abs(obj1.getHeight() - obj2.getHeight());
        if (distanceX > distanceWidth || distanceY > distanceHeight) {
            return false;
        }
        return true;
    }
}