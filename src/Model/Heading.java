package Model;

import java.util.List;

import Controller.TurtleTracker;

public class Heading extends Command implements Executable{
	TurtleTracker turtleTracker;
	
	public Heading(TurtleTracker turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		return turtleTracker.getCurrentAgentOrientation();
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
}
