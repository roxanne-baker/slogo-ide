package commands;

import java.util.Arrays;
import java.util.List;

import controller.TurtleController;
import view.Agent;

public class SetHeading extends TurtleCommand implements Executable {

	public SetHeading(TurtleController turtleController) {
		setTurtleController(turtleController);
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
//		List<Integer> activeAgents = getTurtleController().getActiveAgents();
//		double[] changeDegrees = new double[]{activeAgents.size()};
//		if (params.get(0) instanceof Double) {
//			Arrays.fill(changeDegrees, (double) params.get(0));
//		}
//		else {
//			changeDegrees = (double[]) params.get(0);
//		}			
//		getTurtleController().changeTurtleProperty(changeDegrees, (Agent agent, Double degrees) -> agent.changeOrientation(degrees));
//		return changeDegrees;
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
