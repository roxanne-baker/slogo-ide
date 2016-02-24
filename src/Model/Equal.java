package Model;

import java.util.List;

public class Equal extends Command implements Executable {

	public Equal() {
		numParams = 2;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		if ((double) params.get(0).getValue() == (double) params.get(1).getValue()) {
			return 1;
		}
		else {
			return 0;
		}
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
