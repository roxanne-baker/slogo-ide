package commands;

import java.util.List;

import controller.BackgroundController;

public class ClearStamps extends Command implements Executable {

	BackgroundController backgroundController;
	
	public ClearStamps(BackgroundController bgController) {
		this.backgroundController = bgController;
		numParams = 0;
	}

	public double execute(List<Object> params) {
		return backgroundController.clearStamps();
	}

	public String checkParamTypes(List<Object> params) {
		return null;
	}
}
