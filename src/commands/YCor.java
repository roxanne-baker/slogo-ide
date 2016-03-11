package commands;
import java.util.List;
import controller.TurtleController;
import view.Agent;

public class YCor extends TurtleQueryCommands implements Executable{

	public YCor(TurtleController turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> -agent.getYPosition());
	}
}