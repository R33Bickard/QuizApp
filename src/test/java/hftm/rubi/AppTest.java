package hftm.rubi;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AppTest {

    @Test
    void testSpeichereUndLadeErgebnisse() throws IOException {
        String testName = "TestUser";
        int testPunkte = 10;
        App.speichereErgebnisse(testName, testPunkte);

        List<String> ergebnisse = App.ladeErgebnisse();
        assertTrue(ergebnisse.stream().anyMatch(line -> line.contains(testName) && line.contains(String.valueOf(testPunkte))),
            "Die gespeicherten Ergebnisse sollten den Testnutzer und seine Punkte enthalten");
    }
}
