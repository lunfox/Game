module main {
    requires javafx.controls;
    requires javafx.fxml;

    opens main to javafx.graphics;
    opens controller to javafx.fxml;
}
