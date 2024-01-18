package hftm.rubi;

import java.io.IOException;
import javafx.fxml.FXML;

public class FragenController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("quiz");
    }
}
