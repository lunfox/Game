package client.model.elements;

import java.util.ArrayList;

import client.model.map.GameMapModel;
import client.model.view.ViewTracker;

public class SnakeModel extends BaseModel implements ViewTracker {

    private double BASE_ANGLE = Math.PI * 200;
    private double SPEED = 4;
    private double speed = SPEED;
    private double oldSpeed = SPEED;
    private double angle = BASE_ANGLE;
    private double toAngle = angle;
    private double length = 100;
    private double maxBodyLen;
    private double turnSpeed = 0.06;
    private double vx = 0;
    private double vy = 0;
    private boolean isSpeedUp = false;
    private double point = 0;
    private boolean isAlive = true;
    private ArrayList<Movement> body = new ArrayList<>();

    public SnakeModel(double x, double y, double size) {
        super(x, y, size);
        updateSize(0);
        velocity();
    }

    private void updateSize(double added) {
        width += added;
        height += added;
        length += added * 50;
        turnSpeed -= added / 1000;
        maxBodyLen = Math.ceil(this.length / oldSpeed);
    }

    private void velocity() {
        double angle  = this.angle % (Math.PI * 2);
        double vx = Math.abs(speed * Math.sin(angle));
        double vy = Math.abs(speed * Math.cos(angle));
    
        if (angle < Math.PI / 2) {
            this.vx = vx;
            this.vy = -vy;
        } else if (angle < Math.PI) {
            this.vx = vx;
            this.vy = vy;
        } else if (angle < Math.PI * 3 / 2) {
            this.vx = -vx;
            this.vy = vy;
        } else {
            this.vx = -vx;
            this.vy = -vy;
        }
    }

    private void turnAround() {
        double angleDistance = toAngle - angle;
        if (Math.abs(angleDistance) <= turnSpeed) {
            toAngle = angle = BASE_ANGLE + toAngle % (Math.PI * 2);
        } else {
            angle += Integer.signum((int)angleDistance) * turnSpeed;
        }
    }

    public void action() {
        if (!isAlive) {
            return;
        }
        body.add(new Movement(x, y, speed, angle));
        if (body.size() > maxBodyLen) {
            body.remove(0);
        }
        this.turnAround();
        this.velocity();
        this.x += this.vx;
        this.y += this.vy;
        if (GameMapModel.limit(x, y)) isAlive = false;
    }

    public void moveTo(double nx, double ny) {
        double x = nx - this.x;
        double y = this.y - ny;
        double angle = Math.atan(Math.abs(x / y));

        if (x > 0 && y < 0) {
            angle = Math.PI - angle;
        } else if (x < 0 && y < 0) {
            angle = Math.PI + angle;
        } else if (x < 0 && y > 0) {
            angle = Math.PI * 2 - angle;
        }
    
        double oldAngle = Math.abs(this.toAngle % (Math.PI * 2));
        int rounds = (int) Math.floor(this.toAngle / (Math.PI * 2));
        toAngle = angle;
        if (oldAngle >= Math.PI * 3 / 2 && toAngle <= Math.PI / 2) {
            rounds++;
        } else if (oldAngle <= Math.PI / 2 && toAngle >= Math.PI * 3 / 2) {
            rounds--;
        }
        this.toAngle += rounds * Math.PI * 2;
    }

    public double getLength() {
        return length;
    }

    public ArrayList<Movement> getBody() {
        return body;
    }

    public double getSize() {
        return width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void speedUp() {
        if (isSpeedUp) {
            return;
        }
        isSpeedUp = true;
        oldSpeed = speed;
        speed *= 2;
    }

    public double getPaintX() {
        return paintX;
    }

    public double getPaintY() {
        return paintY;
    }

    public void speedDown() {
        if (!isSpeedUp) {
            return;
        }
        isSpeedUp = false;
        speed = oldSpeed;
    }

    public double eat(Food food) {
        point += food.getPoint();
        double added = food.getPoint() / 200;
        updateSize(added);
        return added;
    }

    public boolean getIsAlive() {
        return isAlive;
    }
}
