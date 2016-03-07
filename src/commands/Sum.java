package commands;

import java.util.List;

public class Sum extends MathCommand implements Executable {

	public Sum() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		double sum = 0;
		for (Object param : params) {
			double val = (double) param;
			sum += val;
		}
		return sum;
	}
	
	@Override
	public String checkNumParams(List<Object> params) {
		if (params.size() < numParams) {
			return String.format(errors.getString("MathTooFewParams"), params.size());
		}
		else {
			return null;
		}
	}
}
