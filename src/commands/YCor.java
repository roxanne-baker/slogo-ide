package commands;
import java.util.List;
import controller.TurtleController;

public class YCor extends TurtleQueryCommands implements Executable{

	TurtleController turtleController;
	public YCor(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		return turtleController.getCurrentAgentYPosition();
	}
}