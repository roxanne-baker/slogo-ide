package commands;

import java.util.List;

import controller.BackgroundController;

public class SetBackground extends Command implements Executable {

	BackgroundController colorPickerController;
	
	public SetBackground(BackgroundController colorPickerController) {
		numParams = 1;
		this.colorPickerController = colorPickerController;
	}
	
	public Object execute(List<Object> params) {
		colorPickerController.setColorForBackgroundView(paramToInt(params.get(0)));
		return paramToInt(params.get(0));
	}
	
	private int paramToInt(Object param) {
		if (!(param instanceof Double || param instanceof Integer)) {
			System.out.println("Invalid param type!");
			return -1;
		}
		else if (param instanceof Double) {
			return ((Double) param).intValue();
		}
		return (int) param;
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			int intParam = paramToInt(param);
			if (intParam < 0 || intParam > colorPickerController.getNumColors()) {
				return "Color index out of range!";
			}
		}
		return null;
	}
	
}
