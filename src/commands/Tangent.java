package commands;

import java.util.List;

public class Tangent extends MathCommand implements Executable {

	public Tangent() {
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double) {
			return Math.tan(Math.toRadians((double) params.get(0)));
		}
		else {
			double[] paramArray = (double[]) params.get(0);
			for (int i=0; i<paramArray.length; i++) {
				paramArray[i] = Math.tan(Math.toRadians(paramArray[i]));
			}
			return paramArray;
		}
	}
}