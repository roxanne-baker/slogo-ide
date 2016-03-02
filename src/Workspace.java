import java.util.*;

import controller.Controller;
import factory.ControllerFactory;
import factory.ModelFactory;
import factory.ViewFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import model.Interpreter;
import model.Model;
import view.View;
import view.ConsoleView;
import view.HistoryView;
import view.VariablesView;

public class Workspace implements Observer {
	private String[] STANDARD_MODELS = {"Variables","Methods"};
	private String[] STANDARD_VIEWS = {"Preferences","Agent","History","Console","Variables","Methods"};
	private String[] STANDARD_CONTROLLERS = {"Agent","Variables","Methods"};
	private HashMap<String,Model> modelMap = new HashMap<String,Model>();
	private HashMap<String,View> viewMap = new HashMap<String,View>();
	private HashMap<String,Controller> controllerMap = new HashMap<String,Controller>();
	GridPane root = new GridPane();
	
	public Scene init(){
		initModels();
		initViews();
		initControllers();
		Interpreter ip = new Interpreter(controllerMap);
		((ConsoleView) viewMap.get("Console")).setInterpreter(ip);
		((HistoryView) viewMap.get("History")).setInterpreter(ip);
		return new Scene(root);
	}
	
	private void initModels(){
		ModelFactory modelFactory = new ModelFactory();
		for(String type: STANDARD_MODELS){
			Model model = modelFactory.createModel(type);
			modelMap.put(type,model);
		}
	}
	
	private void initViews(){
		ViewFactory viewFactory = new ViewFactory();
		for(String type: STANDARD_VIEWS){
			View view = viewFactory.createView(type);
			if(type=="Variables"){
				((VariablesView)view).addObserver(this);
			}
			int[] coords = getViewCoords(type);
			viewMap.put(type,view);
			root.add(view.getView(), coords[0], coords[1]);
		}
	}
	
	private int[] getViewCoords(String type){
		int[] coords = new int[2];
		switch(type){
		case "Agent":
			break;
		case "Console":
			coords = new int[]{0,1};
			break;
		case "History":
			coords = new int[]{1,0};
			break;
		case "Methods":
			coords = new int[]{2,0};
			break;
		case "Variables":
			coords = new int[]{2,1};
			break;
		case "Preferences":
			coords = new int[]{1,1};
		}
		return coords;
	}
	
	private void initControllers(){
		ControllerFactory controllerFactory = new ControllerFactory(modelMap,viewMap);
		for(String type: STANDARD_CONTROLLERS){
			Controller controller = controllerFactory.createController(type);
			controllerMap.put(type, controller);
		}
	}
	
	public Map<String, Controller> getControllerMap() { 
		return controllerMap;
	}

	@Override
	public void update(Observable o, Object arg) {
		updateView((View)o);
	}

	private void updateView(View view) {
		root.getChildren().remove(view.getView());
		int[] coords = getViewCoords(view.getID());
		root.add(view.getView(),coords[0],coords[1]);
	}


}