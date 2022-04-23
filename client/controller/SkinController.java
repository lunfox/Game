package client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
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
}