package model;

import controler.CalculatorControlerInterface;

public interface CalculatorModelInterface {

	public void add() ;
	public void substract() ;
	public void multiply();
	public void divide() ;
	public void opposite() ;
	public void push() ;
	public void pop() ;
	public void drop() ;
	public void swap() ;
	public void clear() ;
	public void dropall() ;

	public void setAccu(double valeur) ;
	public double getAccu() ;
	public void setControler(CalculatorControlerInterface controler) ;
}
