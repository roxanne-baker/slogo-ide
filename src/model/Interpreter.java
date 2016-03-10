package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;

import commands.XCor;
import commands.YCor;
import controller.Controller;
import controller.MethodsController;
import controller.TurtleController;
import controller.VariablesController;
import commands.ArcTangent;
import commands.Back;
import commands.ClearScreen;
import commands.Command;
import commands.Cosine;
import commands.CreatedMethod;
import commands.Difference;
import commands.Divide;
import commands.DoTimes;
import commands.Equal;
import commands.For;
import commands.Forward;
import commands.Greater;
import commands.Heading;
import commands.HideTurtle;
import commands.Home;
import commands.If;
import commands.IfElse;
import commands.Left;
import commands.Less;
import commands.Logarithm;
import commands.MakeVar;
import commands.Minus;
import commands.NotEqual;
import commands.PenDown;
import commands.PenDownQuery;
import commands.PenUp;
import commands.Pi;
import commands.Power;
import commands.Product;
import commands.RandomCommand;
import commands.Remainder;
import commands.Repeat;
import commands.Right;
import commands.SetHeading;
import commands.SetXY;
import commands.ShowTurtle;
import commands.ShowingQuery;
import commands.Sine;
import commands.Sum;
import commands.Tangent;
import commands.To;
import commands.Towards;
import commands.TurtleCommand;
import commands.TurtleQueryCommands;

public class Interpreter extends Observable {

	private Map<String, Command> commandsMap; 
	private final String WHITESPACE = "\\p{Space}";
    private Parser lang = new Parser();
    private final String resourcesPath = "resources/languages/";
	private TurtleController turtleController;
	private VariablesController variableController;
	private MethodsController methodController;
	private String errorMessage = new String();
	private double returnResult; 
	private final List<Object> NO_PARAMS_LIST = new ArrayList<Object>();
	private final char OPEN_BRACKET = '[';
	private final char CLOSED_BRACKET = ']';
	
	public Interpreter(HashMap<String,Controller> controllers) {
		turtleController = (TurtleController) controllers.get("Agent"); 
		variableController = (VariablesController) controllers.get("Variables");
		methodController = (MethodsController) controllers.get("Methods");
		initializeCommandsMap();
		initializeLangs();
	}
	
	public void addLang(String language) { 
		System.out.println(language);
		lang.addPatterns(resourcesPath + language.trim());
	}
	
	private void initializeLangs() { 
        lang.addPatterns("resources/languages/English");
        lang.addPatterns("resources/languages/Syntax");
	}
	
	public void run(String userInput) { 
		callBuildTree(userInput);
	}
    
    private void callBuildTree(String text) { 
    	if (text.trim().length() == 0) return;
    	String parsedFirst = parseText(takeFirst(text));
    	if (parsedFirst.equals("Constant")) { 
    		returnResult =  Double.parseDouble(takeFirst(text));
    		return;
    	}
    	if (errorCommandName(parsedFirst) && errorCommandName(takeFirst(text))) { 
    		sendError(String.format("%s is not a valid command", takeFirst(text)));
    		return;
    	}
    	Command c;
    	if (!errorCommandName(takeFirst(text))) {
    		c = commandsMap.get(takeFirst(text));
    	}
    	else { 
    		c = commandsMap.get(parsedFirst);
    	}
		if (c.getNumParams() == 0) { 
			returnResult = c.execute(NO_PARAMS_LIST);
			callBuildTree(cutFirst(text));
			return;
		}
    	ParseNode root = new ParseNode(c);
    	Stack<ParseNode> commandStack = new Stack<ParseNode>();
    	commandStack.push(root);
    	buildExprTree(cutFirst(text), commandStack, root);  
    }
    
    private void fillCommandStackParams(Stack<ParseNode> stack) { 
		Object result = new Object();
    	while (!stack.isEmpty()) { 
    		result = stack.peek().getValue();
    		ParseNode cur = stack.pop();
    		if (cur.allParamsHaveValue()) { 
    			result = cur.getCommand().execute(cur.extractParamsFromNode());
    			cur.setValue(result);
    		}
    	}
    	returnResult = (double) result;
    }
    
    private void combThruTree(ParseNode root, Stack<ParseNode> stack) {
    	if (!root.isCommand()) { 
    		return; 
    	}
    	stack.push(root);
    	if (!root.allParamsHaveValue()) { 
       		for (ParseNode p: root.getParams()) { 
       			combThruTree(p, stack);
       		}
    	}
    }
    
    private void processTree(ParseNode root) { 
    	Stack<ParseNode> treeStack = new Stack<ParseNode>(); 
    	combThruTree(root, treeStack);
    	fillCommandStackParams(treeStack);
    }
    
    private boolean cutStackAndString(String wholeText, String parsedFirst, Stack<ParseNode> commandStack, ParseNode root) { 
    	if (commandStack.isEmpty()) { 
    		if (!wholeText.equals("")) { 
    			if (!parsedFirst.equals("Constant")) { 
    				processTree(root);
    				if (commandsMap.containsKey(parsedFirst) || commandsMap.containsKey(takeFirst(wholeText))) {
        				callBuildTree(wholeText);
    				}
    			}
    			else if (parsedFirst.equals("Constant") || parsedFirst.equals("Variable")){ 
    				sendError("Too many parameters!");
    			}
    		} else { 
				processTree(root);
    		}
    		return true; 
    	}
    	else if (wholeText.length() == 0 && !commandStack.isEmpty()) {
    		sendError("Not enough parameters!"); 
        	return true; 
    	} 
    	return false;
    }
    
    private boolean invalidVariable(String text, String parsedFirst, Stack<ParseNode> commandStack) { 
    	if (parsedFirst.equals("Variable")) { 
    		if (commandStack.peek().getCommand().isNeedsVarName()) {
    			return false;
    		}
    		try {
    			@SuppressWarnings("unused")
				double val = Double.parseDouble((String) variableController.getVariable(takeFirst(text)));
    		} catch(Exception e) { 
        		sendError(String.format("%s is not a valid variable", takeFirst(text)));
        		return true;
    		}
    	}
    	return false;
    }
    
    private boolean invalidCommandName(String text, String parsedFirst, Stack<ParseNode> commandStack) { 
    	if (parsedFirst.equals("Command")) { 
    		if (commandStack.peek().getCommand().isNeedsVarName() || !errorCommandName(takeFirst(text))) { 
    			return false; 
    		} else if (!errorCommandName(parsedFirst)) { 
    			return false; 
    		} else { 
    			sendError(String.format("%s is not a valid command", takeFirst(text)));
    			return true;
    		}
    	} 
    	return false; 
    }
    
    private boolean invalidInput(String text, String parsedFirst) { 
    	if (parsedFirst.equals("NO MATCH")) {
    		sendError(String.format("%s is not a valid input", takeFirst(text)));
    		return true;
    	}
    	return false; 
    }
    
    private boolean stopBuild(String text, Stack<ParseNode> commandStack, ParseNode root) { 
    	String parsedFirst = parseText(takeFirst(text));
//  	if (commandStack.isEmpty()) { 
//    		if (!text.equals("")) { 
//    			if (!parsedFirst.equals("Constant") && commandsMap.containsKey(parsedFirst)) { 
//    				processTree(root);
//    				callBuildTree(text);
//    			}
//    			else if (parsedFirst.equals("Constant") || parsedFirst.equals("Variable")){ 
//    				sendError("Too many parameters!");
//    			}
//    		} else { 
//				processTree(root);
//    		}
//    		return true; 
//    	}
//    	else if (text.length() == 0 && !commandStack.isEmpty()) {
//    		sendError("Not enough parameters!"); 
//        	return true; 
//    	} 
    	return cutStackAndString(text, parsedFirst, commandStack, root) || 
    			invalidVariable(text, parsedFirst, commandStack) || 
    			invalidCommandName(text, parsedFirst, commandStack) || invalidInput(text, parsedFirst); 
//    	else if (parsedFirst.equals("Variable")) { 
//    		if (commandStack.peek().getCommand().isNeedsVarName()) {
//    			return false;
//    		}
//    		try {
//    			@SuppressWarnings("unused")
//				double val = Double.parseDouble((String) variableController.getVariable(takeFirst(text)));
//    		} catch(Exception e) { 
//        		sendError(String.format("%s is not a valid variable", takeFirst(text)));
//        		return true;
//    		}
//    	}
//    	else if (!parsedFirst.equals("Constant") && !parsedFirst.equals("ListStart")) { 
//    		if (parsedFirst.equals("Command")) { 
//    			if (commandStack.peek().getCommand().isNeedsVarName() || !errorCommandName(takeFirst(text))) { 
//    				return false; 
//    			} else { 
//    				sendError(String.format("%s is not a valid command", takeFirst(text)));
//    				return true;
//    			}
//    		} else if (!errorCommandName(parsedFirst)) { 
//    			return false; 
//    		}
//    		sendError(String.format("%s is not a valid input", takeFirst(text)));
//    		return true;
//    	} 
    }
    
    private void buildExprTree(String text, Stack<ParseNode> commandStack, ParseNode root) { 
    	if (stopBuild(text, commandStack, root)) {
    		return; 
    	}
    	String first = takeFirst(text); 
    	String parsedFirst = parseText(first);
		ParseNode cur;
    	if (parsedFirst.equals("Constant")) { 
    		cur = new ParseNode(Double.parseDouble(first));
    		attachNode(cur, commandStack);
    	} 
    	else if (parsedFirst.equals("Variable")) { 
    		makeAttachVariableNode(first, commandStack);
//    		if (commandStack.peek().getCommand().isNeedsVarName() && commandStack.peek().getNumParamsFilled() == 0) {
//    			cur = new ParseNode(first);
//    		}
//    		else { 
//    			cur = new ParseNode(Double.parseDouble((String) variableController.getVariable(first)));
//    		}
//    		attachNode(cur, commandStack);
   		} 
    	else if (parsedFirst.equals("ListStart")) { 
    		cur = new ParseNode(takeList(text));
    		attachNode(cur, commandStack);
    		buildExprTree(cutList(text), commandStack, root);
    		return;
    	}
    	else { 
    		makeAttachCommandStringNode(first, parsedFirst, commandStack);
//    		if (commandStack.peek().getCommand().isNeedsVarName() && commandStack.peek().getNumParamsFilled() == 0) { 
//    			cur = new ParseNode(first);
//    			attachNode(cur, commandStack);
//    		} else { 
//    			if (commandsMap.containsKey(first)) { 
//    				cur = new ParseNode(commandsMap.get(first));
//    			} else { 
//            		cur = new ParseNode(commandsMap.get(parsedFirst));
//    			}
//        		attachNode(cur, commandStack);
//        		commandStack.push(cur);
//    		}
    	} 
		buildExprTree(cutFirst(text), commandStack, root); 
    }
    
    private void makeAttachVariableNode(String first, Stack<ParseNode> commandStack) { 
    	ParseNode cur;
    	if (commandStack.peek().getCommand().isNeedsVarName() && commandStack.peek().getNumParamsFilled() == 0) {
			cur = new ParseNode(first);
		}
		else { 
			cur = new ParseNode(Double.parseDouble((String) variableController.getVariable(first)));
		}
		attachNode(cur, commandStack);
    }
    
    private void makeAttachCommandStringNode(String first, String parsedFirst, Stack<ParseNode> commandStack) { 
    	ParseNode cur;
		if (commandStack.peek().getCommand().isNeedsVarName() && commandStack.peek().getNumParamsFilled() == 0) { 
			cur = new ParseNode(first);
			attachNode(cur, commandStack);
		} else { 
			if (commandsMap.containsKey(first)) { 
				cur = new ParseNode(commandsMap.get(first));
			} else { 
        		cur = new ParseNode(commandsMap.get(parsedFirst));
			}
			// may want to check for turtlequery/turtle command here
        	if (cur.getCommand().getNumParams() == 0) { 
        		cur.setValue(cur.getCommand().execute(NO_PARAMS_LIST));
        		attachNode(cur, commandStack);
        		return;
        	}
    		attachNode(cur, commandStack);
    		commandStack.push(cur);
		}
    }
    
    private void attachNode(ParseNode cur, Stack<ParseNode> commandStack) {
    	ParseNode mostRecentCommand = commandStack.peek();
		mostRecentCommand.getParams().add(cur); 
		if (mostRecentCommand.paramsFilled()) { 
			commandStack.pop();
		}
    }
    
    private String takeList(String s) { 
    	return s.substring(1, endParenIndex(s)).trim();
    }
    
    private String cutList(String s) { 
    	return s.substring(endParenIndex(s)+1).trim();
    }
    
    private String takeFirst(String text) { 
    	String[] split = text.split(WHITESPACE);
    	return split[0].trim();
    }
    
    private String cutFirst(String text) { 
    	String first = text.split(WHITESPACE)[0];
    	return text.substring(first.length()).trim(); 
    }
    
    private int endParenIndex(String s) {
    	int lastClosed = 0;
    	//int lastOpen = 0; 
    	int openCount = 0;
    	int closedCount = 0;
    	for (int i=0; i<s.length();i++) {
    		// maybe  && i < s.indexOf(']')
    		if (s.charAt(i) == OPEN_BRACKET) { 
    			openCount++;
    			//lastOpen = i; 
    		} else if (s.charAt(i) == CLOSED_BRACKET) { 
    			lastClosed = i; 
    			closedCount++; 
    			String between = s.substring(0, lastClosed);
    			if ((between.indexOf(OPEN_BRACKET) == -1 && between.indexOf(CLOSED_BRACKET) == -1) ||
    					openCount == closedCount) { 
    				break;
    			}
    		}
    	}
//    	for (int i=0; i<s.length();i++) { 
//    		if (s.charAt(i) == ']') { 
//    			lastClosed = i; 
//    			closedCount++;
//    			if (closedCount == openCount) { 
//    				break;
//    			}
//    		}
//    	}
    	return lastClosed; 
    }
	
	private boolean errorCommandName(String input) {
		if (!commandsMap.containsKey(input)) { 
			return true;
		}
		return false;  
	}
	
	public String getErrorMessage() { 
		return errorMessage;
	}
	
	public double getReturnResult() { 
		return returnResult;
	}
	
	private void sendError(String message) { 
		errorMessage = message;
		setChanged();
		notifyObservers("ERROR");
	}

//    private static String readFileToString (String filename) throws FileNotFoundException {
//        final String END_OF_FILE = "\\z";
//        Scanner input = new Scanner(new File(filename));
//        input.useDelimiter(END_OF_FILE);
//        String result = input.next();
//        input.close();
//        return result;
//    }
    
    private String parseText(String s) {
    	return lang.getSymbol(s);
    }
    
    private void initializeCommandsMap() { 
		commandsMap = new HashMap<String, Command>(); 
		addTurtleCommands();
		addTurtleQueries();
		addMathOps();
		addBooleanOps();
		addControlStructureCommands();
		commandsMap.put("MakeVariable", new MakeVar(variableController));
	}
	
    private void addControlStructureCommands() { 
    	commandsMap.put("Repeat", new Repeat(this));
    	commandsMap.put("If", new If(this));
    	commandsMap.put("IfElse", new IfElse(this));
    	commandsMap.put("For", new For(this, variableController));
    	commandsMap.put("DoTimes", new DoTimes(this));
    	commandsMap.put("MakeUserInstruction", new To(this, variableController, methodController));
    }
    
	private void addTurtleCommands() {
		commandsMap.put("Forward", new Forward(turtleController));
		commandsMap.put("Back", new Back(turtleController));
		commandsMap.put("Left", new Left(turtleController));
		commandsMap.put("Right", new Right(turtleController));
		commandsMap.put("SetHeading", new SetHeading(turtleController));
		commandsMap.put("SetTowards", new Towards(turtleController));
		commandsMap.put("SetPosition", new SetXY(turtleController));
		commandsMap.put("PenDown", new PenDown(turtleController));
		commandsMap.put("PenUp", new PenUp(turtleController));
		commandsMap.put("ShowTurtle", new ShowTurtle(turtleController));
		commandsMap.put("HideTurtle", new HideTurtle(turtleController));
		commandsMap.put("Home", new Home(turtleController));
		commandsMap.put("ClearScreen", new ClearScreen(turtleController));
	}
	
	private void addTurtleQueries() {
		commandsMap.put("XCoordinate", new XCor(turtleController));
		commandsMap.put("YCoordinate", new YCor(turtleController));		
		commandsMap.put("Heading", new Heading(turtleController));
		commandsMap.put("IsPenDown", new PenDownQuery(turtleController));
		commandsMap.put("IsShowing", new ShowingQuery(turtleController));
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
    
    public void addCommandToMap(CreatedMethod method) { 
    	commandsMap.put(method.getMethodName(), method);
    }
}