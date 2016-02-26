
package Model;

import java.util.List;
import java.util.ResourceBundle;

public abstract class Command implements Executable {
 
	int numParams; 
	protected ResourceBundle errors = ResourceBundle.getBundle("resources.errors/Errors");
	
	public Command() {
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
