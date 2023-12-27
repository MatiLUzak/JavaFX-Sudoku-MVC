package org.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Menu {

    private MainApp mainApp;

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
}

