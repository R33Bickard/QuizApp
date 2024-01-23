package hftm.rubi;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ErgebnisController {
 @FXML
    private Button exitButton;
   
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("fragen");
    }
      @FXML
    private void handleExitButtonAction() {
        // Programm beenden
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
