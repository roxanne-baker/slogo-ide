package Model;

import java.util.List;

public class ShowTurtle extends Command implements Executable {

	public ShowTurtle() {
		numParams = 0;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		return 1;
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		return null;
	}
	
	
}
