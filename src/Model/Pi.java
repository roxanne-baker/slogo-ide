package Model;

import java.util.List;

public class Pi extends Command implements Executable {

	public Pi() {
		numParams = 0;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		return Math.PI;
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		return null;
	}
	
	
}
