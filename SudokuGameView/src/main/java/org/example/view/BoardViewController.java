package org.example.view;

import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import org.example.SudokuBoard;
import org.example.BacktrackingSudokuSolver;

public class BoardViewController {

    @FXML
    private GridPane sudokuGrid;

    private SudokuBoard board= new SudokuBoard(new BacktrackingSudokuSolver());;

    @FXML
    public void initializeBoard(Difficulty difficulty) {
        initializeSudokuGrid();
        //board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        difficulty.apply(board);
        updateSudokuBoard();
    }

    private void initializeSudokuGrid() {
        sudokuGrid.getChildren().clear();

        final int size = 40;

        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.GRID_SIZE; j++) {
                //TextField textField = new TextField();
                TextField textField = createTextField(i, j);
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

    private TextField createTextField(int row, int col) {
        TextField textField = new TextField();
        SudokuFieldAdapter adapter = new SudokuFieldAdapter(board, row, col);

        // Utworzenie TextFormattera z własnym konwerterem
        TextFormatter<String> textFormatter = new TextFormatter<>(new StringConverter<>() {
            @Override
            public String toString(String object) {
                // Prosta konwersja, ponieważ object jest już typu String
                return "0".equals(object) ? "" : object;
            }

            @Override
            public String fromString(String string) {
                // Zamiana pustego tekstu lub spacji na "0"
                if (string == null || string.isEmpty() || string.equals(" ")) {
                    return "0";
                } else {
                    // Bez zmian
                    return string;
                }
            }
        }, "", change -> {
            // Walidacja wprowadzanych danych
            String newText = change.getControlNewText();
            if (newText.matches("[1-9 ]?")) { // Dopasowuje cyfry 1-9 lub pojedynczą spację
                return change;
            }
            return null;
        });

        textField.setTextFormatter(textFormatter);

        // Dwustronne wiązanie wartości pola tekstowego z właściwością stringValue adaptera
        textFormatter.valueProperty().bindBidirectional(adapter.stringValueProperty());

        /*textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Konwersja tekstu na liczbę i aktualizacja SudokuBoard
            try {
                int intValue = newValue.trim().isEmpty() ? 0 : Integer.parseInt(newValue.trim());
                board.set(row, col, intValue); // Tutaj ustawiamy wartość w SudokuBoard
                // Opcjonalnie wypisz całą tablicę w konsoli do sprawdzenia
                board.printBoard();
            } catch (NumberFormatException e) {
                // Obsługa sytuacji, gdy wprowadzony tekst nie jest liczbą
                System.out.println("Wprowadzono nieprawidłową wartość: " + newValue);
            }
        });*/

        return textField;
    }


}
