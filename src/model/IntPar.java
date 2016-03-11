package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import commands.XCor;
import commands.YCor;
import controller.TurtleController;
import controller.VariablesController;
import commands.ArcTangent;
import commands.Back;
import commands.Command;
import commands.Cosine;
import commands.Difference;
import commands.Divide;
import commands.Equal;
import commands.Forward;
import commands.Greater;
import commands.Heading;
import commands.HideTurtle;
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
import commands.Right;
import commands.SetHeading;
import commands.SetXY;
import commands.ShowTurtle;
import commands.ShowingQuery;
import commands.Sine;
import commands.Sum;
import commands.Tangent;
import commands.Towards;

public class IntPar {

	protected static Map<String, Command> commandsMap; 
	private static final String WHITESPACE = "\\p{Space}";
    private static Parser lang = new Parser();


	private static TurtleController turtleController;
	private static VariablesController variableController;
	
	public IntPar() {
//		turtleController = tc; 
//		variableController = vc;
	}
	
	public void changeLang() { 
		
	}
	
	private static void initializeLangs() { 
        lang.addPatterns("resources/languages/English");
        lang.addPatterns("resources/languages/Syntax");
	}
	
	public void run(String userInput) { 
		initializeLangs();
		initializeCommandsMap();
		callBuildTree(userInput);
	}
    
    private static void callBuildTree(String text) { 
    	Command c = commandsMap.get(parseText(takeFirst(text)));
    	ParseNode root = new ParseNode(c);
    	Stack<ParseNode> commandStack = new Stack<ParseNode>();
    	commandStack.push(root);
    	// if no repeat or control variable
    	buildExprTree(cutFirst(text), commandStack);  
    	Stack<ParseNode> treeStack = new Stack<ParseNode>(); 
    	combThruTree(root, treeStack);
    	Object ans = fillCommandStackParams(treeStack);
    	//System.out.println(ans);
    	// if repeat / control sequence 
    	// do something else 
    }
    
    private static Object fillCommandStackParams(Stack<ParseNode> stack) { 
		Object result = new Object();
    	while (!stack.isEmpty()) { 
    		result = stack.peek().getValue();
    		ParseNode cur = stack.pop();
    		if (cur.allParamsHaveValue()) { 
    			result = cur.getCommand().execute(cur.extractParamsFromNode());
    			cur.setValue(result);
    		}
    	}
    	System.out.println(result);
    	return result;
    }
    
    private static void combThruTree(ParseNode root, Stack<ParseNode> stack) {
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
    	return split[0];
    }
    
    private static boolean stopBuild(String text, Stack<ParseNode> commandStack) { 
    	String parsedFirst = parseText(takeFirst(text));
    	if (commandStack.isEmpty()) { 
    		if (!text.equals("")) { 
    			if (!parsedFirst.equals("Constant") && commandsMap.containsKey(parsedFirst)) { 
    				callBuildTree(text);
    			}
    			else { 
    			// throw error to console view
    				System.out.println("Too many params");
    			}
    		}
    		return true; 
    	}
    	else if (text.length() == 0 && !commandStack.isEmpty()) {
    		// throw error to console view
    		System.out.println("Not enough params"); 
        	return true; 
    	} 
    	else if (parsedFirst.equals("Variable")) { 
    		try {
    			Object val = variableController.getVariable(takeFirst(text).substring(1));
    		} catch(Exception e) { 
        		System.out.println(String.format("%s is not a valid variable", takeFirst(text).substring(1)));
        		return true;
    		}
    	}
    	else if (!parsedFirst.equals("Constant") && errorCommandName(parsedFirst)) { 
    		// throw error to console view
    		return true;
    	}
    	return false;
    }
    
    private static void buildExprTree(String text, Stack<ParseNode> commandStack) { 
    	if (stopBuild(text, commandStack)) return; 
    	String first = takeFirst(text); 
    	String parsedFirst = parseText(first);
    	ParseNode mostRecentCommand = commandStack.peek();
    	if (parsedFirst.equals("Constant")) { 
    		ParseNode cur = new ParseNode(Double.parseDouble(first));
    		mostRecentCommand.getParams().add(cur); 
    		if (mostRecentCommand.paramsFilled()) { 
    			commandStack.pop();
    		}
    		buildExprTree(cutFirst(text), commandStack); 
    	} 
    	else if (parsedFirst.equals("Variable")) { 
        	ParseNode cur = new ParseNode(variableController.getVariable(first.substring(1)));
        	mostRecentCommand.getParams().add(cur); 
       		if (mostRecentCommand.paramsFilled()) { 
       			commandStack.pop();
       		}
       		buildExprTree(cutFirst(text), commandStack); 
   		} 
    	else { 
    		ParseNode cur = new ParseNode(commandsMap.get(parsedFirst));
    		mostRecentCommand.getParams().add(cur); 
    		if (mostRecentCommand.paramsFilled()) { 
    			commandStack.pop();
    		} 
    		commandStack.push(cur);
    		buildExprTree(cutFirst(text), commandStack); 
    	} 
    }
	
	private static boolean errorCommandName(String input) {
		if (!commandsMap.containsKey(input)) { 
			return true;
		}
		return false;  
	}

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
    
    private static void initializeCommandsMap() { 
		commandsMap = new HashMap<String, Command>(); 
		addTurtleCommands();
		addTurtleQueries();
		addMathOps();
		addBooleanOps();
		commandsMap.put("MakeVariable", new MakeVar(variableController));
	}
	
	private static void addTurtleCommands() {
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
	}
	
	private static void addTurtleQueries() {
		commandsMap.put("XCoordinate", new XCor(turtleController));
		commandsMap.put("YCoordinate", new YCor(turtleController));		
		commandsMap.put("Heading", new Heading(turtleController));
		commandsMap.put("IsPenDown", new PenDownQuery(turtleController));
		commandsMap.put("IsShowing", new ShowingQuery(turtleController));
	}
	
	private static void addMathOps() {
		commandsMap.put("Sum", new Sum());
		commandsMap.put("Difference", new Difference());
		commandsMap.put("Product", new Product());
		commandsMap.put("Quotient", new Divide());
		commandsMap.put("Remainder", new Remainder());
		commandsMap.put("Minus", new Minus());
		commandsMap.put("Sine", new Sine());
		commandsMap.put("Cosine", new Cosine());
		commandsMap.put("Tangent", new Tangent());
		commandsMap.put("ArcTangent", new ArcTangent());
		commandsMap.put("NaturalLog", new Logarithm());
		commandsMap.put("Power", new Power());
		commandsMap.put("Pi", new Pi());
	}
	
	private static void addBooleanOps() {
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
        String ui = "fd sum / 4 less? 2 4 3";
        String ui3 = "* / 4 sin 30 18 + 3 5";
        String ui1 = "sin 30.0";
        String ui4 = "- 3 5";
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        String userInput2 = "fd 50 rt 90 BACK 40 Left :angle";
        String userInput3 = "fd + 10 div 6 2";
        IntPar ip = new IntPar(); 
        ip.run(ui3);
    }
}