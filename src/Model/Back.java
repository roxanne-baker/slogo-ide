package Model;

import java.util.List;

public class Back extends Command implements Executable {

	// SAME AS FORWARD
	// DIFFERENT EXECUTE, OTHER METHODS SAME
	public Back() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		return (double) params.get(0).getValue();
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		Object paramValue = params.get(0).value;
		if (paramValue instanceof Integer || paramValue instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), paramValue.toString());
		}
	}
	
	
}
