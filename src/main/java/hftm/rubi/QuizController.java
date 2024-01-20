package hftm.rubi;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuizController {

    @FXML
    private Button exitButton;
    @FXML
    private Button startQuiz;

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("ergebnis");
    }

    @FXML
    private void start() throws IOException {
        App.setRoot("fragen");
    }
    @FXML
    private void handleExitButtonAction() {
        // Programm beenden
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}