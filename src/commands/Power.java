package commands;

import java.util.List;

public class Power extends OperationCommand implements Executable {

	public Power() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		double base = (double) params.get(0);
		double exponent = (double) params.get(1);
		return Math.pow(base, exponent);
	}	
}
