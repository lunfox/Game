module main {
    requires javafx.controls;
    requires javafx.fxml;

    opens viewmodel to javafx.graphics, javafx.fxml;
}
