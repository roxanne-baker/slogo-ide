import java.util.*;

import controller.Controller;
import factory.ControllerFactory;
import factory.ViewFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import model.Interpreter;
import view.View;
import view.ConsoleView;
import view.HistoryView;

public class Workspace {
	private String[] STANDARD_VIEWS = {"Preferences","Agent","History","Console","Variables","Methods"};
	private String[] STANDARD_CONTROLLERS = {"Agent","Variables","Methods"};
	private HashMap<String,View> viewMap = new HashMap<String,View>();
	private HashMap<String,Controller> controllerMap = new HashMap<String,Controller>();
	GridPane root = new GridPane();
	
	public Scene init(){
		initViews();
		initControllers();
		Interpreter ip = new Interpreter(controllerMap);
		((ConsoleView) viewMap.get("Console")).setInterpreter(ip);
		((HistoryView) viewMap.get("History")).setInterpreter(ip);
		return new Scene(root);
	}
	
	private void initViews(){
		ViewFactory viewFactory = new ViewFactory();
		for(String type: STANDARD_VIEWS){
			View view = viewFactory.createView(type);
			int x = 0;
			int y = 0;
			switch(type){
			case "Agent":
				break;
			case "Console":
				y = 1;
				break;
			case "History":
				x = 1;
				break;
			case "Methods":
				x = 2;
				break;
			case "Variables":
				x = 2;
				y = 1;
				break;
			case "Preferences":
				x = 1;
				y = 1;
			}
			viewMap.put(type,view);
			root.add(view.getView(), x, y);
		}
	}
	
	private void initControllers(){
		ControllerFactory controllerFactory = new ControllerFactory(viewMap);
		for(String type: STANDARD_CONTROLLERS){
			Controller controller = controllerFactory.createController(type);
			controllerMap.put(type, controller);
		}
	}


}