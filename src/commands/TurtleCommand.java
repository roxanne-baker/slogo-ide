package commands;

import java.util.List;

import controller.ControllerTurtle;

public abstract class TurtleCommand extends Command implements Executable{

	private ControllerTurtle turtleController;
	
	public TurtleCommand(ControllerTurtle turtleController) {
		this.turtleController = turtleController;
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double || param instanceof double[])) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
	protected void setTurtleController(ControllerTurtle turtleController) {
		this.turtleController = turtleController;
	}
	
	protected ControllerTurtle getTurtleController() {
		return turtleController;
	}
	
}
