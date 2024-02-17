module hftm.rubi {
    requires javafx.controls;
    requires javafx.fxml;
<<<<<<< HEAD
    requires transitive javafx.graphics;
    requires com.fasterxml.jackson.databind;

    opens hftm.rubi to javafx.fxml, com.fasterxml.jackson.databind;
    exports hftm.rubi;
}
=======
    requires org.json;

    opens hftm.rubi to javafx.fxml, org.json;
    exports hftm.rubi;
}
>>>>>>> c165979 (Kategorieauswahl und Fragenausgabe implementiert)
