package commands;

import java.util.List;

import controller.BackgroundController;

public class SetBackground extends Command implements Executable {

	BackgroundController backgroundController;
	
	public SetBackground(BackgroundController backgroundController) {
		numParams = 1;
		this.backgroundController = backgroundController;
	}
	
	public Object execute(List<Object> params) {
		backgroundController.setColorForBackgroundView(paramToInt(params.get(0)));
		return paramToInt(params.get(0));
	}
	
	private int paramToInt(Object param) {
		if (!(param instanceof Double || param instanceof Integer)) {
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
		for (Object param : params) {
			int intParam = paramToInt(param);
			if (intParam < 0 || intParam > backgroundController.getPaletteSize()) {
				return String.format(errors.getString("ColorIndex"), backgroundController.getPaletteSize());
			}
		}
		return null;
	}
	
}
