package org.example.view;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

import org.example.SudokuBoard;
import org.example.BacktrackingSudokuSolver;

public class BoardViewController {

    @FXML
    private GridPane sudokuGrid;

    private SudokuBoard board;

    @FXML
    public void initializeBoard(Difficulty difficulty) {
        initializeSudokuGrid();
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        difficulty.apply(board);
        updateSudokuBoard();
    }

    private void initializeSudokuGrid() {
        sudokuGrid.getChildren().clear();

        final int size = 40;
        final int thickBorder = 3;
        final String borderColor = "black";

        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.GRID_SIZE; j++) {
                TextField textField = new TextField();
                textField.getStyleClass().add("grid-cell");
                textField.setPrefHeight(size);
                textField.setPrefWidth(size);
                textField.setAlignment(Pos.CENTER);
                sudokuGrid.add(textField, j, i);
                GridPane.setMargin(textField, new Insets(1));
            }
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
