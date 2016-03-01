package commands;

import java.util.List;

import controller.TurtleController;

public class Back extends Command implements Executable {

	TurtleController turtleController;
	public Back(TurtleController turtleController) {
		numParams = 1;
		this.turtleController = turtleController;
	}
	
	public double execute(List<Object> params) {
		double distance = (Double) params.get(0);
		double orientation = turtleController.getCurrentAgentOrientation();
		double changeX = -distance*Math.sin(Math.toRadians(orientation));
		double changeY = distance*Math.cos(Math.toRadians(orientation));
		
		turtleController.moveCurrentAgent(changeX, changeY);
	
		return distance;
	}
	
	public String checkParamTypes(List<Object> params) {
		Object paramValue = params.get(0);
		if (paramValue instanceof Integer || paramValue instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), paramValue.toString());
		}
	}
	
	
}
