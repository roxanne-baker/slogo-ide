
package commands;

import java.util.List;
import controller.TurtleController;
import model.Interpreter;
import view.Agent;

public class Forward extends TurtleCommand implements Executable {


	Interpreter interpreter;
	public Forward(TurtleController turtleController, Interpreter interpreter) {
		numParams = 1;
		needsVarName = true;
		setTurtleController(turtleController);
		this.interpreter = interpreter;
	}
	
	public double execute(List<Object> params) {
		double distance;
		if (params.get(0) instanceof Double) {
			distance = (Double) params.get(0);
		}
		else {
			interpreter.run((String) params.get(0));
			distance = interpreter.getReturnResult();
		}
		
//		double distance = (Double) params.get(0);
		double orientation = getTurtleController().getCurrentAgentOrientation();
		double changeX = distance*Math.sin(Math.toRadians(orientation));
		double changeY = -distance*Math.cos(Math.toRadians(orientation));
		getTurtleController().moveCurrentAgent(changeX, changeY);
//		getTurtleController().changeProperty((Agent agent) -> {
//			double orientation = getTurtleController().getCurrentAgentOrientation();
//			double changeX = distance*Math.sin(Math.toRadians(orientation));
//			double changeY = -distance*Math.cos(Math.toRadians(orientation));
//			agent.movePosition(changeX, changeY);
//		});
		return distance;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
}
