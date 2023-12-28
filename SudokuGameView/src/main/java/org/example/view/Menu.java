package org.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Menu {

    private MainApp mainApp;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Label authorsLabel;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleEasyAction() {
        try {
            mainApp.showSudokuBoardScene(Difficulty.EASY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMediumAction() {
        try {
            mainApp.showSudokuBoardScene(Difficulty.MEDIUM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHardAction() {
        try {
            mainApp.showSudokuBoardScene(Difficulty.HARD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleLanguageChange() throws IOException {
        Locale selectedLocale = languageComboBox.getValue().equals("Polski") ? new Locale("pl", "PL") : new Locale("en", "US");

        // Zaktualizuj ResourceBundle do nowego języka
        ResourceBundle mainBundle = ResourceBundle.getBundle("Messages", selectedLocale);
        ResourceBundle authorsBundle = ResourceBundle.getBundle("org.example.view.AuthorsResource", selectedLocale);

        // Przeładuj widok z nowym ResourceBundle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"), mainBundle);
        Parent root = loader.load();

        // Ustawienie referencji MainApp dla nowego kontrolera
        Menu controller = loader.getController();
        controller.setMainApp(this.mainApp);

        // Ustawienie nowego widoku w scenie
        Scene scene = languageComboBox.getScene();
        scene.setRoot(root);

        // Zaktualizuj etykietę z informacjami o autorach
        String authorsKey = selectedLocale.getLanguage().equals("pl") ? "autorzy" : "authors";
        String authorsText = authorsBundle.getString(authorsKey);
        controller.authorsLabel.setText(authorsText); // Ustawienie tekstu etykiety
    }

}

