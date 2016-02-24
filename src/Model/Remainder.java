package Model;

import java.util.List;

public class Remainder extends Command implements Executable {

	public Remainder() {
		numParams = 2;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		int dividend = (int) params.get(0).getValue();
		int divisor = (int) params.get(1).getValue();

		return (dividend % divisor);
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		for (ParseNode param : params) {
			Object paramValue = param.getValue();
			if (!(paramValue instanceof Integer)) {
				return String.format(errors.getString("WrongParamType"), paramValue.toString());
			}			
		}
		return null;
	}
	
	
}
