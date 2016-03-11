
package commands;

import java.util.List;

public class Minus extends MathCommand implements Executable {

	public Minus() {
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		if (params.get(0) instanceof Double) {
			return -((Double) params.get(0));
		}
		else {
			double[] paramArray = (double[]) params.get(0);
			for (int i=0; i<paramArray.length; i++) {
				paramArray[i] *= -1;
			}
			return paramArray;
		}
	}
}
