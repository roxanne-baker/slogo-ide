import java.util.*;

import controller.Controller;
import factory.ControllerFactory;
import factory.ModelFactory;
import factory.ViewFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Interpreter;
import model.Model;
import view.View;
import view.ConsoleView;
import view.HistoryView;
import view.VariablesView;
import view.ViewWindowPreferences;

public class Workspace implements Observer {
	private static final int COORD0 = 0;
	private static final int COORD1 = (View.WIDE_WIDTH)+5;
	private static final int COORD2 = (View.WIDE_WIDTH+View.NARROW_WIDTH)+5;
	
	private String[] STANDARD_MODELS = {"Variables","Methods"};
	private String[] STANDARD_VIEWS = {"Preferences","Agent","History","Console","Variables","Methods", "WindowPreferences"};
	private String[] STANDARD_CONTROLLERS = {"Agent","Variables","Methods"};
	private HashMap<String,Model> modelMap = new HashMap<String,Model>();
	private HashMap<String,View> viewMap = new HashMap<String,View>();
	private HashMap<String,Controller> controllerMap = new HashMap<String,Controller>();
	GridPane root = new GridPane();
	Group group = new Group();
	ScrollPane pane = new ScrollPane(group);
	
	public Scene init(){
		initModels();
		initViews();
		initControllers();
		Interpreter ip = new Interpreter(controllerMap);
		((ConsoleView) viewMap.get("Console")).setInterpreter(ip);
		((HistoryView) viewMap.get("History")).setInterpreter(ip);
		((ViewWindowPreferences) viewMap.get("WindowPreferences")).setInterpreter(ip);
		return new Scene(pane);
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
			Pane viewGroup = view.getView();
			viewGroup.setTranslateX(coords[0]);
			viewGroup.setTranslateY(coords[1]);
			group.getChildren().add(viewGroup);
		}
	}
	
	private int[] getViewCoords(String type){
		int[] coords = new int[2];
		switch(type){
		case "Agent":
			break;
		case "Console":
			coords = new int[]{COORD0,COORD1};
			break;
		case "History":
			coords = new int[]{COORD1,COORD0};
			break;
		case "Methods":
			coords = new int[]{COORD2,COORD0};
			break;
		case "Variables":
			coords = new int[]{COORD2,COORD1};
			break;
		case "Preferences":
			coords = new int[]{COORD0,COORD2};
			break;
		case "WindowPreferences":
			coords = new int[]{COORD1,COORD1};
			break;
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
		group.getChildren().remove(view.getView());
		int[] coords = getViewCoords(view.getID());
		Pane viewGroup = view.getView();
		viewGroup.setTranslateX(coords[0]);
		viewGroup.setTranslateY(coords[1]);
		group.getChildren().add(viewGroup);
		
	}


}