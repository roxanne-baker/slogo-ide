package Model;

import java.util.List;

<<<<<<< HEAD
public class PenDown extends Command implements Executable {

	public PenDown() {
		numParams = 0;
	}
	
	public double execute(List<ParseNode> params) {
=======
import Controller.TurtleController;

public class PenDown extends Command implements Executable {

	public PenDown(TurtleController turtleController) {
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
>>>>>>> 1f69d547baa990fb4570051f44f23a0db6d94dd2
		// need to figure out how to communicate with front-end
		return 1;
	}
	
<<<<<<< HEAD
	public String checkParamTypes(List<ParseNode> params) {
=======
	public String checkParamTypes(List<Object> params) {
>>>>>>> 1f69d547baa990fb4570051f44f23a0db6d94dd2
		return null;
	}
	
	
}
