package ch.hftm.quizapp;

public class Frage {
    private String fragenText;
    private String[] optionen;
    private int richtigeAntwortIndex;

    public Frage(String fragenText, String[] optionen, int richtigeAntwortIndex) {
        this.fragenText = fragenText;
        this.optionen = optionen;
        this.richtigeAntwortIndex = richtigeAntwortIndex;
    }

    // Getter und Setter für alle Variablen
}
