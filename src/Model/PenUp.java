package Model;

import java.util.List;

public class PenUp extends Command implements Executable {

	public PenUp() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		return 1;
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		return null;
	}
	
	
}
