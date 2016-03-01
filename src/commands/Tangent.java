package commands;

import java.util.List;

public class Tangent extends Command implements Executable {

	public Tangent() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double degrees = (double) params.get(0);
		return Math.tan(degrees);
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