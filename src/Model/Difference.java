package Model;

import java.util.List;

public class Difference extends Command implements Executable {

	public Difference() {
		numParams = 2;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		double minuend = (double) params.get(0).getValue();
		double subtrahend = (double) params.get(1).getValue();
		return minuend - subtrahend;
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
