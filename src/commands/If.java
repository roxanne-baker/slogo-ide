package commands;

import java.util.List;

import model.Interpreter;



public class If extends ControlCommand implements Executable {

	Interpreter interpreter;
	
	public If(Interpreter interpreter) {
		this.interpreter = interpreter;
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		double ifCondition = (double) params.get(0);
		String commands = (String) params.get(1);
		
		if (ifCondition != 0) {
			interpreter.run(commands);
		}
		return interpreter.getReturnResult();
	}	
}
