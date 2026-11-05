package view;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public interface CalculatorGUIInterface {


    void affiche();


    void change(String accu);

    
    void change(List<Double> stackData);


    void setActionHandler(EventHandler<ActionEvent> handler);
}
