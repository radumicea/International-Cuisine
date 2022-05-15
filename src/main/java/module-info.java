module com.sef
{
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens com.sef.gui to javafx.graphics;
    opens com.sef.gui.controllers to javafx.fxml;
    exports com.sef;
}
