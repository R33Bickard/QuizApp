package ch.hftm.quizapp;

import java.util.List;

public class Quiz {
    private List<Frage> fragen;
    private int punkte;
    private int aktuelleFragenIndex;

    public Quiz(List<Frage> fragen) {
        this.fragen = fragen;
        this.punkte = 0;
        this.aktuelleFragenIndex = 0;
    }

    public Frage naechsteFrage() {
        if (aktuelleFragenIndex < fragen.size()) {
            return fragen.get(aktuelleFragenIndex++);
        }
        return null; // Keine weiteren Fragen
    }

    public boolean antwortUeberpruefen(int ausgewaehlteAntwortIndex) {
        if (fragen.get(aktuelleFragenIndex - 1).getRichtigeAntwortIndex() == ausgewaehlteAntwortIndex) {
            punkte++;
            return true;
        }
        return false;
    }

    // Weitere Methoden und Getter/Setter
}
