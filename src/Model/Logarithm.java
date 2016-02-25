package Model;

import java.util.List;

public class Logarithm extends Command implements Executable {

	public Logarithm() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
		double expr = (double) params.get(0).getValue();
		return Math.log(expr);
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
