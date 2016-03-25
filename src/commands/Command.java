package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ControllerBackground;
import controller.ControllerTurtle;
import controller.ControllerVariables;
import controller.MethodsController;
import parsing.Interpreter;

public abstract class Command implements Executable {
 
	protected int numParams; 
	protected ResourceBundle errors = ResourceBundle.getBundle("resources.errors/Errors");
	protected boolean needsVarName = false;
	protected List<Object> params; 
	protected Interpreter interpreter;
	protected ControllerTurtle turtleController;
	protected ControllerVariables variableController;
	protected ControllerBackground backgroundController;
	protected MethodsController methodController;
	
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
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double || param instanceof double[])) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
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
	
	public void setMethodController(MethodsController mc) { 
		methodController = mc;
	}
	public void setVariablesController(ControllerVariables vc) { 
		variableController = vc; 
	}
	
	public void setBackgroundController(ControllerBackground bc) { 
		backgroundController = bc;
	}
	public void setTurtleController(ControllerTurtle tc) { 
		turtleController = tc;
	}
	public void setInterpreter(Interpreter ip) { 
		interpreter= ip; 
	}

}