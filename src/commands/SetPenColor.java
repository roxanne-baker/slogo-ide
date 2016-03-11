package commands;

import java.util.Arrays;
import java.util.List;
import controller.TurtleController;
import view.Agent;

public class SetPenColor extends Command implements Executable {
	
	TurtleController turtleController;
	
	public SetPenColor(TurtleController turtleController) {
		numParams = 1;
		this.turtleController = turtleController;
	}
	
	public Object execute(List<Object> params) {
		turtleController.changeTurtleProperty(paramToDoubleArray(params.get(0)), (Agent agent, Double index) -> agent.setPenColorIndex(index.intValue()));
		return paramToDoubleArray(params.get(0));
	} 
	
	private double[] paramToDoubleArray(Object param) {
		double[] penColorArray = null;
		if (!(param instanceof Double || param instanceof Integer || param instanceof double[])) {
			System.out.println("Invalid param type!");
		}
		else if (param instanceof Double) {
			penColorArray = new double[turtleController.getActiveAgents().size()];
			Arrays.fill(penColorArray, ((Double) param));
		}
		else if (param instanceof double[]) {
			penColorArray = (double[]) param;
		}
		return penColorArray;
	}
	
	public String checkParamTypes(List<Object> params) {
		double[] paramArray = paramToDoubleArray(params.get(0));
		for (int i=0; i<paramArray.length; i++) {
			if (paramArray[i] < 0 || paramArray[i] > turtleController.getColorPaletteSize()) {
				return String.format(errors.getString("ColorIndex"), turtleController.getColorPaletteSize());
			}
		}
		return null;
	}
	
}
