package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent main = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        Scene scene = new Scene(main, 1366, 768);
        stage.setTitle("Название игры");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
