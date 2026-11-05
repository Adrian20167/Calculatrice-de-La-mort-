package controler;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.CalculatorModelInterface;
import view.CalculatorGUIInterface;

/**
 * Contrôleur : relie la vue et le modèle.
 *  - vue -> modèle : handle(ActionEvent)
 *  - modèle -> vue : change(...) (callbacks)
 */
public class CalculatorControler implements CalculatorControlerInterface, EventHandler<ActionEvent> {

	private final CalculatorModelInterface model;
	private final CalculatorGUIInterface view;

	/** Nombre en cours de saisie (vide = pas de saisie en cours). */
	private final StringBuilder saisie = new StringBuilder();

	public CalculatorControler(CalculatorModelInterface model, CalculatorGUIInterface view) {
		this.model = model;
		this.view = view;
		this.model.setControler(this);     // le modèle appellera change(...)
		this.view.setActionHandler(this);  // la vue enverra ses clics ici
	}

	// ------------------------------------------------------------------
	// Vue -> Modèle
	// ------------------------------------------------------------------

	@Override
	public void handle(ActionEvent e) {
		String cmd = (String) ((Button) e.getSource()).getUserData();

		switch (cmd) {
			case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> saisirChiffre(cmd);
			case "," -> saisirVirgule();
			default -> {
				validerSaisie(); // le nombre saisi est placé dans l'accumulateur
				executer(cmd);
			}
		}
	}

	private void saisirChiffre(String chiffre) {
		if (saisie.toString().equals("0")) {
			saisie.setLength(0); // évite "007"
		}
		saisie.append(chiffre);
		majAccuDepuisSaisie();
	}

	private void saisirVirgule() {
		if (saisie.indexOf(".") >= 0) {
			return; // une seule virgule
		}
		if (saisie.length() == 0) {
			saisie.append("0");
		}
		saisie.append(".");
		majAccuDepuisSaisie();
	}

	/** Place la saisie dans l'accumulateur du modèle et l'affiche. */
	private void majAccuDepuisSaisie() {
		model.setAccu(Double.parseDouble(saisie.toString().endsWith(".") ? saisie + "0" : saisie.toString()));
		view.change(saisie.toString());
		view.affiche();
	}

	/** Termine la saisie en cours (le prochain chiffre démarrera un nouveau nombre). */
	private void validerSaisie() {
		saisie.setLength(0);
	}

	private void executer(String cmd) {
		switch (cmd) {
			case "+"     -> model.add();
			case "-"     -> model.substract();
			case "*"     -> model.multiply();
			case "/"     -> model.divide();
			case "+/-"   -> model.opposite();
			case "push"  -> model.push();
			case "pop"   -> model.pop();
			case "drop"  -> model.drop();
			case "swap"  -> model.swap();
			case "clear" -> model.clear();
			default      -> { }
		}
	}

	// ------------------------------------------------------------------
	// Modèle -> Vue (callbacks)
	// ------------------------------------------------------------------

	@Override
	public void change(String accu) {
		view.change(accu);
		view.affiche();
	}

	@Override
	public void change(List<Double> stackData) {
		view.change(stackData);
		view.affiche();
	}
}
