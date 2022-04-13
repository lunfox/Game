package client.controller.map;

import client.model.map.GameMapModel;
import client.model.view.ViewTracker;
import client.view.map.GameMapView;

public class GameMapController {
    
    private GameMapModel model;
    private GameMapView view;

    public GameMapController(GameMapModel model, GameMapView view) {
        this.model = model;
        this.view = view;
    }

    public void update(ViewTracker obj) {
        model.update(obj);
    }

    public void render() {
        view.clear(GameMapModel.view.getWidth(), GameMapModel.view.getHeight());
        view.render(
        model.getPaintWidth(), 
        model.getPaintHeight(), 
        GameMapModel.relative(view.getTileWidth()),
        GameMapModel.relative(view.getTileHeight()));
    }

}
