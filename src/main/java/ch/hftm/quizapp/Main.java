package ch.hftm.quizapp;

public class Main {
    public static void main(String[] args) {
        // Beispiel-Fragen erstellen und Quiz starten
        Frage frage1 = new Frage("Wie heißt die Hauptstadt der Schweiz?", new String[]{"Zürich", "Bern", "Genf", "Basel"}, 1);
        // Weitere Beispiel-Fragen...

        Quiz quiz = new Quiz(List.of(frage1 /*, weitere Fragen... */));

        // Testlauf (später durch Benutzeroberfläche ersetzen)
        Frage aktuelleFrage = quiz.naechsteFrage();
        while (aktuelleFrage != null) {
            System.out.println(aktuelleFrage.getFragenText());
            // Hier würdest du die Optionen anzeigen und die Benutzereingabe abfragen
            aktuelleFrage = quiz.naechsteFrage();
        }
    }
}