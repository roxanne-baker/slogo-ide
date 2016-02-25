package Model;

import java.util.List;

<<<<<<< HEAD
public class Forward extends Command implements Executable {

	public Forward() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		return (double) params.get(0).getValue();
	}
	
<<<<<<< HEAD
=======
	public String checkNumParams(List<ParseNode> params) {
		if (params.size() != 1) {
			return String.format(errors.getString("WrongNumParams"), 1, params.size());
		}
		else {
			return null;
		}
	}
	
>>>>>>> carolyn
	public String checkParamTypes(List<ParseNode> params) {
		Object paramValue = params.get(0).value;
		if (paramValue instanceof Integer || paramValue instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), paramValue.toString());
=======
import View.Turtle;
import Controller.TurtleController;

public class Forward extends Command implements Executable {

	Turtle currentTurtle;
	public Forward(TurtleController turtleController) {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		
	
		return (double) params.get(0);
	}
	
	public String checkParamTypes(List<Object> params) {
		Object param = params.get(0);
		if (param instanceof Integer || param instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), param.toString());
		}
	}
	
	
}
