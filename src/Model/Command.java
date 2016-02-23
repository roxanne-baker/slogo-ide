package Model;

public class Command {
 
	int num_params; 
	protected ResourceBundle errors = ResourceBundle.getBundle("resources/Errors");
	
	public Command() {
	}
	
	public String checkNumParams(List<ParseNode> params) {
		if (params.size() != numParams) {
			return errors.getString(WrongNumParams), numParams, params.size();
		}
		else {
			return null;
		}
	}

}
