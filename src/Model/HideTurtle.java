package Model;

import java.util.List;

public class HideTurtle extends Command implements Executable {

	public HideTurtle() {
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return 0;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}
