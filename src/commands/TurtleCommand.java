package commands;

import java.util.List;

import controller.TurtleController;

public abstract class TurtleCommand extends Command implements Executable{

	private TurtleController turtleController;
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
	protected void setTurtleController(TurtleController turtleController) {
		this.turtleController = turtleController;
	}
	
	protected TurtleController getTurtleController() {
		return turtleController;
	}
	
}
