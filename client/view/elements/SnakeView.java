package client.view.elements;

import java.util.ArrayList;
import client.model.elements.Movement;
import client.model.elements.SnakeModel;
import client.model.map.GameMapModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class SnakeView {

    private SnakeModel snake;
    private Color color;
    private GraphicsContext gContext;

    public SnakeView(SnakeModel model, Color color, GraphicsContext gContext) {
        this.snake = model;
        this.color = color;
        this.gContext = gContext;
    }

    public void render() {
        ArrayList<Movement> body = snake.getBody();
        double length = snake.getLength();
        double size = snake.getSize();
        double paintX = snake.getPaintX();
        double paintY = snake.getPaintY();
        gContext.save();
        gContext.beginPath();
        gContext.moveTo(paintX, paintY);
        double wholeLength = length;
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
            gContext.lineTo(GameMapModel.view.relativeX(x), GameMapModel.view.relativeY(y));
            }
        }
        gContext.setLineCap(StrokeLineCap.ROUND);
        gContext.setLineJoin(StrokeLineJoin.ROUND);
        gContext.setStroke(color);
        gContext.setLineWidth(size);
        gContext.stroke();
        gContext.restore();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
