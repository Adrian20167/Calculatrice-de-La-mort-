package controler;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.CalculatorModelInterface;
import view.CalculatorGUIInterface;


public class CalculatorControler implements CalculatorControlerInterface, EventHandler<ActionEvent> {

	private final CalculatorModelInterface model;
	private final CalculatorGUIInterface view;

	private final StringBuilder saisie = new StringBuilder();

	public CalculatorControler(CalculatorModelInterface model, CalculatorGUIInterface view) {
		this.model = model;
		this.view = view;
		this.model.setControler(this);     
		this.view.setActionHandler(this);  
	}


	@Override
	public void handle(ActionEvent e) {
		String cmd = (String) ((Button) e.getSource()).getUserData();

		switch (cmd) {
			case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> saisirChiffre(cmd);
			case "," -> saisirVirgule();
			default -> {
				validerSaisie(); 
				executer(cmd);
			}
		}
	}

	private void saisirChiffre(String chiffre) {
		if (saisie.toString().equals("0")) {
			saisie.setLength(0); 
		}
		saisie.append(chiffre);
		majAccuDepuisSaisie();
	}

	private void saisirVirgule() {
		if (saisie.indexOf(".") >= 0) {
			return; 
		}
		if (saisie.length() == 0) {
			saisie.append("0");
		}
		saisie.append(".");
		majAccuDepuisSaisie();
	}

	
	private void majAccuDepuisSaisie() {
		model.setAccu(Double.parseDouble(saisie.toString().endsWith(".") ? saisie + "0" : saisie.toString()));
		view.change(saisie.toString());
		view.affiche();
	}

	
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
