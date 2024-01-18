package hftm.rubi;

import java.io.IOException;
import javafx.fxml.FXML;

public class QuizController {

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("ergebnis");
    }
}