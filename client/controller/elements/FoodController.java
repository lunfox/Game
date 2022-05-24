package client.controller.elements;

import client.model.elements.FoodModel;
import client.view.elements.FoodView;

public class FoodController {
    
    private FoodModel model;
    private FoodView view;

    public FoodController(FoodModel model, FoodView view) {
        this.model = model;
        this.view = view;
    }

    public FoodModel getModel() {
        return model;
    }

    public FoodView getView() {
        return view;
    }
}
