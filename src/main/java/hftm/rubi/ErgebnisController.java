package hftm.rubi;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ErgebnisController {
    @FXML
    private Button exitButton;
    @FXML
    private Label ergebnisLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private ListView<String> ranglisteView;

    public void initialize() {
        try {
            List<String> rangliste = App.ladeErgebnisse();
            List<String> formatierteRangliste = rangliste.stream()
                .map(line -> {
                    String[] parts = line.split(",");
                    return "Spieler: " + parts[0] + ", Punkte: " + parts[1];
                })
                .collect(Collectors.toList());
    
            //ranglisteView.getItems().clear();
            ranglisteView.getItems().addAll(formatierteRangliste);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void setErgebnisse(int korrekteAntworten, int gesamtFragen) {
        nameLabel.setText("Spieler: " + App.getBenutzerName());
        ergebnisLabel.setText("Du hast " + korrekteAntworten + " von " + gesamtFragen + " Fragen richtig beantwortet!");
    }

    public void setBenutzerName(String name) {
        
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
