package hftm.rubi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

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

    private QuizDaten quizDaten = new QuizDaten(); // Instanz von QuizDaten

    @FXML
    private void initialize() {
        List<Kategorie> kategorien = quizDaten.getKategorien();
        Button[] kategorieButtons = {Kategorie1, Kategorie2, Kategorie3, Kategorie4};

        for (int i = 0; i < kategorieButtons.length; i++) {
            if (i < kategorien.size()) {
                kategorieButtons[i].setText(kategorien.get(i).getName());
                Kategorie ausgewaehlteKategorie = kategorien.get(i);
                kategorieButtons[i].setOnAction(event -> waehleKategorie(ausgewaehlteKategorie));
            } else {
                kategorieButtons[i].setVisible(false);
            }
        }
    }

    private void waehleKategorie(Kategorie kategorie) {
        App.setAusgewaehlteKategorie(kategorie);
        try {
            switchToSecondary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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