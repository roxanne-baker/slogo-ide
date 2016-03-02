package commands;

import java.util.List;

public class Sine extends MathCommand implements Executable {

	public Sine() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double degrees = (double) params.get(0);
		return Math.sin(Math.toRadians(degrees));
	}
}