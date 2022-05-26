module com.sef {
  requires transitive javafx.controls;
  requires javafx.fxml;
  requires com.fasterxml.jackson.databind;
  requires org.mongodb.driver.sync.client;
  requires org.mongodb.bson;
  requires org.mongodb.driver.core;
  requires spring.security.crypto;

  opens com.sef.frontend to javafx.graphics;
  opens com.sef.frontend.controllers to javafx.fxml;
  opens com.sef.backend.models to com.fasterxml.jackson.databind, javafx.base;

  exports com.sef ;
    opens com.sef.backend.controllers to javafx.fxml;
}
