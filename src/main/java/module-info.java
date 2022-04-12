module com.sef {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sef to javafx.fxml;
    exports com.sef;
}
