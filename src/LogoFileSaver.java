import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import commands.CreatedMethod;
import controller.Controller;
import controller.MethodsController;
import controller.ControllerVariables;
import javafx.stage.Stage;
import parsing.Parser;
import view.ViewType;

public class LogoFileSaver extends Saver{
	private ControllerVariables variableController; 
	private MethodsController methodController; 
	private Parser parser; 
	PrintWriter writer; 

	private static final String EXTENSION = "*.logo";
	private static final String EXTENSION_DESCRIPTION = "Logo File";
	
	LogoFileSaver (Stage stage, HashMap<ViewType, Controller> controllerMap, Parser parser) { 
		super(stage, EXTENSION_DESCRIPTION, EXTENSION);
		variableController = (ControllerVariables) controllerMap.get(ViewType.VARIABLES);
		methodController = (MethodsController) controllerMap.get(ViewType.METHODS); 
		this.parser = parser;
	}
	
	public void saveFile(String filePath) { 
		try {
			writer = new PrintWriter(filePath);
			StringBuffer sb = new StringBuffer();
			scrapeVars(sb);
			scrapeMethods(sb);
			writer.print(sb.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldn't find file path to save");
		}
	}
	
	public void scrapeVars(StringBuffer sb) { 
		HashMap<String, Object> varMap = variableController.getVariablesModel();
		for (String key: varMap.keySet()) { 
			sb.append(parser.getNameInLang("MakeVariable") + " " + key + " " + varMap.get(key));
			sb.append("\n");
		}
	}
	
	public void scrapeMethods(StringBuffer sb) { 
		Map<String, CreatedMethod> methodMap = methodController.getMethodsModel();
		for (String key: methodMap.keySet()) { 
			String name = key.split(" ")[0];
			String paramNames = key.substring(name.length()+1);
			sb.append(parser.getNameInLang("MakeUserInstruction") + " " + name 
											+ " [ " + paramNames + " ] " + "[ \n" + methodMap.get(key).getMethodCommands() + "\n ]");
			sb.append("\n");
		}
	}

}
