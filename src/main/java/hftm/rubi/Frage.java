package hftm.rubi;

import java.util.List;

public class Frage {
    private String fragetext;
    private List<String> antwortmoeglichkeiten;
    private int korrekteAntwortIndex;

    public Frage(String fragetext, List<String> antwortmoeglichkeiten, int korrekteAntwortIndex) {
        this.fragetext = fragetext;
        this.antwortmoeglichkeiten = antwortmoeglichkeiten;
        this.korrekteAntwortIndex = korrekteAntwortIndex;
    }

    public String getFragetext() {
        return fragetext;
    }

    public void setFragetext(String fragetext) {
        this.fragetext = fragetext;
    }

    public List<String> getAntwortmoeglichkeiten() {
        return antwortmoeglichkeiten;
    }

    public void setAntwortmoeglichkeiten(List<String> antwortmoeglichkeiten) {
        this.antwortmoeglichkeiten = antwortmoeglichkeiten;
    }

    public int getKorrekteAntwortIndex() {
        return korrekteAntwortIndex;
    }

    public void setKorrekteAntwortIndex(int korrekteAntwortIndex) {
        this.korrekteAntwortIndex = korrekteAntwortIndex;
    }

    // Methode, um die Korrektheit einer Antwort zu prüfen
    public boolean istAntwortKorrekt(int antwortIndex) {
        return antwortIndex == korrekteAntwortIndex;
    }
}