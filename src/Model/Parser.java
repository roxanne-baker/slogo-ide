package Model;

import java.util.HashMap;
import java.util.Map;

public class Parser {

	protected Map<String, Command> commandsMap; 
	
	public Parser() {
		initializeCommandsMap();
	}
	
	public void run(String userInput) { 
		Command c = commandsMap.get(getCommandName(userInput));
		// c.checkError(); 
		// c.execute(); 
	}
	
	public boolean errorCommandName(String userInput) {
		String commandName = getCommandName(userInput);
		if (!commandsMap.containsKey(commandName)) { 
			return true;
		}
		else return false;  
	}

	private String getCommandName(String input) { 
		String[] commandAndParams = input.split(" "); 
		return commandAndParams[0];
	}
	
	private void initializeCommandsMap() { 
		commandsMap = new HashMap<String, Command>(); 
		// for all command c somewhere 
		// addCommandtoMap(c); 
	}
	
	private void addCommandtoMap(Command c) { 
		for (String name: c.getNames()) { 
			commandsMap.put(name, c); 
		}
	}
}
