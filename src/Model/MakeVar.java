package Model;

import java.util.List;

public class MakeVar extends Command implements Executable {

	public MakeVar() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		// what if expression not a number?  need to account for later
		double val = (double) params.get(1);
		return val;
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
