package commands;

import java.util.List;

import controller.VariablesController;
import model.Interpreter;

public class CreatedMethod extends Command implements Executable {

	private Interpreter interpreter;
	private VariablesController variablesController;
	private String name;
	private String[] paramNames;
	private String commands;
	
	public CreatedMethod(Interpreter interpreter, VariablesController variablesController, String methodName, String[] paramNames, String commands) {
		this.interpreter = interpreter;
		this.variablesController = variablesController;
		this.name = methodName;
		this.paramNames = paramNames;
		this.commands = commands;
		numParams = paramNames.length;
//		for (int i=0; i<numParams; i++) {
//			if (variablesController.getVariable(paramNames[i]) == null) {
//				variablesController.addVariable(paramNames[i], "0");
//			}
//		}

	}
	
	public double execute(List<Object> params) {
		for (int i=0; i<paramNames.length; i++) {
			variablesController.addVariable(paramNames[i], ""+params.get(i));
		}
		System.out.println(commands);
		interpreter.run(commands);
		return 0;
	}	
	
	public String getMethodCommands() { 
		return commands;
	}
	
	public String getMethodName() { 
		return name;
	}

	public String checkParamTypes(List<Object> params) {
		for (int i=1; i<params.size(); i++) {
			Object command = params.get(i);
			if (!(command instanceof Integer || command instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), command.toString());
			}
		}
		return null;
	}	
	
	
}
