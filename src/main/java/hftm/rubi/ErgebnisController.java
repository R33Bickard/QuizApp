package hftm.rubi;


import java.io.IOException;
import javafx.fxml.FXML;

public class ErgebnisController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("fragen");
    }
}
