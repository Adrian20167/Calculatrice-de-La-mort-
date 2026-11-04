package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CalculatorGUI extends Scene implements CalculatorGUIInterface {

    // Éléments d'affichage
    private final List<TextField> stackFields = new ArrayList<>();
    private final TextField accuField = new TextField("0.0");

    public CalculatorGUI() {
        this(new VBox());
    }

    private CalculatorGUI(VBox root) {
        super(root, 280, 420);
        buildUI(root);
    }

    private void buildUI(VBox root) {
        root.setPadding(new Insets(10));
        root.setSpacing(6);

        // 1. Zone d'affichage : 4 lignes de pile + 1 ligne accumulateur
        VBox displayBox = new VBox(4);
        for (int i = 0; i < 4; i++) {
            TextField tf = new TextField("");
            tf.setEditable(false);
            tf.setAlignment(Pos.CENTER_RIGHT);
            stackFields.add(tf);
            displayBox.getChildren().add(tf);
        }

        accuField.setAlignment(Pos.CENTER_RIGHT);
        accuField.setEditable(false);
        displayBox.getChildren().add(accuField);
        root.getChildren().add(displayBox);

        // 2. Zone des boutons
        HBox keyboardBox = new HBox(8);
        keyboardBox.setPadding(new Insets(10, 0, 0, 0));

        // Pavé numérique (4 lignes x 3 colonnes)
        GridPane numPad = new GridPane();
        numPad.setHgap(4);
        numPad.setVgap(4);

        String[][] numKeys = {
            {"7", "8", "9"},
            {"4", "5", "6"},
            {"1", "2", "3"},
            {"0", ",", "+/-"}
        };

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = createButton(numKeys[row][col]);
                numPad.add(btn, col, row);
            }
        }

        // Pavé des opérateurs (colonne de droite)
        VBox opsPad = new VBox(4);
        String[] opKeys = {"+", "-", "*", "/", "<>"};
        for (String op : opKeys) {
            Button btn = createButton(op);
            opsPad.getChildren().add(btn);
        }

        HBox.setHgrow(numPad, Priority.ALWAYS);
        keyboardBox.getChildren().addAll(numPad, opsPad);
        root.getChildren().add(keyboardBox);
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn.setPrefSize(50, 40);
        
        // TODO: Quand le contrôleur sera prêt, connecter l'action :
        // btn.setOnAction(e -> controler.handleButton(text));
        return btn;
    }

    @Override
    public void affiche() {
        // Utilisé pour rendre la vue visible si nécessaire
    }

    @Override
    public void change(String accu) {
        accuField.setText(accu != null ? accu : "0.0");
    }

    @Override
    public void change(List<Double> stackData) {
        // Efface les 4 niveaux de pile
        for (TextField tf : stackFields) {
            tf.setText("");
        }

        if (stackData == null) return;

        // Affiche les derniers éléments de la pile de bas en haut
        int displayIndex = 3; // L'élément juste au-dessus de l'accu
        for (int i = stackData.size() - 1; i >= 0 && displayIndex >= 0; i--) {
            stackFields.get(displayIndex).setText(String.valueOf(stackData.get(i)));
            displayIndex--;
        }
    }
}