package commands;

import java.util.Arrays;
import java.util.List;
import controller.TurtleController;
import view.Agent;

public class SetPenSize extends Command implements Executable {
	
	TurtleController turtleController;
	
	public SetPenSize(TurtleController turtleController) {
		numParams = 1;
		this.turtleController = turtleController;
	}
	
	public Object execute(List<Object> params) {
		double[] penThicknessToSet = new double[turtleController.getActiveAgents().size()];
		if (params.get(0) instanceof Double) {
			Arrays.fill(penThicknessToSet, (double) params.get(0));
		}
		else {
			penThicknessToSet = (double[]) params.get(0);
		}
		turtleController.changeTurtleProperty(penThicknessToSet,
				(Agent agent, Double thickness) -> agent.setPenThickness(thickness));
		
		return penThicknessToSet;
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double || param instanceof double[])) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
}
