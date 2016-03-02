
package commands;

import java.util.List;
import controller.VariablesController;
import model.Interpreter;


public class For extends ControlCommand implements Executable {

	Interpreter interpreter;
	VariablesController variableController;
	
	public For(Interpreter interpreter, VariablesController variableController) {
		this.interpreter = interpreter;
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// WANT TO GET MAX VALUE FOR VARIABLE
		String variableStartEndIncrement = (String) params.get(0);
		String[] forLoopCondition = variableStartEndIncrement.split(" ");
		String varName = forLoopCondition[0];
		double start = Double.parseDouble(forLoopCondition[1]);
		double end = Double.parseDouble(forLoopCondition[2]);
		double increment = Double.parseDouble(forLoopCondition[3]);
		
		String commands = (String) params.get(1);
		
		for (double i=start; i<end; i+= increment) {
			interpreter.run("MAKE "+varName+" "+i);
			variableController.addVariable(varName, ""+i);
			interpreter.run(commands);
		}

		//NEED TO ADD IN RETURN VALUE
		return 0;
	}	

	@Override
	public String checkNumParams(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof String)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}
		}
		return null;
	}
}
