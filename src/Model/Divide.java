package model;

import java.util.List;

public class Divide extends Command implements Executable {

	public Divide() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		double numerator = (double) params.get(0);
		double denominator = (double) params.get(1);
		return numerator / denominator;
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}	
			else if ((int) param == 0) {
				return errors.getString("DivideByZero");
			}
		}
		return null;
	}
	
	
}
