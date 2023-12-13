package org.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.Node;

import org.example.Difficulty;
import org.example.SudokuBoard;
import org.example.BacktrackingSudokuSolver;

public class BoardViewController {

    @FXML
    private GridPane sudokuGrid;
    @FXML
    private ComboBox<String> difficultyComboBox;
    @FXML
    private Button startButton;

    private SudokuBoard board;

    @FXML
    private void initialize() {
        difficultyComboBox.getItems().addAll("Łatwy", "Średni", "Trudny");
        difficultyComboBox.setValue("Łatwy");
        initializeSudokuGrid();
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        updateSudokuBoard();
    }

    private void initializeSudokuGrid() {
        sudokuGrid.getChildren().clear();
        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.GRID_SIZE; j++) {
                TextField textField = new TextField();
                textField.setPrefHeight(40);
                textField.setPrefWidth(40);
                sudokuGrid.add(textField, j, i);
            }
        }
    }


    @FXML
    private void onStartButtonClick() {
        String selectedDifficulty = difficultyComboBox.getValue();
        Difficulty level = getDifficultyFromSelection(selectedDifficulty);
        board.solveGame();
        board.removeFields(level);
        updateSudokuBoard();
    }
    private Difficulty getDifficultyFromSelection(String selection) {
        switch (selection) {
            case "Łatwy":
                return Difficulty.EASY;
            case "Średni":
                return Difficulty.MEDIUM;
            case "Trudny":
                return Difficulty.HARD;
            default:
                return null;
        }
    }


    private void updateSudokuBoard() {
        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.GRID_SIZE; j++) {
                TextField textField = (TextField) getNodeFromGridPane(sudokuGrid, j, i);
                textField.setText(board.get(i, j) == 0 ? "" : Integer.toString(board.get(i, j)));
            }
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
