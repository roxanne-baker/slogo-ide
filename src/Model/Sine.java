
package model;

import java.util.List;

public class Sine extends Command implements Executable {

	public Sine() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double degrees = (double) params.get(0);
		return Math.sin(degrees);
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