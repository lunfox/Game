package client.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SkinController {

    @FXML
    ColorPicker colorPicker;
    
    @FXML
    private AnchorPane mainRoot;

    @FXML
    public void initialize() {
        colorPicker.setOnAction(event -> {
            colorPicker.setStyle("-fx-background-color:" + toHexString(colorPicker.getValue()));
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
    private void onBack() throws IOException
    {
    AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
    mainRoot.getChildren().setAll(pane);
    }

}