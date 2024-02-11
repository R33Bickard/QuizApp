module hftm.rubi {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires com.fasterxml.jackson.databind;

    opens hftm.rubi to javafx.fxml, com.fasterxml.jackson.databind;
    exports hftm.rubi;
}