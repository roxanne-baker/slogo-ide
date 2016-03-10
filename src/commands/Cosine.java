
package commands;

import java.util.List;

public class Cosine extends MathCommand implements Executable {

	public Cosine() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double degrees = (Double) params.get(0);
		return Math.cos(degrees);
	}	
}
