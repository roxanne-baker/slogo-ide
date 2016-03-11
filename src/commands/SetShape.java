package commands;

import java.util.Arrays;
import java.util.List;
import controller.TurtleController;
import view.Agent;

public class SetShape extends Command implements Executable {
	
	TurtleController turtleController;
	
	public SetShape(TurtleController turtleController) {
		numParams = 1;
		this.turtleController = turtleController;
	}
	
	public Object execute(List<Object> params) {
		turtleController.changeTurtleProperty(paramToDoubleArray(params.get(0)), (Agent agent, Double index) -> agent.setCurrentImageIndex(index.intValue()));
		return paramToDoubleArray(params.get(0));
	} 
	
	private double[] paramToDoubleArray(Object param) {
		double[] shapeArray = null;
		if (!(param instanceof Double || param instanceof Integer || param instanceof double[])) {
			System.out.println("Invalid param type!");
		}
		else if (param instanceof Double) {
			shapeArray = new double[turtleController.getActiveAgents().size()];
			Arrays.fill(shapeArray, ((Double) param));
		}
		else if (param instanceof double[]) {
			shapeArray = (double[]) param;
		}
		return shapeArray;
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			double[] intParam = paramToDoubleArray(param);
//			if (intParam < 0 || intParam > colorPickerController.getNumColors()) {
//				return "Color index out of range!";
//			}
		}
		return null;
	}
	
}
