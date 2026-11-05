package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Vue de la calculatrice RPN. Aucune logique métier ici : la vue affiche
 * et transmet les clics au contrôleur.
 */
public class CalculatorGUI extends Scene implements CalculatorGUIInterface {

    private static final int STACK_LINES = 4; // lignes de pile visibles (+ 1 ligne d'accumulateur)

    private final Label[] stackLabels = new Label[STACK_LINES];
    private final Label accuLabel = new Label("0.0");
    private final List<Button> buttons = new ArrayList<>();

    private String accu = "0.0";
    private List<Double> stackData = new ArrayList<>();

    public CalculatorGUI() {
        super(new VBox(), 260, 420);
        VBox root = (VBox) getRoot();
        root.setSpacing(6);
        root.setPadding(new Insets(8));

        // --- Affichage : pile (haut) + accumulateur (bas)
        VBox display = new VBox(3);
        for (int i = 0; i < STACK_LINES; i++) {
            stackLabels[i] = createDisplayLabel("");
            display.getChildren().add(stackLabels[i]);
        }
        styleDisplay(accuLabel);
        accuLabel.setStyle(accuLabel.getStyle() + "-fx-font-weight: bold;");
        display.getChildren().add(accuLabel);

        // --- Clavier
        GridPane keypad = new GridPane();
        keypad.setHgap(4);
        keypad.setVgap(4);

        // chiffres
        addButton(keypad, "7", "7", 0, 0);
        addButton(keypad, "8", "8", 1, 0);
        addButton(keypad, "9", "9", 2, 0);
        addButton(keypad, "4", "4", 0, 1);
        addButton(keypad, "5", "5", 1, 1);
        addButton(keypad, "6", "6", 2, 1);
        addButton(keypad, "1", "1", 0, 2);
        addButton(keypad, "2", "2", 1, 2);
        addButton(keypad, "3", "3", 2, 2);
        addButton(keypad, "0", "0", 0, 3);
        addButton(keypad, ",", ",", 1, 3);
        addButton(keypad, "+/-", "+/-", 2, 3);

        // opérateurs
        addButton(keypad, "+", "+", 3, 0);
        addButton(keypad, "-", "-", 3, 1);
        addButton(keypad, "*", "*", 3, 2);
        addButton(keypad, "/", "/", 3, 3);

        // gestion de la pile
        addButton(keypad, "Push", "push", 0, 4);
        addButton(keypad, "Pop", "pop", 1, 4);
        addButton(keypad, "Drop", "drop", 2, 4);
        addButton(keypad, "Swap", "swap", 3, 4);
        addButton(keypad, "C", "clear", 0, 5);

        root.getChildren().addAll(display, keypad);
        VBox.setVgrow(keypad, Priority.ALWAYS);

        affiche();
    }

    // ------------------------------------------------------------------
    // CalculatorGUIInterface
    // ------------------------------------------------------------------

    @Override
    public void change(String accu) {
        this.accu = accu;
    }

    @Override
    public void change(List<Double> stackData) {
        this.stackData = new ArrayList<>(stackData); // copie défensive
    }

    @Override
    public void affiche() {
        accuLabel.setText(accu);

        // le sommet de pile (dernier élément) s'affiche juste au-dessus de l'accumulateur
        int size = stackData.size();
        for (int i = 0; i < STACK_LINES; i++) {
            int idx = size - STACK_LINES + i;
            stackLabels[i].setText(idx >= 0 ? String.valueOf(stackData.get(idx)) : "");
        }
    }

    @Override
    public void setActionHandler(EventHandler<ActionEvent> handler) {
        for (Button b : buttons) {
            b.setOnAction(handler);
        }
    }

    // ------------------------------------------------------------------
    // Utilitaires internes
    // ------------------------------------------------------------------

    private void addButton(GridPane grid, String text, String command, int col, int row) {
        Button b = new Button(text);
        b.setUserData(command); // commande lue par le contrôleur
        b.setMaxWidth(Double.MAX_VALUE);
        b.setMaxHeight(Double.MAX_VALUE);
        b.setPrefSize(55, 40);
        GridPane.setHgrow(b, Priority.ALWAYS);
        GridPane.setVgrow(b, Priority.ALWAYS);
        grid.add(b, col, row);
        buttons.add(b);
    }

    private Label createDisplayLabel(String text) {
        Label l = new Label(text);
        styleDisplay(l);
        return l;
    }

    private void styleDisplay(Label l) {
        l.setMaxWidth(Double.MAX_VALUE);
        l.setAlignment(Pos.CENTER_RIGHT);
        l.setPadding(new Insets(4, 6, 4, 6));
        l.setMinHeight(28);
        l.setStyle("-fx-border-color: gray; -fx-background-color: #eeeeee; -fx-font-size: 14px;");
    }
}
