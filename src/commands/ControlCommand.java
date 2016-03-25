// This entire file is part of my masterpiece.
// Carolyn Yao
// I've placed the error-checking code for control commands here, and taken out duplicate code in relevant subclasses. 
// because almost all the control commands use this except for dotimes 

package commands;
import java.util.List;

public abstract class ControlCommand extends Command {
	// overrides the main Command class' checkParamTypes, which will apply for Math/Turtle commands (majority commands)
	@Override public String checkParamTypes(List<Object> params) {
		Object param = params.get(0);
		if (!(param instanceof Integer || param instanceof Double)) {
			return String.format(errors.getString("WrongParamType"), param.toString());
		}
		for (int i=1; i<params.size(); i++) {
			Object command = params.get(i);
			if (!(command instanceof String)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}
		}
		return null;
	}	
	
}
