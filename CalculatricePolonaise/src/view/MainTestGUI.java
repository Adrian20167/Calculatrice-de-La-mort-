package view;

import java.util.Arrays;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainTestGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        CalculatorGUI gui = new CalculatorGUI();
        gui.change("0.0");
        gui.change(Arrays.asList(78.0, 45.0, 12.0, 23.0));

        primaryStage.setTitle("Ma calculette");
        primaryStage.setScene(gui);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}