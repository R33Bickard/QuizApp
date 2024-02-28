package hftm.rubi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class QuizController {

    @FXML
    private Label frageLabel;
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
    //@FXML
   // private Button ErgebnisButton;

    private Kategorie ausgewaehlteKategorie;
    private List<Frage> fragen;
    private int aktuelleFrageIndex = 0;
    private int korrekteAntwortenZaehler = 0;

    public void setKategorie(Kategorie kategorie) {
        this.ausgewaehlteKategorie = kategorie;
        this.fragen = kategorie.getFragen();
        zeigeFrage();
    }

    private void zeigeFrage() {
        if (aktuelleFrageIndex < fragen.size()) {
            Frage aktuelleFrage = fragen.get(aktuelleFrageIndex);
            frageLabel.setText(aktuelleFrage.getFragetext());
            List<String> antworten = aktuelleFrage.getAntwortmoeglichkeiten();
            Button[] antwortButtons = { Auswahl1, Auswahl2, Auswahl3, Auswahl4 };

            for (int i = 0; i < antwortButtons.length; i++) {
                if (i < antworten.size()) {
                    antwortButtons[i].setText(antworten.get(i));
                    antwortButtons[i].setVisible(true);
                } else {
                    antwortButtons[i].setVisible(false);
                }
            }
        }
    }

    @FXML
    private void antwortAuswaehlen(ActionEvent event) {
        Button gewaehlteAntwort = (Button) event.getSource();
        int antwortIndex = Integer.parseInt(gewaehlteAntwort.getId().replaceAll("\\D+", "")) - 1;

        // Logik, um die ausgewählte Antwort zu verarbeiten
        if (fragen.get(aktuelleFrageIndex).istAntwortKorrekt(antwortIndex)) {
            korrekteAntwortenZaehler++;
        }
        aktuelleFrageIndex++;
        if (aktuelleFrageIndex < fragen.size()) {
            zeigeFrage();
        } else {
            // Alle Fragen beantwortet, wechsle zur Ergebnisseite
            try {
                switchToErgebnis();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void switchToErgebnis() throws IOException {
        // Speichere Ergebnisse bevor zur Ergebnisseite gewechselt wird
        App.speichereErgebnisse(App.getBenutzerName(), korrekteAntwortenZaehler);
    
        // Wechsel zur Ergebnisseite
        App.setRoot("ergebnis", korrekteAntwortenZaehler, fragen.size());
    }
    

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
