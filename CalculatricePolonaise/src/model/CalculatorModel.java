package model;
import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface {
	
	private Stack<Double> memory ;
	private double accu ;
	
	public CalculatorModel() {
	    this.memory = new Stack<>();
	    this.accu = 0.0;
	}
	
	public void add() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() + this.accu ;
		}
	}
	
	public void substract() {
		// Vérification de si la pile est vide ou non pour éviter les erreurs 
		if (!this.memory.isEmpty()) {							
			this.accu = this.memory.pop() - this.accu  ;
		}
	}
	
	// Fonction produit 
	
	public void multiply() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() * this.accu  ;
		}
	}
	
	public void divide() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() / this.accu  ;
		}
	}
	
	public void pop() {
		if (!this.memory.isEmpty()) {
			this.accu = this.memory.pop() ;
		}
	}
	
	public void push()  {
		this.memory.push(this.accu) ;
	}
	
	public void clear() {
		this.accu = 0.0;
    }

	public void drop() {
		if (!this.memory.isEmpty()) {
			this.memory.pop() ;
		}
	}

	public void dropall() {
		while (!this.memory.isEmpty()) {
					this.memory.pop() ;
		}
	}
	
	public void opposite() {
	    this.accu = -this.accu; 
	}
	
	public void swap() {
		if (!this.memory.isEmpty()) {
			double a = this.memory.pop() ;
			this.memory.push(this.accu);
			this.accu = a ;
		}
	}
}
