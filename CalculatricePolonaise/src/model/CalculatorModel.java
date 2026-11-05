package model;

import java.util.ArrayList;
import java.util.Stack;

import controler.CalculatorControlerInterface;

public class CalculatorModel implements CalculatorModelInterface {

	private Stack<Double> memory ;
	private double accu ;
	private CalculatorControlerInterface controler ; // callback vers le contrôleur

	public CalculatorModel() {
	    this.memory = new Stack<>();
	    this.accu = 0.0;
	}

	public void setControler(CalculatorControlerInterface controler) {
		this.controler = controler;
	}

	/** Prévient le contrôleur que l'état du modèle a changé. */
	private void notifyControler() {
		if (this.controler != null) {
			this.controler.change(String.valueOf(this.accu));
			this.controler.change(new ArrayList<>(this.memory));
		}
	}

	public void add() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() + this.accu ;
		}
		notifyControler();
	}

	public void substract() {
		// Vérification de si la pile est vide ou non pour éviter les erreurs
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() - this.accu  ;
		}
		notifyControler();
	}

	// Fonction produit

	public void multiply() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() * this.accu  ;
		}
		notifyControler();
	}

	public void divide() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() / this.accu  ;
		}
		notifyControler();
	}

	public void pop() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() ;
		}
		notifyControler();
	}

	public void push()  {
		this.memory.push(this.accu) ;
		notifyControler();
	}

	public void clear() {
		this.accu = 0.0;
		notifyControler();
    }

	public void drop() {
		if (!this.memory.isEmpty()) {
			this.memory.pop() ;
		}
		notifyControler();
	}

	public void dropall() {
		while (!this.memory.isEmpty()) {
					this.memory.pop() ;
		}
		notifyControler();
	}

	public void opposite() {
	    this.accu = -this.accu;
	    notifyControler();
	}

	public void swap() {
		if (!this.memory.isEmpty()) {
			double a = this.memory.pop() ;
			this.memory.push(this.accu);
			this.accu = a ;
		}
		notifyControler();
	}

	/** Pas de notification ici : le contrôleur gère l'affichage pendant la saisie. */
	public void setAccu(double valeur) {
	    this.accu = valeur;
	}

	public double getAccu() {
	    return this.accu;
	}
}
