// This entire file is part of my masterpiece.
// Roxanne Baker
// This class demonstrates the generalization of boolean commands to just a simple comparison
// It also gets rid of the ugly code that continuously refers to whether the current parameter was
// passed in as a "double" or "double[]"

package commands;

import java.util.List;
import java.util.function.BiFunction;

public abstract class BooleanCommand extends Command{

	private BiFunction<Double, Double, Double> boolFunction;
	
	public BooleanCommand() {
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {		
		return getReturnValue(params.get(0), params.get(1));
	}
	
	private double[] getReturnValue(Object firstExpr, Object secondExpr) {
		int numAgents = getNumAgents(params);
		double[] returnValues = new double[numAgents];
		for (int i=0; i<numAgents; i++) {
			double firstExprValue = getExprToCompare(firstExpr, i);
			double secondExprValue = getExprToCompare(secondExpr, i);
			returnValues[i] = boolFunction.apply(firstExprValue, secondExprValue);
		}
		 return returnValues;
	}

	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!((param instanceof Double) || (param instanceof double[]))) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
	protected void setBoolFunction(BiFunction<Double, Double, Double> boolFunction) {
		this.boolFunction = boolFunction;
	}	
}