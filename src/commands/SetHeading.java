package commands;

import java.util.Arrays;
import java.util.List;

import controller.ControllerTurtle;
import view.Agent;

public class SetHeading extends TurtleCommand implements Executable {

	public SetHeading(ControllerTurtle turtleController) {
		super(turtleController);
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		double[] orientationsToSet = new double[getTurtleController().getActiveAgents().size()];
		if (params.get(0) instanceof Double) {
			Arrays.fill(orientationsToSet, (double) params.get(0));
		}
		else {
			orientationsToSet = (double[]) params.get(0);
		}
		getTurtleController().changeTurtleProperty(orientationsToSet,
				(Agent agent, Double degrees) -> agent.changeOrientation(degrees - agent.getOrientation()));
		
		return orientationsToSet;
	}
}
