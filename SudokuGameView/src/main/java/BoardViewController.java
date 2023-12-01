import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class BoardViewController {

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private Button startButton;

    @FXML
    private void initialize() {
        difficultyComboBox.getItems().addAll("Łatwy", "Średni", "Trudny");
    }

    @FXML
    private void onStartButtonClick() {
        String selectedDifficulty = difficultyComboBox.getValue();
        if (selectedDifficulty != null) {
            // Tutaj umieść logikę rozpoczynającą grę z wybranym poziomem trudności
            System.out.println("Wybrany poziom trudności: " + selectedDifficulty);
        }
    }
}
