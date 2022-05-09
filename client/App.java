package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("./view/Menu.fxml"));
        Scene scene = new Scene(pane);
        stage.setTitle("snake.io");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
