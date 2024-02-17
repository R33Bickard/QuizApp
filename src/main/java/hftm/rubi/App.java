package hftm.rubi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Kategorie ausgewaehlteKategorie; // Hinzugefügte Variable für die ausgewählte Kategorie

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("fragen"));
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        scene.setRoot(loader.load());
        if ("quiz".equals(fxml)) {
            // Zugriff auf den QuizController und Übertragung der ausgewählten Kategorie
            QuizController controller = loader.getController();
            controller.setKategorie(ausgewaehlteKategorie);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setAusgewaehlteKategorie(Kategorie kategorie) {
        ausgewaehlteKategorie = kategorie;
    }

    public static void main(String[] args) {
        launch();
    }
}
