package commands;

import java.util.List;

public class Divide extends MathCommand implements Executable {
	
	public Divide() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		double numerator = (Double) params.get(0);
		double denominator = (Double) params.get(1);
		return numerator / denominator;
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		int divisor = ((Double) params.get(1)).intValue();
		if (divisor == 0) {
			return String.format(errors.getString("DivideByZero"));
		}
		
		return null;
	}
	
}