package client.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class SkinController {

    public static Color color = Color.color(Math.random(), Math.random(), Math.random());

    @FXML
    ColorPicker colorPicker;
    
    @FXML
    private AnchorPane mainRoot;

    @FXML
    public void initialize() {
        colorPicker.setValue(color);
        colorPicker.setStyle("-fx-background-color:" + toHexString(color));
        colorPicker.setOnAction(event -> {
            color = colorPicker.getValue();
            colorPicker.setStyle("-fx-background-color:" + toHexString(color));
        });
    }

    private String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }
    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    @FXML
    private void onBack() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
        mainRoot.getChildren().setAll(pane);
    }

}