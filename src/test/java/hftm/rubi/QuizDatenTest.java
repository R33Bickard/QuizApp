package hftm.rubi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuizDatenTest {

    private QuizDaten quizDaten;

    @BeforeEach
    void setUp() {
        quizDaten = new QuizDaten();
    }

    @Test
    void testLadeDaten() {
        assertAll("Kategorien und Fragen sollten korrekt geladen werden",
            () -> assertNotNull(quizDaten.getKategorien(), "Kategorien sollten nicht null sein"),
            () -> assertFalse(quizDaten.getKategorien().isEmpty(), "Kategorien sollten nicht leer sein")
        );

        for (Kategorie kategorie : quizDaten.getKategorien()) {
            assertAll("Jede Kategorie sollte Fragen enthalten",
                () -> assertNotNull(kategorie.getName(), "Kategoriename sollte nicht null sein"),
                () -> assertFalse(kategorie.getFragen().isEmpty(), "Fragenliste sollte nicht leer sein")
            );
        }
    }

    @Test
    void testWaehleZufaelligeFragenProKategorie() {
        int anzahlFragenProKategorie = 5;
        quizDaten.waehleZufaelligeFragenProKategorie(anzahlFragenProKategorie);
        quizDaten.getKategorien().forEach(kategorie -> 
            assertEquals(anzahlFragenProKategorie, kategorie.getFragen().size(), 
            "Anzahl der Fragen pro Kategorie sollte " + anzahlFragenProKategorie + " sein")
        );
    }
}
