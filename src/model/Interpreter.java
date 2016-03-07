package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;

import commands.XCor;
import commands.YCor;
import controller.ColorPickerController;
import controller.Controller;
import controller.TurtleController;
import controller.VariablesController;
import commands.ArcTangent;
import commands.Back;
import commands.Command;
import commands.Cosine;
import commands.Difference;
import commands.Divide;
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
import commands.SetBackground;
import commands.SetHeading;
import commands.SetPalette;
import commands.SetPenColor;
import commands.SetXY;
import commands.ShowTurtle;
import commands.ShowingQuery;
import commands.Sine;
import commands.Sum;
import commands.Tangent;
import commands.Towards;

public class Interpreter extends Observable {

	protected Map<String, Command> commandsMap; 
	private final String WHITESPACE = "\\p{Space}";
    private Parser lang = new Parser();
    private final String resourcesPath = "resources/languages/";
	private TurtleController turtleController;
	private VariablesController variableController;
	private ColorPickerController colorPickerController;
	private String errorMessage = new String();
	private double returnResult; 
	
	public Interpreter(HashMap<String,Controller> controllers) {
		turtleController = (TurtleController) controllers.get("Agent"); 
		variableController = (VariablesController) controllers.get("Variables");
		colorPickerController = (ColorPickerController) controllers.get("ColorPicker");
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
		initializeLangs();
		initializeCommandsMap();
		callBuildTree(userInput);
	}
    
    private void callBuildTree(String text) { 
    	String parsedFirst = parseText(takeFirst(text));
    	if (errorCommandName(parsedFirst)) { 
    		sendError(String.format("%s is not a valid command", takeFirst(text)));
    		return;
    	}
    	Command c = commandsMap.get(parsedFirst);
    	ParseNode root = new ParseNode(c);
    	Stack<ParseNode> commandStack = new Stack<ParseNode>();
    	commandStack.push(root);
    	buildExprTree(cutFirst(text), commandStack, root);  
//    	Stack<ParseNode> treeStack = new Stack<ParseNode>(); 
//    	combThruTree(root, treeStack);
//    	Object ans = fillCommandStackParams(treeStack);
    	//System.out.println(ans);
    	// if repeat / control sequence 
    	// do something else 
    }
    
    private void fillCommandStackParams(Stack<ParseNode> stack) { 
		Object result = new Object();
    	while (!stack.isEmpty()) { 
    		result = stack.peek().getValue();
    		ParseNode cur = stack.pop();
    		if (cur.allParamsHaveValue()) { 
    			List<Object> params = cur.extractParamsFromNode();
    			String error = cur.getCommand().checkParamTypes(params);
    			if (error != null) {
    				sendError(error);
    				return;
    			}
    			else {
    				result = cur.getCommand().execute(cur.extractParamsFromNode());
    				cur.setValue(result);
    			}
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
    
    private String takeFirst(String text) { 
    	String[] split = text.split(WHITESPACE);
    	return split[0];
    }
    
    private void processTree(ParseNode root) { 
    	Stack<ParseNode> treeStack = new Stack<ParseNode>(); 
    	combThruTree(root, treeStack);
    	fillCommandStackParams(treeStack);
    }
    
    private boolean stopBuild(String text, Stack<ParseNode> commandStack, ParseNode root) { 
    	String parsedFirst = parseText(takeFirst(text));
    	if (commandStack.isEmpty()) { 
    		if (!text.equals("")) { 
    			if (!parsedFirst.equals("Constant") && commandsMap.containsKey(parsedFirst)) { 
    				processTree(root);
    				callBuildTree(text);
    			}
    			else if (parsedFirst.equals("Constant") || parsedFirst.equals("Variable")){ 
    				sendError("Too many parameters!");
    			}
    		} else { 
				processTree(root);
    		}
    		return true; 
    	}
    	else if (text.length() == 0 && !commandStack.isEmpty()) {
    		sendError("Not enough parameters!"); 
        	return true; 
    	} 
    	else if (parsedFirst.equals("Variable")) { 
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
    	else if (!parsedFirst.equals("Constant") && !parsedFirst.equals("ListStart") && errorCommandName(parsedFirst)) { 
    		sendError(String.format("%s is not a valid command", takeFirst(text)));
    		return true;
    	}
    	return false;
    }
    
    private void buildExprTree(String text, Stack<ParseNode> commandStack, ParseNode root) { 
    	System.out.println(text);
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
    		if (commandStack.peek().getCommand().isNeedsVarName() && commandStack.peek().getNumParamsFilled() == 0) {
    			cur = new ParseNode(first);
    		}
    		else { 
    			cur = new ParseNode(Double.parseDouble((String) variableController.getVariable(first)));
    		}
    		attachNode(cur, commandStack);
   		} 
    	else if (parsedFirst.equals("ListStart")) { 
    		System.out.println(takeList(text));
    		cur = new ParseNode(takeList(text));
    		attachNode(cur, commandStack);
    		buildExprTree(cutList(text), commandStack, root);
    		return;
    	}
    	else { 
    		cur = new ParseNode(commandsMap.get(parsedFirst));
    		attachNode(cur, commandStack);
    		commandStack.push(cur);
    	} 
		buildExprTree(cutFirst(text), commandStack, root); 
    }
    
    private void attachNode(ParseNode cur, Stack<ParseNode> commandStack) {
    	ParseNode mostRecentCommand = commandStack.peek();
		mostRecentCommand.getParams().add(cur); 
		if (mostRecentCommand.paramsFilled()) { 
			commandStack.pop();
		}
    }
    
//    private String cutList(String s) { 
//    	String reversed = new StringBuilder(s).reverse().toString();
//    	int tempIndex = reversed.indexOf("]");
//    	int endIndex = s.length() - tempIndex - 1; 
//    	System.out.println("blah blah" + s.substring(endIndex + 1).trim());
//    	return s.substring(endIndex + 1).trim();
//    }
    
    private String takeList(String s) { 
    	return s.substring(1, endParenIndex(s)).trim();
    }
    
    private String cutList(String s) { 
    	return s.substring(endParenIndex(s)+1).trim();
    }
    private int endParenIndex(String s) {
    	//int endIndex = s.length() - new StringBuilder(s).reverse().toString().indexOf("]") - 1;
    	int lastClosed = 0;
    	int openCount = 0;
    	int closedCount = 0;
    	for (int i=0; i<s.length();i++) { 
    		if (s.charAt(i) == '[' && i < s.indexOf(']')) { 
    			openCount++;
    		}
    	}
    	for (int i=0; i<s.length();i++) { 
    		if (s.charAt(i) == ']') { 
    			lastClosed = i; 
    			closedCount++;
    			if (closedCount == openCount) { 
    				break;
    			}
    		}
    	}
    	return lastClosed; 
    }
//    	
//    	
//    	int cutOffIndex = 0; 
//    	while(s.substring(cutOffIndex).indexOf(beginParens) > -1 && s.substring(cutOffIndex).indexOf(beginParens) < s.indexOf(endParens)) { 
//    		bracketStack.push(beginParens);
//    		cutOffIndex = s.substring(cutOffIndex).indexOf(beginParens) + 1;
//        	System.out.println(cutOffIndex);
//    	}
//    	System.out.println(cutOffIndex);
//    	System.out.println("[s in stack " + bracketStack.size());
//    	while (copy.length() > 0 && copy.indexOf(endParens) > -1 && !bracketStack.isEmpty()) { 
//    		bracketStack.pop();
//    		if (bracketStack.isEmpty()) { 
//    			endIndex = s.indexOf(endParens);
//    		}
//    		copy = copy.substring(copy.indexOf(endParens) + 1);
//    	}
    	//String reversed = new StringBuilder(s).reverse().toString();
    	//int tempIndex = reversed.indexOf("]");
    	//int endIndex = s.length() - tempIndex - 1; 
    	//System.out.println("endindex = " + endIndex);
    	//return s.substring(1, endIndex+1).trim();
   // }
    
//    private void buildExprTree(String text, Stack<ParseNode> commandStack) { 
//    	if (stopBuild(text, commandStack)) return; 
//    	String first = takeFirst(text); 
//    	String parsedFirst = parseText(first);
//    	ParseNode mostRecentCommand = commandStack.peek();
//    	if (parsedFirst.equals("Constant")) { 
//    		ParseNode cur = new ParseNode(Double.parseDouble(first));
//    		mostRecentCommand.getParams().add(cur); 
//    		if (mostRecentCommand.paramsFilled()) { 
//    			commandStack.pop();
//    		}
//    		buildExprTree(cutFirst(text), commandStack); 
//    	} 
//    	else if (parsedFirst.equals("Variable")) { 
//    		ParseNode cur;
//    		if (commandStack.peek().getCommand().isNeedsVarName() && commandStack.peek().getNumParamsFilled() == 0) {
//    			cur = new ParseNode(first);
//    		}
//    		else { 
//    			cur = new ParseNode(Double.parseDouble((String) variableController.getVariable(first)));
//    		}
//        	mostRecentCommand.getParams().add(cur); 
//       		if (mostRecentCommand.paramsFilled()) { 
//       			commandStack.pop();
//       		}
//       		buildExprTree(cutFirst(text), commandStack); 
//   		} 
//    	else { 
//    		ParseNode cur = new ParseNode(commandsMap.get(parsedFirst));
//    		mostRecentCommand.getParams().add(cur); 
//    		if (mostRecentCommand.paramsFilled()) { 
//    			commandStack.pop();
//    		} 
//    		commandStack.push(cur);
//    		buildExprTree(cutFirst(text), commandStack); 
//    	} 
//    }
	
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
		commandsMap.put("SetPalette", new SetPalette(colorPickerController));
		commandsMap.put("SetBackground", new SetBackground(colorPickerController));
		commandsMap.put("SetPenColor", new SetPenColor(turtleController));
	}
	
    private void addControlStructureCommands() { 
    	commandsMap.put("Repeat", new Repeat(this));
    	commandsMap.put("If", new If(this));
    	commandsMap.put("IfElse", new IfElse(this));
    	commandsMap.put("For", new For(this, variableController));
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
    
    private String cutFirst(String text) { 
    	String first = text.split(WHITESPACE)[0];
    	return text.substring(first.length()).trim(); 
    }
}