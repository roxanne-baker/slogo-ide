// This entire file is part of my masterpiece.
// Roxanne Baker
// This class demonstrates the generalization of the math commands
// It allows for flexibility in the number of parameters (despite the "multiple parameters" not being implemented)
// It also gets rid of the ugly code that continuously refers to whether the current parameter was
// passed in as a "double" or "double[]"

package commands;

import java.util.List;
import java.util.function.BiFunction;

public abstract class MathCommand extends Command {
	
	protected BiFunction<Double, Double, Double> operation;
	
	public MathCommand() {
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		int numAgents = getNumAgents(params);
		double[] returnVal = new double[numAgents];
		for (int i=0; i<numAgents; i++) {
			returnVal[i] = getExprToCompare(params.get(0), i);
			for (int j=1; j<params.size(); j++) {
				double nextExpr = getExprToCompare(params.get(j), i);
				returnVal[i] = operation.apply(returnVal[i], nextExpr);			
			}
		}
		return returnVal;
	}	
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double || param instanceof double[])) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
	protected void setOperation(BiFunction<Double, Double, Double> operation) {
		this.operation = operation;
	}
}
