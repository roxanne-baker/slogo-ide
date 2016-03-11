package commands;

import java.util.List;
import java.util.regex.Pattern;

import controller.MethodsController;
import controller.VariablesController;
import model.Interpreter;

public class To extends Command implements Executable {

	Interpreter interpreter;
	VariablesController variablesController;
	MethodsController methodController;
	
	public To(Interpreter interpreter, VariablesController variablesController, MethodsController methodController) {
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
		methodController.addMethod(methodName, createdMethod);	
		return 0;
	}	
	
	public String checkParamTypes(List<Object> params) {
		for (int i=1; i<params.size(); i++) {
			Object command = params.get(i);
			if (!(command instanceof String)) {
				return String.format(errors.getString("WrongParamType"), command.toString());
			}
		}
		String varNamesParam = (String) params.get(1);
		String[] varNamesArray = varNamesParam.split(" ");
		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		for (int i=0; i<varNamesArray.length; i++) {
			if (!varNamesArray[i].startsWith(":")) {
				return String.format(errors.getString("VariablePrecededByColon"));	
			}
			else if ((p.matcher(varNamesArray[i].substring(1)).find())) {
				return String.format(errors.getString("VariableHasInvalidChar"));				
			}
		}		
		return null;
	}	
	
}