
package commands;

import java.util.Arrays;
import java.util.List;

import controller.ControllerTurtle;
import model.Agent;

public class Right extends TurtleCommand implements Executable {

	public Right(ControllerTurtle turtleController) {
		super(turtleController);
		numParams = 1;
	}
		
	public Object execute(List<Object> params) {
		double[] changeDegrees = new double[getTurtleController().getActiveAgents().size()];
		if (params.get(0) instanceof Double) {
			Arrays.fill(changeDegrees, (double) params.get(0));
		}
		else {
			changeDegrees = (double[]) params.get(0);
		}			
		getTurtleController().changeTurtleProperty(changeDegrees, (Agent agent, Double degrees) -> agent.changeOrientation(degrees));
		return changeDegrees;
	}
}