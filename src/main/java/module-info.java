module com.sef
{
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens com.sef to javafx.fxml;
    exports com.sef;
}
