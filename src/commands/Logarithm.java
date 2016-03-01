package commands;

import java.util.List;

public class Logarithm extends Command implements Executable {

	public Logarithm() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double expr = (Double) params.get(0);
		return Math.log(expr);
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