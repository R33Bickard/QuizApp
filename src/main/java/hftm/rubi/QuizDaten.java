package hftm.rubi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class QuizDaten {
    private List<Kategorie> kategorien;

    public QuizDaten() {
        this.kategorien = new ArrayList<>();
        ladeDaten();
        waehleZufaelligeFragenProKategorie(5);
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

public void waehleZufaelligeFragenProKategorie(int anzahlFragenProKategorie) {
        for (Kategorie kategorie : kategorien) {
            Collections.shuffle(kategorie.getFragen()); // Mische die Fragenliste
            if (kategorie.getFragen().size() > anzahlFragenProKategorie) {
                kategorie.setFragen(new ArrayList<>(kategorie.getFragen().subList(0, anzahlFragenProKategorie)));
            }
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