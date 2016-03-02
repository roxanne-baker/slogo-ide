package commands;

import java.util.List;

public class ArcTangent extends OperationCommand implements Executable {

	public ArcTangent() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double degrees = (Double) params.get(0);
		return Math.atan(degrees);
	}	
}
