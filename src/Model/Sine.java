package Model;

import java.util.List;

public class Sine extends Command implements Executable {

	public Sine() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
		double degrees = (double) params.get(0).getValue();
		return Math.sin(degrees);
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		for (ParseNode param : params) {
			Object paramValue = param.getValue();
			if (!(paramValue instanceof Integer || paramValue instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), paramValue.toString());
			}			
		}
		return null;
	}
	
	
}
