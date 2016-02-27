package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import Controller.TurtleController;
import Controller.VariableController;

public class Interpreter {

	protected static Map<String, Command> commandsMap; 
	private static final String WHITESPACE = "\\p{Space}";
    private static Parser lang = new Parser();
	TurtleController turtleController;
	VariableController variableController;
	
	public Interpreter() {
		initializeCommandsMap();
	}
	
	public void run(String userInput) { 
		this.callRecurse(userInput);
	}
	
    
    private void callRecurse(String text) { 
    	Stack<ParseNode> commandStack = new Stack<ParseNode>();
    	Command c = commandsMap.get(takeFirst(text));
    	ParseNode root = new ParseNode(c);
    	commandStack.push(root);
    	buildExprTree(cutFirst(text), commandStack); 
    	Stack<ParseNode> treeStack = new Stack<ParseNode>(); 
    	combThruTree(root, treeStack);
    	fillCommandStackParams(treeStack);
    }
    
    private void fillCommandStackParams(Stack<ParseNode> stack) { 
    	while (!stack.isEmpty()) { 
    		ParseNode cur = stack.pop();
    		if (cur.allParamsHaveValue()) { 
    			double result = cur.getCommand().execute(cur.extractParamsFromNode());
    			cur.setValue(result);
    		}
        	System.out.println(cur.getValue());
    	}
    }
    
    private void combThruTree(ParseNode root, Stack<ParseNode> stack) {
    	if (!root.isCommand()) { 
    		return; 
    	}
    	stack.push(root);
    	if (root.allParamsHaveValue()) { 
       		root.setValue(root.getCommand().execute(root.extractParamsFromNode()));
    	} else { 
       		for (ParseNode p: root.getParams()) { 
       			combThruTree(p, stack);
       		}
    	}
    }
    
    private static String takeFirst(String text) { 
    	String[] split = text.split(WHITESPACE);
    	String first = parseText(split[0]); 
    	return first; 
    }
    
    private static void buildExprTree(String text, Stack<ParseNode> commandStack) { 
    	if (commandStack.isEmpty()) { 
    		if (!text.equals("")) { 
    			System.out.println("Too many params");
    		}
    		return; 
    	}
    	if (text.length() == 0 && !commandStack.isEmpty()) {
    		System.out.println("Not enough params"); 
        	return; 
    	} 
    	String first = takeFirst(text); 
    	ParseNode mostRecentCommand = commandStack.peek();
    	if (first.equals("Constant")) { 
    		ParseNode cur = new ParseNode(Double.parseDouble(text.split(WHITESPACE)[0]));
    		mostRecentCommand.getParams().add(cur); 
    		if (mostRecentCommand.paramsFilled()) { 
    			commandStack.pop();
    		}
    		buildExprTree(cutFirst(text), commandStack); 
    	} 
    	else if (commandsMap.containsKey(first)) { 
    		ParseNode cur = new ParseNode(commandsMap.get(first));
    		mostRecentCommand.getParams().add(cur); 
    		if (mostRecentCommand.paramsFilled()) { 
    			commandStack.pop();
    		} 
    		commandStack.push(cur);
    		buildExprTree(cutFirst(text), commandStack); 
    	} else { 
    		System.out.println(String.format("%s is not a valid command", text.split(WHITESPACE)[0]));
    	}
    }
	
//	public boolean errorCommandName(String userInput) {
//		String commandName = getCommandName(userInput);
//		if (!commandsMap.containsKey(commandName)) { 
//			return true;
//		}
//		else return false;  
//	}
//
//	private String getCommandName(String input) { 
//		String[] commandAndParams = input.split(" "); 
//		return commandAndParams[0];
//	}
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
    
    private static String parseText(String s) {
    	return lang.getSymbol(s);
    }
    
    private void initializeCommandsMap() { 
		commandsMap = new HashMap<String, Command>(); 
		addTurtleCommands();
		addTurtleQueries();
		addMathOps();
		addBooleanOps();
		commandsMap.put("MakeVariable", new MakeVar(variableController));
	}
	
	private void addTurtleCommands() {
		commandsMap.put("Forward", new Forward(turtleController));
		commandsMap.put("Back", new Back(turtleController));
		commandsMap.put("Left", new Left(turtleController));
		commandsMap.put("Right", new Right(turtleController));
		commandsMap.put("SetHeading", new SetHeading(turtleController));
		//set towards
		commandsMap.put("PenDown", new PenDown(turtleController));
		commandsMap.put("PenUp", new PenUp(turtleController));
		commandsMap.put("ShowTurtle", new ShowTurtle(turtleController));
		commandsMap.put("HideTurtle", new HideTurtle(turtleController));
	}
	
	private void addTurtleQueries() {
		commandsMap.put("XCoordinate", new XCor(turtleController));
		commandsMap.put("YCoordinate", new YCor(turtleController));		
	}
	
	private void addMathOps() {
		commandsMap.put("Sum", new Sum());
		commandsMap.put("Difference", new Difference());
		commandsMap.put("Product", new Product());
		commandsMap.put("Quotient", new Divide());
		commandsMap.put("Remainder", new Remainder());
		commandsMap.put("Minus", new Minus());
		commandsMap.put("Random", new RandomCommand());
		commandsMap.put("Sine", new Sine());
		commandsMap.put("Cosine", new Cosine());
		commandsMap.put("Tangent", new Tangent());
		commandsMap.put("ArcTangent", new ArcTangent());
		commandsMap.put("NaturalLog", new Logarithm());
		commandsMap.put("Power", new Power());
		commandsMap.put("Pi", new Pi());
	}
	
	private void addBooleanOps() {
		commandsMap.put("LessThan", new Less());
		commandsMap.put("GreaterThan", new Greater());
		commandsMap.put("Equal", new Equal());
		commandsMap.put("NotEqual", new NotEqual());
	}
    
    private static String cutFirst(String text) { 
    	String first = text.split(WHITESPACE)[0];
    	return text.substring(first.length()).trim(); 
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
        String ui = "fd sum / 4 less? 2 4 3 ";
        String ui3 = "* / 4 sin 30 18";
        String ui1 = "sin 30.0";
        String ui4 = "- 3 5";
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        String userInput2 = "fd + 10 div 6 2";
        Interpreter it = new Interpreter(); 
    	it.run(ui3);
    }
}
