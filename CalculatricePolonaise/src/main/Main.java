package main;

import model.CalculatorModel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculatorModel Calc = new CalculatorModel() ;
				
		Calc.setAccu(15);
		Calc.push();
		
		Calc.setAccu(5);
		Calc.add();
		
		System.out.println( Calc.getAccu()) ;
	}

}
