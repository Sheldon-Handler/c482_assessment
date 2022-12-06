/**
 * modules required
 */
module com.example.c482_assessment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

  //  requires org.controlsfx.controls;

    opens com.example.c482_assessment to javafx.fxml;
    exports com.example.c482_assessment;
}