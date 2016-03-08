package commands;

import java.util.List;

import controller.ColorPickerController;

public class SetPalette extends Command implements Executable {

	ColorPickerController colorPickerController;
	
	public SetPalette(ColorPickerController colorPickerController) {
		numParams = 4;
		this.colorPickerController = colorPickerController;
	}
	
	public double execute(List<Object> params) {
		colorPickerController.addColor(paramToInt(params.get(0)),
				paramToInt(params.get(1)), paramToInt(params.get(2)), paramToInt(params.get(3)));
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
		int index = paramToInt(params.get(0));
		if (index < 0 || index > colorPickerController.getNumColors()) {
			return "Index too high!";
		}
		for (Object param : params) {
			int intParam = paramToInt(param);
			if (intParam < 0 || intParam > 255) {
				return "Invalid color value!";
			}
		}
		return null;
	}
	
}
