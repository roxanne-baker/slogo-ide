package model;

import java.util.List;

public class Pi extends Command implements Executable {

	public Pi() {
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return Math.PI;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}
