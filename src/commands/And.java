package commands;

import java.util.List;

public class And extends Command implements Executable {

	public And() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		for (Object param : params) {
			if ((double) param == 0) {
				return 0;
			}
		}
		return 1;
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
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
	
}
