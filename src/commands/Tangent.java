package commands;

import java.util.List;

public class Tangent extends OperationCommand implements Executable {

	public Tangent() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double degrees = (double) params.get(0);
		return Math.tan(degrees);
	}
}