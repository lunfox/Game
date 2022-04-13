package client.view.elements;

import java.util.ArrayList;

import client.model.elements.Movement;
import client.model.map.GameMapModel;
import client.view.map.GameMapView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeView {

    public void render(ArrayList<Movement> body, double length, double size) {
        GraphicsContext gContext = GameMapView.gContext;
        gContext.save();
        double wholeLength = length;
        if (body.size() > 0) {
            int i = 0;
            while (i != (body.size() - 1)) {
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
            i++;
            wholeLength -= movement.speed;
            if (i % 6 > 3) gContext.setFill(Color.WHITE);
            else gContext.setFill(Color.BLACK);
            gContext.fillOval(GameMapModel.view.relativeX(x), GameMapModel.view.relativeY(y), size, size);
            }
        }
        gContext.restore();
    }
}
