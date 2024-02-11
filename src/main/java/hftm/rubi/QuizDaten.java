package hftm.rubi;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class QuizDaten {
    // Liste der Kategorien, die aus der JSON-Datei geladen werden.
    private List<Kategorie> categories;

    // Pfad zur JSON-Datei, die die Quizdaten enthält.
    String pathToJsonFile = "src/main/resources/hftm/rubi/quizfragen.json";

    // Konstruktor, der die Quizdaten aus der angegebenen JSON-Datei lädt.
    public QuizDaten(String pathToJsonFile) {
        this.categories = loadQuizData(pathToJsonFile);
    }

    // Lädt Quizdaten aus der angegebenen JSON-Datei und gibt eine Liste von Kategorien zurück.
    private List<Kategorie> loadQuizData(String pathToJsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Kategorie> categories = List.of(objectMapper.readValue(new File(pathToJsonFile), Kategorie[].class));
            System.out.println(categories.size() + " Kategorien geladen.");
            return categories;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Laden von: " + pathToJsonFile);
            return null;
        }
    }
    
    // Wählt eine zufällige Frage aus der angegebenen Kategorie aus und gibt sie zurück.
    public Frage getRandomQuestionFromCategory(String category) {
        List<Frage> questionsInCategory = categories.stream()
                .filter(c -> c.getCategory().equals(category))
                .findFirst()
                .map(Kategorie::getQuestions)
                .orElse(null);

        if (questionsInCategory != null && !questionsInCategory.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(questionsInCategory.size());
            return questionsInCategory.get(randomIndex);
        }

        return null;
    }

    // Gibt die Anzahl der Fragen in der angegebenen Kategorie zurück.
    public int getQuestionCountInCategory(String category) {
        List<Frage> questionsInCategory = categories.stream()
                .filter(c -> c.getCategory().equals(category))
                .findFirst()
                .map(Kategorie::getQuestions)
                .orElse(null);

        return questionsInCategory != null ? questionsInCategory.size() : 0;
    }

    // Getter für die Liste der Kategorien.
    public List<Kategorie> getCategories() {
        return categories;
    }

    // Innere Klasse, die eine Quizkategorie repräsentiert.
    public static class Kategorie {
        private String category; // Name der Kategorie.
        private List<Frage> questions; // Liste der Fragen in dieser Kategorie.

        // Getter für den Kategorienamen.
        public String getCategory() {
            return category;
        }

        // Setter für den Kategorienamen.
        public void setCategory(String category) {
            this.category = category;
        }

        // Getter für die Liste der Fragen.
        public List<Frage> getQuestions() {
            return questions;
        }

        // Setter für die Liste der Fragen.
        public void setQuestions(List<Frage> questions) {
            this.questions = questions;
        }
    }

    // Innere Klasse, die eine Quizfrage repräsentiert.
    public static class Frage {
        private String question; // Der Fragetext.
        private List<String> options; // Die Antwortmöglichkeiten.
        private String answer; // Die korrekte Antwort.

        // Getter für den Fragetext.
        public String getQuestion() {
            return question;
        }

        // Setter für den Fragetext.
        public void setQuestion(String question) {
            this.question = question;
        }

        // Getter für die Liste der Antwortmöglichkeiten.
        public List<String> getOptions() {
            return options;
        }

        // Setter für die Liste der Antwortmöglichkeiten.
        public void setOptions(List<String> options) {
            this.options = options;
        }

        // Getter für die korrekte Antwort.
        public String getAnswer() {
            return answer;
        }

        // Setter für die korrekte Antwort.
        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}