package hftm.rubi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.List;

public class QuizController {

    // Referenzen auf UI-Elemente, definiert in der zugehörigen FXML-Datei.
    @FXML
    private Label questionLabel; // Zeigt die aktuelle Quizfrage an.
    @FXML
    private Button exitButton; // Beendet das Quiz.
    @FXML
    private Button nextButton; // Geht zur nächsten Frage.
    @FXML
    private Button answerButton1; // Antwortmöglichkeit 1.
    @FXML
    private Button answerButton2; // Antwortmöglichkeit 2.
    @FXML
    private Button answerButton3; // Antwortmöglichkeit 3.
    @FXML
    private Button answerButton4; // Antwortmöglichkeit 4.

    private String selectedCategory; // Die vom Benutzer gewählte Kategorie.
    private QuizDaten quizData; // Verwaltet die Daten des Quiz, einschließlich der Fragen.
    private List<QuizDaten.Frage> questions; // Liste der Fragen für die ausgewählte Kategorie.
    private QuizDaten.Frage currentQuestion; // Die aktuell angezeigte Frage.
    private int currentQuestionIndex = 0; // Index der aktuellen Frage in der Liste.
    private int correctAnswers = 0; // Anzahl der korrekt beantworteten Fragen.
    private String selectedOption = null; // Die vom Benutzer gewählte Antwortoption.

    // Setzt die ausgewählte Kategorie und initialisiert das Laden der Fragen.
    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
        System.out.println("Ausgewählte Kategorie: " + selectedCategory);
    }

    // Initialisiert die Komponenten des Controllers.
    @FXML
    private void initialize() {
        if (selectedCategory != null) {
            loadDataForCategory(selectedCategory);
        }
    }  

    // Lädt die Fragen für die angegebene Kategorie aus der JSON-Datei.
    public void loadDataForCategory(String category) {
        quizData = new QuizDaten("src/main/resources/hftm/rubi/quizfragen.json");
        questions = quizData.getCategories().stream()
                .filter(c -> c.getCategory().equals(category))
                .findFirst()
                .map(QuizDaten.Kategorie::getQuestions)
                .orElse(null);
    }
    
    // Zeigt die aktuelle Frage und deren Antwortmöglichkeiten an.
    private void showCurrentQuestion() {
        System.out.println("Warten um die Fragen zu zeigen...");
        if (questions != null && !questions.isEmpty() && currentQuestionIndex < questions.size()) {
            currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Gezeigte Frage: " + currentQuestion.getQuestion());

            questionLabel.setText(currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            if (options.size() >= 4) {
                answerButton1.setText(options.get(0));
                answerButton2.setText(options.get(1));
                answerButton3.setText(options.get(2));
                answerButton4.setText(options.get(3));
            }
    
            selectedOption = null; // Zurücksetzen für die nächste Frage
            nextButton.setDisable(true); // Nächste Frage erst nach Auswahl möglich
        } else {
            questionLabel.setText("Keine weiteren Fragen in dieser Kategorie verfügbar.");
            disableAnswerButtons();
        }
    }
    
    // Setzt die Kategorie und lädt entsprechende Fragen.
    public void setQuizCategory(String category) {
        questions = quizData.getCategories().stream()
                .filter(c -> c.getCategory().equals(category))
                .findFirst()
                .map(QuizDaten.Kategorie::getQuestions)
                .orElse(null);
        showCurrentQuestion();
    }    

    // Verarbeitet die Auswahl einer Antwortmöglichkeit und gibt Feedback.
    @FXML
    private void handleOptionClick(ActionEvent event) {
        if (currentQuestion == null || selectedOption != null) {
            System.out.println("Keine Frage geladen.");
            return;
        }
    
        Button clickedButton = (Button) event.getSource();
        selectedOption = clickedButton.getText();
        
        if (currentQuestion != null) {
            String correctAnswer = currentQuestion.getAnswer();
            if (selectedOption.equals(correctAnswer)) {
                correctAnswers++;
                clickedButton.setStyle("-fx-background-color: #4CAF50;"); // Grün für richtig
            } else {
                clickedButton.setStyle("-fx-background-color: #F44336;"); // Rot für falsch
            }
        }
    
        nextButton.setDisable(false); // Aktiviert den "Nächste"-Button
    }
    
    // Geht zur nächsten Frage oder zeigt das Ergebnis, wenn alle Fragen beantwortet sind.
    @FXML
    private void handleNextButtonAction() {
        currentQuestionIndex++;
        if (currentQuestionIndex < quizData.getQuestionCountInCategory(selectedCategory)) {
            showCurrentQuestion();
        } else {
            showResults();
        }
    }
    
    // Beendet das Quiz und schließt das Fenster.
    @FXML
    private void handleExitButtonAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    // Zeigt eine Zusammenfassung der Quizergebnisse in einem Dialogfenster an.
    private void showResults() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ergebnisse");
        alert.setHeaderText(null);
        alert.setContentText("Sie haben " + correctAnswers + " von " + quizData.getQuestionCountInCategory(selectedCategory) + " Fragen richtig beantwortet.");
        alert.showAndWait();
    }

    // Hilfsmethode, um Antwortbuttons zu deaktivieren.
    private void disableAnswerButtons() {
        answerButton1.setDisable(true);
        answerButton2.setDisable(true);
        answerButton3.setDisable(true);
        answerButton4.setDisable(true);
    }
}