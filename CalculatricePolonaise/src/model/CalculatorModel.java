package model;

import java.util.ArrayList;
import java.util.Stack;

import controler.CalculatorControlerInterface;

public class CalculatorModel implements CalculatorModelInterface {

	// Mise en places des attributs nécéssaires à la création du modèle, notamment avec l'accumulateur et une pile, et un controler pour gérer l'interface 
	private Stack<Double> memory ;
	private double accu ;
	private CalculatorControlerInterface controler ; 

	// Constructeur du modèle 
	public CalculatorModel() {
	    this.memory = new Stack<>();
	    this.accu = 0.0;
	}

	public void setControler(CalculatorControlerInterface controler) {
		this.controler = controler;
	}

	// permets de communiquer avec le contrôleur 
	@Override
	private void notifyControler() {
		if (this.controler != null) {
			this.controler.change(String.valueOf(this.accu));
			this.controler.change(new ArrayList<>(this.memory));
		}
	}
	// Permets de faire une addition
	@Override
	public void add() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() + this.accu ;
		}
		notifyControler();
	}

	@Override
	public void substract() {
		
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() - this.accu  ;
		}
		notifyControler();
	}

	

	@Override
	public void multiply() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() * this.accu  ;
		}
		notifyControler();
	}

	@Override
	public void divide() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() / this.accu  ;
		}
		notifyControler();
	}
	// permets de sortir le dernier terme de la pile 
	@Override
	public void pop() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() ;
		}
		notifyControler();
	}

	// Permets d'envoyer le nombre de l'accumulateur dans la pile 
	@Override
	public void push()  {
		this.memory.push(this.accu) ;
		this.accu = 0 ;
		notifyControler();
	}
	// Remets l'accumulateur à 0
	@Override
	public void clear() {
		this.accu = 0.0;
		notifyControler();
    }
	// Supprime le premier terme de la pile 
	@Override
	public void drop() {
		if (!this.memory.isEmpty()) {
			this.memory.pop() ;
		}
		notifyControler();
	}
	// Supprime tout les termes de la pile
	@Override
	public void dropall() {
		while (!this.memory.isEmpty()) {
					this.memory.pop() ;
		}
		notifyControler();
	}
	// Change le signe de l'accumulateur 
	@Override
	public void opposite() {
	    this.accu = -this.accu;
	    notifyControler();
	}
	// Inverse le premier terme de la pile et l'accumulateur 
	@Override
	public void swap() {
		if (!this.memory.isEmpty()) {
			double a = this.memory.pop() ;
			this.memory.push(this.accu);
			this.accu = a ;
		}
		notifyControler();
	}

	// Sert à communiquer avec la console 
	@Override
	public void setAccu(double valeur) {
	    this.accu = valeur;
	}

	@Override
	public double getAccu() {
	    return this.accu;
	}
}
