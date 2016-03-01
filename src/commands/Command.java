package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class Command implements Executable {
 
	public int numParams; 
	protected ResourceBundle errors = ResourceBundle.getBundle("resources.errors/Errors");
	private List<Object> params; 
	
	public Command() {
		params = new ArrayList<Object>();
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

}