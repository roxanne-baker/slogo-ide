
package Model;

import java.util.List;

public class Sum extends Command implements Executable {

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
		if (params.size() < 2) {
			return String.format(errors.getString("MathTooFewParams"), params.size());
		}
		else {
			return null;
		}
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
	
}
