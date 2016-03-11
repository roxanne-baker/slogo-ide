package commands;

import java.util.List;

public class Sine extends MathCommand implements Executable {

	public Sine() {
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double) {
			return Math.sin(Math.toRadians((double) params.get(0)));
		}
		else {
			double[] paramArray = (double[]) params.get(0);
			for (int i=0; i<paramArray.length; i++) {
				paramArray[i] = Math.sin(Math.toRadians(paramArray[i]));
			}
			return paramArray;
		}
	}
}