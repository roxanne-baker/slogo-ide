package Model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Parser {

	protected Map<String, Command> commandsMap; 
	private List<Entry<String, Pattern>> mySymbols;
	
	public Parser() {
		initializeCommandsMap();
        mySymbols = new ArrayList<>();
	}
	
	public void run(String userInput) { 
		Command c = commandsMap.get(getCommandName(userInput));
		// c.checkError(); 
		
		// c.execute(); 
	}

    // adds the given resource file to this language's recognized types
    public void addPatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key,
                           // THIS IS THE IMPORTANT LINE
                           Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    // returns the language's type associated with the given text if one exists 
    public String getSymbol (String text) {
        final String ERROR = "NO MATCH";
        for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        return ERROR;
    }

    // returns true if the given text matches the given regular expression pattern
    private boolean match (String text, Pattern regex) {
        // THIS IS THE KEY LINE
        return regex.matcher(text).matches();
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
	
	// recursive
	
//	private void populateParseTree(Command c) { 
//		CommandParseTree commandTree = new CommandParseTree(ParseNode());
//	}
//	
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
