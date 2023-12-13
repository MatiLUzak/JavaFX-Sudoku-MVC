module SudokuGameView {
     requires javafx.controls;
     requires  javafx.fxml;
     requires ModelProject;

     exports org.example.view;
     opens org.example.view to javafx.fxml, javafx.graphics;
}