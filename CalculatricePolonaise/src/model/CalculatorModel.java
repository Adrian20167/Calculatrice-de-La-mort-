package model;
import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface {
	
	private Stack<Double> memory ;
	private double accu ;

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
}
