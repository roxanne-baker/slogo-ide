package commands;

import java.util.List;

public interface Executable {

	/**
	 * @return a double representing the result of execute
	 * Method will also tell the front-end what changes it
	 * needs to make for display if applicable
	 * This could be a change in the position/color of a 
	 * particular turtle, the addition of a variable, etc.
	 **/
	public Object execute(List<Object> params);
	
	/**
	 * This method will take the user input and determine if the
	 * type of the parameters fits the command
	 * @param userInput
	 * @return String representing error message
	 * or null if there is no error
	 */	
	public String checkParamTypes(List<Object> params);
}
