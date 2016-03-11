package commands;

import java.util.List;

public class ArcTangent extends MathCommand implements Executable {

	public ArcTangent() {
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double) {
			return Math.atan(Math.toRadians((double) params.get(0)));
		}
		else {
			double[] paramArray = (double[]) params.get(0);
			for (int i=0; i<paramArray.length; i++) {
				paramArray[i] = Math.atan(Math.toRadians(paramArray[i]));
			}
			return paramArray;
		}
	}
}
