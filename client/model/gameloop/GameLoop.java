package client.model.gameloop;

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

    public GameLoop(SnakeModel snakeModel, SnakeView snakeView, GameMapModel mapModel, GameMapView mapView) {
        this.snakeModel = snakeModel;
        this.snakeView = snakeView;
        this.mapModel = mapModel;
        this.mapView = mapView;
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
        if (!snakeModel.getIsAlive()) running = false;
    }

    private void render() {
        mapView.render();
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
}
