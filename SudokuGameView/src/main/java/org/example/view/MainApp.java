package org.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApp extends Application {

    private  Stage primaryStage;

    private static Locale currentLocale = Locale.getDefault();


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        showDifficultySelectionScene();

    }
    public void showDifficultySelectionScene() throws IOException {
        //ResourceBundle authorsBundle = ResourceBundle.getBundle("org.example.view.AuthorsResource_en");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", Locale.getDefault());
        loader.setResources(bundle); // Set the ResourceBundle for the loader
        Parent difficultySelectionRoot = loader.load();

        Menu controller = loader.getController();
        controller.setMainApp(this);

        Scene difficultySelectionScene = new Scene(difficultySelectionRoot);
        primaryStage.setScene(difficultySelectionScene);
        primaryStage.setTitle("Wybierz poziom trudności");
        primaryStage.show();
    }


    public void showSudokuBoardScene(Difficulty difficulty) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BoardView.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", Locale.getDefault());
        loader.setResources(bundle); // Set the ResourceBundle for the loader
        Parent sudokuBoardRoot = loader.load();

        // Przekazanie wybranego poziomu trudności do kontrolera planszy Sudoku
        BoardViewController controller = loader.getController();
        controller.setMainApp(this);
        controller.initializeBoard(difficulty);

        Scene sudokuBoardScene = new Scene(sudokuBoardRoot);
        primaryStage.setScene(sudokuBoardScene);
        primaryStage.setTitle("Sudoku");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
