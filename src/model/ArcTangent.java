package model;

import java.util.List;

public class ArcTangent extends Command implements Executable {

	public ArcTangent() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double degrees = (Double) params.get(0);
		return Math.atan(degrees);
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
