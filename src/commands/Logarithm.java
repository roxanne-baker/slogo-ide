package commands;

import java.util.List;

public class Logarithm extends MathCommand implements Executable {

	public Logarithm() {
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double) {
			return Math.log((double) params.get(0));
		}
		else {
			double[] paramArray = (double[]) params.get(0);
			for (int i=0; i<paramArray.length; i++) {
				paramArray[i] = Math.log(paramArray[i]);
			}
			return paramArray;
		}
	}
}