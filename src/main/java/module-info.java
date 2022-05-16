module com.sef {
  requires transitive javafx.controls;
  requires javafx.fxml;
  requires com.fasterxml.jackson.databind;

  opens com.sef.frontend to javafx.graphics;
  opens com.sef.frontend.controllers to javafx.fxml;
  opens com.sef.backend.models to com.fasterxml.jackson.databind;

  exports com.sef ;
}
