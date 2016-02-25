package Model;

import java.util.List;

public class Divide extends Command implements Executable {

	public Divide() {
		numParams = 2;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		double numerator = (double) params.get(0).getValue();
		double denominator = (double) params.get(1).getValue();
		return numerator / denominator;
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		for (ParseNode param : params) {
			Object paramValue = param.getValue();
			if (!(paramValue instanceof Integer || paramValue instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), paramValue.toString());
			}	
			else if ((int) paramValue == 0) {
				return errors.getString("DivideByZero");
			}
		}
		return null;
	}
	
	
}
