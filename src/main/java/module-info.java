module com.sef {
  requires transitive javafx.controls;
  requires javafx.fxml;
  requires com.fasterxml.jackson.databind;

  opens com.sef to javafx.graphics;
  opens com.sef.controllers to javafx.fxml;
  exports com.sef ;
}
