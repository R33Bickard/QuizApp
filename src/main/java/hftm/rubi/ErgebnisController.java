package hftm.rubi;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErgebnisController {
    @FXML
    private Button exitButton;
    @FXML
    private Label ergebnisLabel;

    public void setErgebnisse(int korrekteAntworten, int gesamtFragen) {
        ergebnisLabel.setText("Du hast " + korrekteAntworten + " von " + gesamtFragen + " Fragen richtig beantwortet!");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void handleExitButtonAction() {
        // Programm beenden
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
