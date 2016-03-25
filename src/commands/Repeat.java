package commands;

import java.util.List;

import parsing.Interpreter;



public class Repeat extends ControlCommand implements Executable {

	Interpreter interpreter;
	
	public Repeat(Interpreter interpreter) {
		this.interpreter = interpreter;
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		String commands = (String) params.get(1);
		double numRepeats;
		if (params.get(0) instanceof Double) {
			numRepeats = (double) params.get(0);			
		}
		else {
			double[] numRepeatsArray = (double[]) params.get(0);
			numRepeats = numRepeatsArray[numRepeatsArray.length-1];
		}
		for (int i=0; i<numRepeats; i++) {
			interpreter.run(commands);		
		}
		return interpreter.getReturnResult();
	}	

}
