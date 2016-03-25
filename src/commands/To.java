// This entire file is part of my masterpiece.
// Carolyn Yao
// I replaced some previous code so that this command could call on the Interpreter parser API to detect whether the variable
// names were valid before constructing the user-defined method

package commands;
import java.util.List;
import controller.MethodsController;
import parsing.Interpreter;
import controller.ControllerVariables;

public class To extends Command implements Executable {

	Interpreter interpreter;
	ControllerVariables variablesController;
	MethodsController methodController;
	
	public To(Interpreter interpreter, ControllerVariables variablesController, MethodsController methodController) {
		this.methodController = methodController;
		this.variablesController = variablesController;
		this.interpreter = interpreter;
		numParams = 3;
		needsVarName = true;
	}
	
	public Object execute(List<Object> params) {
		String methodName = ((String) params.get(0)).trim();
		String paramNames = ((String) params.get(1)).trim();
		String[] varNamesArray;
		if (paramNames.isEmpty()) { 
			varNamesArray = new String[0];
		}
		else { 
			varNamesArray = ((String) params.get(1)).trim().split(" ");
		}
		String commands = (String) params.get(2);
		CreatedMethod createdMethod = new CreatedMethod(interpreter, variablesController, methodName, varNamesArray, commands);
		interpreter.addCommandToMap(createdMethod);
		methodController.addMethod(methodName + " " + paramNames, createdMethod);	
		return 0;
	}	
	
	@Override public String checkParamTypes(List<Object> params) {
		for (int i=1; i<params.size(); i++) {
			Object command = params.get(i);
			if (!(command instanceof String)) {
				return String.format(errors.getString("WrongParamType"), command.toString());
			}
		}
		String[] varNamesArray;
		String varNamesParam = (String) params.get(1);
		// took out the if/else: we shouldn't ever put an empty string through a loop
		if (!varNamesParam.isEmpty()) { 
			varNamesArray = ((String) params.get(1)).trim().split(" ");
		// edited the code here so that we could use the Interpreter API to parse and detect whether the string contained
		// valid variables names before proceeding. then, reference an error resource file
			for (int i=0; i<varNamesArray.length; i++) {
				if (!interpreter.parseText(varNamesArray[i]).equals("Variable")) {
					return String.format(errors.getString("VariableInvalidName"), varNamesArray[i]);	
				}
			}
		}
		return null;
	}	
}