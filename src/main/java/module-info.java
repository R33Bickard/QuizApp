module hftm.rubi {
    requires javafx.controls;
    requires javafx.fxml;

    opens hftm.rubi to javafx.fxml;
    exports hftm.rubi;
}
