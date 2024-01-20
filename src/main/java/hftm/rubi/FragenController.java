package hftm.rubi;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FragenController {

    @FXML
    private Button exitButton;
    @FXML
    private Button Kategorie1;
    @FXML
    private Button Kategorie2;
    @FXML
    private Button Kategorie3;
    @FXML
    private Button Kategorie4;
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("quiz");
    }
    @FXML
    private void handleExitButtonAction() {
        // Programm beenden
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
