package client.controller;

import java.io.IOException;
import java.net.URL;

import client.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SkinController {

    @FXML
    ColorPicker colorPicker;

    public void init() {
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
    void onBack(MouseEvent event)
    {

    }

}