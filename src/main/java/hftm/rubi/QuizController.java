package hftm.rubi;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuizController {

    @FXML
    private Button exitButton;
    @FXML
    private Button Auswahl1;
    @FXML
    private Button Auswahl2;
    @FXML
    private Button Auswahl3;
    @FXML
    private Button Auswahl4;


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("fragen");
    }
    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("ergebnis");
    }

    @FXML
    private void handleExitButtonAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}