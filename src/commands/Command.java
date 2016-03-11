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
	
	public String checkNumParams(List<Object> params) {
		if (params.size() != numParams) {
			return String.format(errors.getString("WrongNumParams"), numParams, params.size());
		}
		else {
			return null;
		}
	}

	public int getNumParams() {
		return numParams;
	}
	
	protected boolean isEqual(double a, double b) {
		if (Math.abs(a - b) < 0.00001) {
			return true;
		}
		return false;
	}

}