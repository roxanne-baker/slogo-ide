
package commands;

import java.util.List;

public class Minus extends MathCommand implements Executable {

	public Minus() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return -((Double) params.get(0));
	}
}
