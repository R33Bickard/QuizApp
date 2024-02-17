package hftm.rubi;

<<<<<<< HEAD
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
=======
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class QuizDaten {
    private List<Kategorie> kategorien;

    public QuizDaten() {
        this.kategorien = new ArrayList<>();
        ladeDaten();
    }

private void ladeDaten() {
    try (InputStream inputStream = getClass().getResourceAsStream("/hftm/rubi/quizdaten.json")) {
        if (inputStream == null) {
            throw new IOException("Datei 'quizdaten.json' konnte nicht gefunden werden");
        }
        String inhalt = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        JSONArray kategorienArray = new JSONArray(inhalt);

        for (int i = 0; i < kategorienArray.length(); i++) {
            JSONObject kategorieObj = kategorienArray.getJSONObject(i);
            String kategorieName = kategorieObj.getString("Name");
            Kategorie kategorie = new Kategorie(kategorieName);

            JSONArray fragenArray = kategorieObj.getJSONArray("Fragen");
            for (int j = 0; j < fragenArray.length(); j++) {
                JSONObject frageObj = fragenArray.getJSONObject(j);
                String fragetext = frageObj.getString("Frage");
                int korrekteAntwortIndex = frageObj.getInt("KorrekteAntwort");
                JSONArray antwortenArray = frageObj.getJSONArray("Antworten");

                List<String> antworten = new ArrayList<>();
                for (int k = 0; k < antwortenArray.length(); k++) {
                    antworten.add(antwortenArray.getString(k));
                }

                Frage frage = new Frage(fragetext, antworten, korrekteAntwortIndex);
                kategorie.addFrage(frage);
            }

            this.kategorien.add(kategorie);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public void druckeQuizDaten() {
        for (Kategorie kategorie : kategorien) {
            System.out.println("Kategorie: " + kategorie.getName());
            for (Frage frage : kategorie.getFragen()) {
                System.out.println("\tFrage: " + frage.getFragetext());
                System.out.println("\tAntworten:");
                List<String> antworten = frage.getAntwortmoeglichkeiten();
                for (int i = 0; i < antworten.size(); i++) {
                    System.out.println("\t\t" + (i + 1) + ": " + antworten.get(i));
                }
                System.out.println("\tKorrekte Antwort: " + (frage.getKorrekteAntwortIndex() + 1));
            }
            System.out.println();
        }
    }
    

    public List<Kategorie> getKategorien() {
        return kategorien;
    }
}
>>>>>>> c165979 (Kategorieauswahl und Fragenausgabe implementiert)
