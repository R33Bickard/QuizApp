package hftm.rubi;

import java.util.ArrayList;
import java.util.List;

public class Kategorie {
    private String name;
    private List<Frage> fragen;

    public Kategorie(String name) {
        this.name = name;
        this.fragen = new ArrayList<>();
    }

    // Getter und Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Frage> getFragen() {
        return fragen;
    }

    public void setFragen(List<Frage> fragen) {
        this.fragen = fragen;
    }

    // Methode, um eine Frage hinzuzufügen
    public void addFrage(Frage frage) {
        this.fragen.add(frage);
    }
}