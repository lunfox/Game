package client.view.elements;

import client.model.elements.FoodModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class FoodView {
    
    private FoodModel food;
    private Color color = Color.color(Math.random(), Math.random(), Math.random());
    private GraphicsContext gContext;

    public FoodView(FoodModel food, GraphicsContext gContext) {
        this.food = food;
        this.gContext = gContext;
    }

    public void render() {
        if (!food.getVisible()) {
            return;
        }
        gContext.save();
        gContext.setEffect(new DropShadow(food.getLightSize(), color));
        gContext.setFill(color);
        gContext.beginPath();
        gContext.fillOval(food.getPaintX(), food.getPaintY(), 
        food.getPaintWidth() / 2, 
        food.getPaintHeight() / 2);
        gContext.restore();
    } 
}
