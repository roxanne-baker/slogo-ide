package Model;

import java.util.List;

public class Forward extends Command implements Executable {

	public Forward() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		return (double) params.get(0).getValue();
	}
	
<<<<<<< HEAD
=======
	public String checkNumParams(List<ParseNode> params) {
		if (params.size() != 1) {
			return String.format(errors.getString("WrongNumParams"), 1, params.size());
		}
		else {
			return null;
		}
	}
	
>>>>>>> carolyn
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
