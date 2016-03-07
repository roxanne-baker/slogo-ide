package commands;

import java.util.List;

public class Product extends MathCommand implements Executable {

	public Product() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		int product = 1;
		for (Object param : params) {
			double val = (double) param;
			product *= val;
		}
		return product;
	}
	
	@Override
	public String checkNumParams(List<Object> params) {
		if (params.size() < 2) {
			return String.format(errors.getString("MathTooFewParams"), params.size());
		}
		else {
			return null;
		}
	}
	
	
}