module hftm.rubi {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens hftm.rubi to javafx.fxml, org.json, junit;
    exports hftm.rubi;
}
