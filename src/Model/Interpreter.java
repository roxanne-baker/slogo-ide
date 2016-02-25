package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Interpreter {

	protected static Map<String, Command> commandsMap; 
	private static final String WHITESPACE = "\\p{Space}";
    private static Parser lang = new Parser();
	
	public Interpreter() {
		initializeCommandsMap();
	}
	
	public void run(String userInput) { 
		//parseText(lang, userInput); 
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
	}
	
//	private void addCommandtoMap(Command c) { 
//		for (String name: c.getNames()) { 
//			commandsMap.put(name, c); 
//		}
//	}
//	
//    private static String readFileToString (String filename) throws FileNotFoundException {
//        final String END_OF_FILE = "\\z";
//        Scanner input = new Scanner(new File(filename));
//        input.useDelimiter(END_OF_FILE);
//        String result = input.next();
//        input.close();
//        return result;
//    }
    
    private static void parseText (Parser lang, String text) {
    	String first = text.split(WHITESPACE)[0];
    	
    	if (!lang.getSymbol(first).equals("COMMAND")) { 
    		System.out.println(text);
    	}
    	Command command = commandsMap.get(lang.getSymbol(first));
//    	root.params = new ArrayList<ParseNode>(); 
//    	int index = 1; 
//    	for (int i=0; i < command.getNumParams();i++) { 
//    		String param = text.split(WHITESPACE)[index];
//    		if (commandsMap.get(param).equals("CONSTANT")) { 
//        		root.params.add(new ParseNode(Double.valueOf(param))); 	
//    		}
//    		else {
//    			//parseText(index); 
//    		}
//    		index++; 
//    	}
//        for (String s : split) {
//            if (s.trim().length() > 0) {
//                System.out.println(String.format("%s : %s", s, lang.getSymbol(s)));
//            }
//        }
        System.out.println();
    }
    
    private void callRecurse(String text) { 
    	Stack<ParseNode> commandStack = new Stack<ParseNode>();
    	commandStack.push(new ParseNode(commandsMap.get(text.split(WHITESPACE)[0])));
    	recurse(cutFirst(text), commandStack); 
    }
    
    private void recurse(String text, Stack<ParseNode> commandStack) { 
    	String[] split = text.split(WHITESPACE);
    	String first = lang.getSymbol(split[0]); 
    	ParseNode mostRecentCommand = commandStack.peek();
    	if (text.length() == 0) {
    		if (!mostRecentCommand.paramsFilled()) { 
    			System.out.println("Not enough params"); 
    		} 
    		return; 
    	}
    	if (lang.getSymbol(first).equals("Constant")) { 
    		ParseNode cur = new ParseNode(Double.parseDouble(first));
    		mostRecentCommand.getParams().add(cur); 
    		if (mostRecentCommand.paramsFilled()) { 
    			commandStack.pop();
    		}
    		recurse(cutFirst(text), commandStack); 
    	} 
    	else if (lang.getSymbol(first).equals("Command") && commandsMap.containsKey(first)) { 
    		ParseNode cur = new ParseNode(commandsMap.get(first));
    		mostRecentCommand.getParams().add(cur); 
    		if (mostRecentCommand.paramsFilled()) { 
    			commandStack.pop();
    		} 
    		commandStack.push(cur);
    		recurse(cutFirst(text), commandStack); 
    	}
    }
    
    private static String cutFirst(String text) { 
    	String first = text.split(WHITESPACE)[0];
    	return text.substring(first.length()); 
    }
    
    public static void main(String[] args) { 
//        String[] examples = {
//            "",
//            "# foo",
//            "foo #",
//            "#",
//            "fd",
//            "FD",
//            "forwardd",
//            "equalp",
//            "equal?",
//            "equal??",
//            "+",
//            "SuM",
//            "-",
//            "*",
//            "/",
//            "%",
//            "~",
//            "+not",
//            "not+",
//            "++",
//            "+*+",
//            "or",
//            "FOR",
//            "allOrNothing",
//            "all_or_nothing",
//            "allOr_nothing?",
//            "allOr?nothing_",
//            ":allornothing",
//            "PI",
//            "90",
//            "9.09",
//            "9.0.0",
//            "[",
//            "]",
//            "(",
//            ")", 
//            "fd (sum 10 3)"
//        };
        lang.addPatterns("resources/languages/English");
        lang.addPatterns("resources/languages/Syntax");
        String ui = "sum div 4 2 4";
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        String userInput2 = "fd + 10 div 6 2";
		//String fileInput = readFileToString("square.logo");
		//parseText(lang, examples);
		parseText(lang, userInput);
		parseText(lang, userInput);
		parseText(lang, userInput2);
		//parseText(lang, fileInput.split(WHITESPACE));
    	
    }

}
