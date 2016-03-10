package commands;

import java.util.List;
import controller.TurtleController;

public class SetPenSize extends Command implements Executable {
	
	TurtleController turtleController;
	
	public SetPenSize(TurtleController turtleController) {
		numParams = 1;
		this.turtleController = turtleController;
	}
	
	public Object execute(List<Object> params) {
		turtleController.setCurrentAgentPenThickness((double) params.get(0));
		return (double) params.get(0);
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
}
