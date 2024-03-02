package hftm.rubi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class FrageTest {
    
    @Test
    void testIstAntwortKorrekt() {
        Frage frage = new Frage("Was ist 2+2?", List.of("3", "4", "5"), 1);
        assertTrue(frage.istAntwortKorrekt(1), "Die Antwort '4' sollte als korrekt erkannt werden");
        assertFalse(frage.istAntwortKorrekt(0), "Die Antwort '3' sollte als falsch erkannt werden");
    }
}

