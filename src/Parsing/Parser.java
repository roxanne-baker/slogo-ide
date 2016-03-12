/**
 * NOTE: This it the example code ProgramParser in the CS308 repository example_regex, uploaded by Professor Duvall. 
 * Again, did not write this code. Using it for regex-matching purposes, using this as object class to be called on 
 * by the actual parsing engine in the Interpreter class. 
 */

package parsing;

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

	private List<Entry<String, Pattern>> mySymbols;
	private Map<String, String> myCommandSymbols;
    private final String resourcesPath = "resources/languages/";
	
	public Parser() {
		mySymbols = new ArrayList<>();
		myCommandSymbols = new HashMap<String, String>();
		addPatterns("English");
		addPatterns("Syntax");
	}
	
    // adds the given resource file to this language's recognized types
    public void addPatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(resourcesPath + syntax);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            myCommandSymbols.put(key, regex);
            mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
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
    
    // this part added on my own
    public String getNameInLang(String command) { 
    	String possibilities = myCommandSymbols.get(command);
    	int cut = possibilities.indexOf("|");
    	if (cut < 0) {
    		return possibilities;
    	} else { 
    		return possibilities.substring(0, cut);
    	}
    }

    // returns true if the given text matches the given regular expression pattern
    private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

}

