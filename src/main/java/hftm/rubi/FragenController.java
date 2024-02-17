package hftm.rubi;

<<<<<<< HEAD
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FragenController {

    // UI-Komponenten, die in der FXML-Datei definiert sind.
    @FXML
    private Button categoryButton1;
    @FXML
    private Button categoryButton2;
    @FXML
    private Button categoryButton3;
    @FXML
    private Button categoryButton4;
    @FXML
    private Button selectedButton; // Nicht verwendet in diesem Codeausschnitt.
    @FXML
    private Button exitButton;

    // Speichert die vom Benutzer ausgewählte Kategorie.
    private String selectedCategory;

    // Konstruktor des Controllers, initialisiert die ausgewählte Kategorie als null.
    public FragenController() {
        selectedCategory = null;
    }

    // Wird automatisch nach dem Laden der FXML aufgerufen, initialisiert die Texte der Kategorie-Buttons.
    @FXML
    private void initialize() {
        categoryButton1.setText("Fussball");
        categoryButton2.setText("Allgemeinbildung");
        categoryButton3.setText("Java");
        categoryButton4.setText("Geographie");
    }

    // Behandelt Klick-Events auf Kategorie-Buttons, setzt die ausgewählte Kategorie und wechselt zur Quiz-Ansicht.
    @FXML
    private void handleKategorieButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String kategorie = button.getText();
        selectedCategory = kategorie;
        switchToQuizView();
    }

    // Wechselt zur Quiz-Ansicht, indem es die Quiz-FXML lädt und den QuizController mit der ausgewählten Kategorie initialisiert.
    @FXML
    private void switchToQuizView() {
        if (selectedCategory != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
                Parent root = loader.load();
                QuizController quizController = loader.getController();
                quizController.setSelectedCategory(selectedCategory);
                quizController.loadDataForCategory(selectedCategory);
                App.getPrimaryStage().setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
=======
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
                kategorieButtons[i].setVisible(false); // Verstecke den Button, wenn keine Kategorie vorhanden ist
>>>>>>> c165979 (Kategorieauswahl und Fragenausgabe implementiert)
            }
        }
    }

<<<<<<< HEAD
    // Alternativer Weg, um zur Quiz-Ansicht zu wechseln, derzeit nicht im Einsatz.
    @FXML
    private void switchToSecondary(ActionEvent event) {
        try {
            App.setRoot("quiz");
=======
    private void waehleKategorie(Kategorie kategorie) {
        App.setAusgewaehlteKategorie(kategorie);
        try {
            switchToSecondary();
>>>>>>> c165979 (Kategorieauswahl und Fragenausgabe implementiert)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    // Behandelt Klick-Events auf Kategorie-Buttons, eine Alternative zu `handleKategorieButtonAction` ohne Kategorie-Zuweisung.
    @FXML
    private void handleCategoryButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String selectedCategory = clickedButton.getText();

        switchToQuizView(); // Verwendet die lokale Variable selectedCategory, nicht die Instanzvariable.
    }

    // Beendet die Anwendung, wenn der Exit-Button geklickt wird.
    @FXML
    private void handleExitButtonAction() {
=======
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("quiz");
    }

    @FXML
    private void handleExitButtonAction() {
        // Programm beenden
>>>>>>> c165979 (Kategorieauswahl und Fragenausgabe implementiert)
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}