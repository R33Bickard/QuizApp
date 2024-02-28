package hftm.rubi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App extends Application {

    private static Scene scene;
    private static Kategorie ausgewaehlteKategorie; // Hinzugefügte Variable für die ausgewählte Kategorie
    private static String benutzerName;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    static void setRoot(String fxml, int korrekteAntworten, int gesamtFragen) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        scene.setRoot(loader.load());
    
        if ("quiz".equals(fxml)) {
            QuizController controller = loader.getController();
            controller.setKategorie(ausgewaehlteKategorie);
        } else if ("ergebnis".equals(fxml)) {
            ErgebnisController ergebnisController = loader.getController();
            ergebnisController.setErgebnisse(korrekteAntworten, gesamtFragen);
        }
    }

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml, 0, 0);
    }  

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setAusgewaehlteKategorie(Kategorie kategorie) {
        ausgewaehlteKategorie = kategorie;
    }

    public static void setBenutzerName(String name) {
        benutzerName = name;
    }    

    public static String getBenutzerName() {
        return benutzerName;
    }

    public static void speichereErgebnisse(String name, int punkte) {
        try (FileWriter fw = new FileWriter("ergebnisse.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(name + "," + punkte); // Speichert den Namen und die Punkte getrennt durch ein Komma
        } catch (IOException e) {
            System.err.println("Ein Fehler ist aufgetreten beim Speichern der Ergebnisse: " + e.getMessage());
        }
    }

        public static List<String> ladeErgebnisse() throws IOException {
        return Files.lines(Paths.get("ergebnisse.txt"))
                    .sorted(Comparator.comparingInt(line -> -Integer.parseInt(line.split(",")[1])))
                    .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        launch();
    }

}