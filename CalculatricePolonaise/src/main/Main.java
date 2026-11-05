package main;

import controler.CalculatorControler;
import javafx.application.Application;
import javafx.stage.Stage;
import model.CalculatorModel;
import view.CalculatorGUI;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        CalculatorModel model = new CalculatorModel();
        CalculatorGUI gui = new CalculatorGUI();
        new CalculatorControler(model, gui); // câblage MVC

        stage.setTitle("Ma calculette");
        stage.setScene(gui);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
