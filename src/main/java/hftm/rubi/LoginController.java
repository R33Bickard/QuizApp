package hftm.rubi;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label frageLabel;
    @FXML
    private Button startQuiz;
    @FXML
    private TextField namebox;

  
    @FXML
    private void switchToPrimary() throws IOException {
        App.setBenutzerName(namebox.getText());
        App.setRoot("fragen");
    }
    
}
