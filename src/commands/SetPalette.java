package commands;

import java.util.List;

import controller.ControllerBackground;

public class SetPalette extends Command implements Executable {

	ControllerBackground backgroundController;
	
	public SetPalette(ControllerBackground backgroundController) {
		numParams = 4;
		this.backgroundController = backgroundController;
	}
	
	public Object execute(List<Object> params) {
		backgroundController.addColor(paramToInt(params.get(0)),
				paramToInt(params.get(1)), paramToInt(params.get(2)), paramToInt(params.get(3)));
		return paramToInt(params.get(0))+0.0;
	}
	
	private int paramToInt(Object param) {
		if (!(param instanceof Double || param instanceof Integer || param instanceof double[])) {
			return -1;
		}
		else if (param instanceof Double) {
			return ((Double) param).intValue();
		}
		else if (param instanceof double[]) {
			double[] paramArray = (double[]) param;
			return ((Double) paramArray[paramArray.length-1]).intValue();
		}
		return (int) param;
	}
	
	public String checkParamTypes(List<Object> params) {
		int index = paramToInt(params.get(0));
		if (index < 0 || index > backgroundController.getPaletteSize()) {
			return String.format(errors.getString("ColorIndex"), backgroundController.getPaletteSize());
		}
		for (Object param : params) {
			int intParam = paramToInt(param);
			if (intParam < 0 || intParam > 255) {
				return String.format(errors.getString("ColorVal"));
			}
		}
		return null;
	}
	
}
