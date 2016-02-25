package Model;

import java.util.List;

<<<<<<< HEAD
public class SetHeading extends Command implements Executable {

	public SetHeading() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		return (double) params.get(0).getValue();
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		Object paramValue = params.get(0).value;
		if (paramValue instanceof Integer || paramValue instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), paramValue.toString());
=======
import Controller.TurtleController;

public class SetHeading extends Command implements Executable {

	public SetHeading(TurtleController turtleController) {
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
