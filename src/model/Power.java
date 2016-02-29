package model;

import java.util.List;

public class Power extends Command implements Executable {

	public Power() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		double base = (double) params.get(0);
		double exponent = (double) params.get(1);
		return Math.pow(base, exponent);
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
