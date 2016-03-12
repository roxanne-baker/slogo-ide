package commands;

import java.util.List;

import controller.ControllerBackground;

public class ClearStamps extends Command implements Executable {

	ControllerBackground backgroundController;
	
	public ClearStamps(ControllerBackground bgController) {
		this.backgroundController = bgController;
		numParams = 0;
	}

	public Object execute(List<Object> params) {
		return backgroundController.clearStamps();
	}

	public String checkParamTypes(List<Object> params) {
		return null;
	}
}
