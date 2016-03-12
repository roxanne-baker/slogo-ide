package commands;
import java.util.List;
import controller.ControllerTurtle;
import model.Agent;

public class YCor extends TurtleQueryCommands implements Executable{

	public YCor(ControllerTurtle turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> -agent.getYPosition());
	}
}