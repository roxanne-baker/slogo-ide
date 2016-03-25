// This entire file is part of my masterpiece.
// Carolyn Yao

// This depeneds on changing the class names to exactly what they are referenced as in the parser symbols resource file. 
// Can't run this with the original right now because of class name issues. 
// I use reflection in this class to fill in the Commands Map, since we have a lot of code in the Interpreter class
// basically duplicating instantiations. 
// There's quite a few if statements but I think it is better that the code exists here than in the Interpreter class. 
// Also gives us some room for extension because it is easy to give a command access to a controller for an extension. 
// For this to work, we also want to no longer have command constructors that are specific to which controllers they'd like to
// be passed in for construction. 

// this reduces duplicate code and when we expect an influx of new commands to be made, we can simply add to the arrays. 
// we can even outsource the data in the arrays to resource files. 

package parsing;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import commands.And;
import commands.ArcTangent;
import commands.Ask;
import commands.AskWith;
import commands.Back;
import commands.ClearStamps;
import commands.Command;
import commands.Cosine;
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
import commands.Minus;
import commands.Not;
import commands.NotEqual;
import commands.NumTurtles;
import commands.Or;
import commands.PenColorQuery;
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
import commands.SetPenSize;
import commands.SetShape;
import commands.SetXY;
import commands.ShowTurtle;
import commands.ShowingQuery;
import commands.Sine;
import commands.Stamp;
import commands.Tangent;
import commands.Tell;
import commands.To;
import commands.Towards;
import commands.TurtleID;
import commands.XCor;
import commands.YCor;
import controller.ControllerBackground;
import controller.ControllerTurtle;
import controller.ControllerVariables;
import controller.MethodsController;

public class CommandMapInitializer {
	
	private Map<String, Command> commandsMap;
	private Interpreter interpreter;
	private ControllerTurtle turtleController;
	private ControllerVariables variableController;
	private ControllerBackground backgroundController;
	private MethodsController methodController;
	
	private final String[] needTurtleController = {"SetPenColor", "SetPenSize", "SetShape", "Stamp", "Tell", "ID", 
			"Ask", "Turtles", "AskWith", "Forward", "Backward", "Left", "Right", "SetHeading", "SetTowards", "SetPosition",
			"PenDown", "PenUp", "ShowTurtle", "HideTurtle", "Home", "XCoordinate", "YCoordinate", "Heading", "IsPenDown", 
			"IsShowing", "GetPenColor", "Random", "ClearScreen", "If", "IfElse"};
	private final String[] needNoController = {"Difference", "Product", "Quotient", "Remainder", "Minus", "Random", 
			"Sine", "Cosine", "Tangent", "ArcTangent", "NaturalLog", "Power", "Pi", "LessThan", "GreaterThan", 
			"Equal", "NotEqual", "And", "Or", "Not"};
	private final String[] needBackgroundController = {"ClearScreen", "SetPalette", "SetBackground", "ClearStamps"};
	private final String[] needVariableController = {"MakeVariable", "DoTimes", "DefineCommand"};
	private final String[] needMethodController = {"DefineCommand"};
	private final String[] needInterpreter = {"Repeat", "If", "IfElse", "For", "DoTimes", "To"};
	private final String[][] listCommands = {needTurtleController, needNoController, needBackgroundController, needVariableController, 
				needMethodController, needInterpreter};
	
	CommandMapInitializer(Interpreter interpreter, ControllerTurtle tc, ControllerVariables vc,
			ControllerBackground bc, MethodsController mc) { 
		this.interpreter = interpreter; 
		turtleController = tc; 
		variableController = vc; 
		backgroundController = bc;
		methodController = mc;
		commandsMap = new HashMap<String, Command>();
	}
	
	public void initializeMap() { 
		for (int i= 0; i < listCommands.length; i++) { 
			for (int j = 0; j < listCommands[i].length; j++) { 
				String name = listCommands[i][j];
				if (!commandsMap.containsKey(name)) {
					Command c = createCommand(name);
					checkIfAddController(name, c);
					commandsMap.put(name, c);
				}
			}
		}
	}
	
	// adding the proper controllers 
	// getting rid of controllers passed in via specific class constructors 
	public void checkIfAddController(String commandName, Command command) { 
		if (classNeedsController(needVariableController, commandName)) { 
			command.setVariablesController(variableController);
		}
		if (classNeedsController(needMethodController, commandName)) { 
			command.setMethodController(methodController);
		}
		if (classNeedsController(needBackgroundController, commandName)) { 
			command.setBackgroundController(backgroundController);
		}
		if (classNeedsController(needTurtleController, commandName)) { 
			command.setTurtleController(turtleController);
		}
		if (classNeedsController(needInterpreter, commandName)) { 
			command.setInterpreter(interpreter);
		}
	}
	
	public boolean classNeedsController(String[] list, String name) { 
		for (String s: list) { 
			if (s.equals(name)) { 
				return true;
			}
		}
		return false;
	}
		
	// using reflection to instantiate the classes 
	public Command createCommand(String commandName) { 
		try {  
			Class myClass = Class.forName("commands." + commandName);
			Constructor constructor = myClass.getConstructor(new Class[]{});
			Command command = (Command) constructor.newInstance();
			return command;
		}
		catch(InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("Not a valid command class");
		}
	}
	
	public void setCommandTurtleController(Command c) { 
		c.setTurtleController(turtleController);
	}
	
	public Map<String, Command> getCommandsMap() { 
		return commandsMap;
	}
}
