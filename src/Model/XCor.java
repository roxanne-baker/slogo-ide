package Model;

import java.util.List;

import Controller.TurtleController;

public class XCor extends Command implements Executable{

	public XCor(TurtleController turtleController) {
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
