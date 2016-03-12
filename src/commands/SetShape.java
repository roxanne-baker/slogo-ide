package commands;

import java.util.Arrays;
import java.util.List;
import controller.TurtleController;
import view.Agent;

public class SetShape extends TurtleCommand implements Executable {
	
	public SetShape(TurtleController turtleController) {
		super(turtleController);
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeTurtleProperty(paramToDoubleArray(params.get(0)), (Agent agent, Double index) -> agent.setCurrentImageIndex(index.intValue()));
		return paramToDoubleArray(params.get(0));
	} 
	
	private double[] paramToDoubleArray(Object param) {
		double[] shapeArray = null;
		if (!(param instanceof Double || param instanceof Integer || param instanceof double[])) {
			shapeArray = new double[getTurtleController().getActiveAgents().size()];
			Arrays.fill(shapeArray, -1);	// will allow checkParamTypes to throw error
		}
		else if (param instanceof Double) {
			shapeArray = new double[getTurtleController().getActiveAgents().size()];
			Arrays.fill(shapeArray, ((Double) param));
		}
		else if (param instanceof double[]) {
			shapeArray = (double[]) param;
		}
		return shapeArray;
	}
	
	public String checkParamTypes(List<Object> params) {
		double[] paramArray = paramToDoubleArray(params.get(0));
		for (int i=0; i<paramArray.length; i++) {
			if (paramArray[i] < 0 || paramArray[i] > getTurtleController().getImagePaletteSize()) {
				return String.format(errors.getString("ColorIndex"), getTurtleController().getImagePaletteSize());
			}
		}
		return null;
	}
	
}
