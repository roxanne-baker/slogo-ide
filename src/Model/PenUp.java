package Model;

import java.util.List;

<<<<<<< HEAD
public class PenUp extends Command implements Executable {

	public PenUp() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
=======
import Controller.TurtleController;

public class PenUp extends Command implements Executable {

	public PenUp(TurtleController turtleController) {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return 1;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}
