package Model;

public class Forward extends Command implements Executable {

	numParams = 1;
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		return params.get(0).value;
	}
	
	public String checkNumParams(List<ParseNode> params) {
		if (params.size() != 1) {
			return "Should have 1 parameter, have "+params.size();
		}
		else {
			return null;
		}
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		Object paramValue = params.get(0).value;
		if (param instanceof Integer || param instanceof Double) {
			return null;
		}
		else {
			return "Parameter "+param+" should be a number"
		}
	}
	
	
}
