package Model;

import java.util.List;
import java.util.ResourceBundle;

public class Command {
 
	int numParams; 
	protected ResourceBundle errors = ResourceBundle.getBundle("resources/Errors");
	
	public Command() {
	}
	
	public String checkNumParams(List<ParseNode> params) {
		if (params.size() != numParams) {
			return String.format(errors.getString("WrongNumParams"), numParams, params.size());
		}
		else {
			return null;
		}
	}

}
