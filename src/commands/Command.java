// This entire file is part of my masterpiece.
// Roxanne Baker
// The handling of the ambiguity of a double/double[] parameter was reduced here
// to only be addressed in a few brief methods
// It allows for flexibility in the number of parameters (despite the "multiple parameters" not being implemented)
// It also gets rid of the ugly code that continuously refers to whether the current parameter was
// passed in as a "double" or "double[]"

package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class Command implements Executable {
 
	protected int numParams; 
	protected ResourceBundle errors = ResourceBundle.getBundle("resources.errors/Errors");
	protected boolean needsVarName = false;
	protected List<Object> params; 
	
	public Command() {
		params = new ArrayList<Object>();
	}
	
	public boolean isNeedsVarName() {
		return needsVarName;
	}

	public void setNeedsVarName(boolean needsVarName) {
		this.needsVarName = needsVarName;
	}

	public void addParam(Object param) { 
		params.add(param);
	}

	public int getNumParams() {
		return numParams;
	}
	
	/*
	 * Since doubles should not be compared directly,
	 * this method tests if the values are close enough
	 * to be deemed equal for our purposes
	 */
	protected boolean isEqual(double a, double b) {
		if (Math.abs(a - b) < 0.00001) {
			return true;
		}
		return false;
	}
	
	/*
	 * Used to handle cases of ambiguity where a parameter
	 * may have been passed in as either a double or a double[]
	 */
	protected Double getExprToCompare(Object expr, int index) {
		if (expr instanceof Double) {
			return (double) expr;
		}
		else {
			return ((double[]) expr)[index];
		}
	}
	
	/*
	 * Determines the number of elements in any arrays contained in parameters
	 * Otherwise returns 1
	 * This is effectively equal to the current number of agents
	 */
	protected int getNumAgents(List<Object> params) {
		for (Object param : params) {
			if (param instanceof double[]) {
				return ((double[]) param).length;
			}
		}
		return 1;
	}

}
