package model;

import java.util.ArrayList;
import java.util.Stack;

import controler.CalculatorControlerInterface;

// Nous avons fait le choix e faire les calculs avec l'accumulateur et non pas avec les deux derniers termes des piles pour respecter le cahier des charges,
// Néanmoins, il faut donc être vigilant pour ne pas écraser le résultat

public class CalculatorModel implements CalculatorModelInterface {

	// Mise en place des attributs nécessaires à la création du modèle, notamment avec l'accumulateur, une pile et un contrôleur pour gérer l'interface 
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

	// Permet d'actualiser l'interface graphique via le contrôleur 
	private void notifyControler() {
		if (this.controler != null) {
			this.controler.change(String.valueOf(this.accu));
			this.controler.change(new ArrayList<>(this.memory));
		}
	}
	
	// Permet de faire une addition
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
	
	// Permet de sortir le dernier terme (le sommet) de la pile pour le placer dans l'accumulateur
	@Override
	public void pop() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() ;
		}
		notifyControler();
	}

	// Permet d'envoyer le nombre de l'accumulateur dans la pile 
	@Override
	public void push()  {
		this.memory.push(this.accu) ;
		this.accu = 0 ;
		notifyControler();
	}
	
	// Remet l'accumulateur à 0
	@Override
	public void clear() {
		this.accu = 0.0;
		notifyControler();
    }
	
	// Supprime le dernier élément (le sommet) de la pile 
	@Override
	public void drop() {
		if (!this.memory.isEmpty()) {
			this.memory.pop() ;
		}
		notifyControler();
	}
	
	// Supprime tous les termes de la pile
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
	
	// Échange le sommet de la pile avec l'accumulateur 
	@Override
	public void swap() {
		if (!this.memory.isEmpty()) {
			double a = this.memory.pop() ;
			this.memory.push(this.accu);
			this.accu = a ;
		}
		notifyControler();
	}

	// Permet au contrôleur de modifier ou lire la valeur de l'accumulateur
	@Override
	public void setAccu(double valeur) {
	    this.accu = valeur;
	}

	@Override
	public double getAccu() {
	    return this.accu;
	}
}
