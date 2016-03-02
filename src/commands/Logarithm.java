package commands;

import java.util.List;

public class Logarithm extends MathCommand implements Executable {

	public Logarithm() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double expr = (Double) params.get(0);
		return Math.log(expr);
	}
}