package model.elements;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import model.map.GameMap;
import model.view.ViewTracker;

public class Snake extends Base implements ViewTracker{
   
    private double BASE_ANGLE = Math.PI * 200;
    private double SPEED = 4;

    private double speed = SPEED;
    private double oldSpeed = SPEED;

    private double angle = Math.PI * 2 * Math.random();
    private double toAngle = angle;

    private Image img;

    private double length = 280;
    private double maxBodyLen;
    private double turnSpeed = 0.06;
    private double vx = 0;
    private double vy = 0;

    private ArrayList<Movement> body = new ArrayList<>();

    public Snake(double x, double y, double size) {
        super(x, y, size);
        updateSize(0);
        velocity();
    }

    private void turnAround() {
        double angleDistance = toAngle - angle;
        if (Math.abs(angleDistance) <= turnSpeed) {
            toAngle = angle = BASE_ANGLE + toAngle % (Math.PI * 2);
        } else {
            angle += Integer.signum((int)angleDistance) * turnSpeed;
        }
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

    @Override
    public void action() {
        body.add(new Movement(x, y, speed, angle));
        if (body.size() > maxBodyLen) {
            body.remove(0);
        }
        this.turnAround();
        this.velocity();
        this.x += this.vx;
        this.y += this.vy;
    }

    @Override
    public void render() {
        GraphicsContext gContext = GameMap.gContext;
        gContext.save();
        gContext.beginPath();
        gContext.moveTo(paintX, paintY);
        double wholeLength = this.length;
        if (body.size() > 0) {
            int i = body.size() - 1;
            while (i > 0) {
                Movement movement = body.get(i);
                double x = movement.x;
                double y = movement.y;
                if (wholeLength > 0 && wholeLength < movement.speed) {
                    double lx = body.get(i + 1).x;
                    double ly = body.get(i + 1).y;
                    double ratio = wholeLength / movement.speed;
                    x = lx - (lx - x) * ratio;
                    y = ly - (ly - y) * ratio;
                } else if (wholeLength < 0) {
                break;
            }
            i--;
            wholeLength -= movement.speed;
            gContext.lineTo(GameMap.view.relativeX(x), GameMap.view.relativeY(y));
            }
        }
        gContext.setLineCap(StrokeLineCap.ROUND);
        gContext.setLineJoin(StrokeLineJoin.ROUND);
        gContext.setStroke(Color.GREEN);
        gContext.setLineWidth(width);
        gContext.stroke();
        gContext.restore();

        gContext.save();
        gContext.translate(paintX, paintY);
        gContext.rotate(angle);
        gContext.drawImage(
        img,
        -paintWidth / 2,
        -paintHeight / 2,
        paintWidth,
        paintHeight
        );
        gContext.restore();
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

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void updateSize(double added) {
        width += added;
        height += added;
        length += added * 50;
        turnSpeed -= added / 1000;
        maxBodyLen = Math.ceil(this.length / oldSpeed);
    }
}
