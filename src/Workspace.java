import java.util.*;

import controller.Controller;
import factory.ControllerFactory;
import factory.ModelFactory;
import factory.ViewFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Interpreter;
import model.Model;
import view.View;
import view.ViewType;
import view.ConsoleView;
import view.HistoryView;
import view.VariablesView;
import view.ViewWindowPreferences;

public class Workspace implements Observer {
	private static final int COORD0 = 0;
	private static final int COORD1 = (View.WIDE_WIDTH)+5;
	private static final int COORD2 = (View.WIDE_WIDTH+View.NARROW_WIDTH)+5;
	
	private ViewType[] STANDARD_MODELS = {ViewType.VARIABLES,ViewType.METHODS};
	private ViewType[] STANDARD_VIEWS = {ViewType.PREFERENCES,ViewType.AGENT,ViewType.HISTORY,ViewType.CONSOLE,ViewType.VARIABLES,ViewType.METHODS, ViewType.WINDOWPREFERENCES};
	private ViewType[] STANDARD_CONTROLLERS = {ViewType.AGENT,ViewType.VARIABLES,ViewType.METHODS};
	private HashMap<ViewType,Model> modelMap = new HashMap<ViewType,Model>();
	private HashMap<ViewType,View> viewMap = new HashMap<ViewType,View>();
	private HashMap<ViewType,Controller> controllerMap = new HashMap<ViewType,Controller>();
	private Group group = new Group();
	private ScrollPane pane = new ScrollPane(group);
	private Stage myStage;
	
	public Workspace(Stage stage){
		myStage = stage;
		//pane.setLayoutY();
	}
	
	public Scene init(){
		initModels();
		initViews();
		initControllers();
		initInterpreters();
		Scene myScene = new Scene(pane);
		myScene.getStylesheets().add("resources/style/style.css");
		return myScene;
	}
	
	private void initWindowMenu(){
		CheckBox menu = new CheckBox();
	}
	
	private void initInterpreters() {
		Interpreter ip = new Interpreter(controllerMap);
		((ConsoleView) viewMap.get(ViewType.CONSOLE)).setInterpreter(ip);
		((HistoryView) viewMap.get(ViewType.HISTORY)).setInterpreter(ip);
		((ViewWindowPreferences) viewMap.get(ViewType.WINDOWPREFERENCES)).setInterpreter(ip);
	}

	private void initModels(){
		ModelFactory modelFactory = new ModelFactory();
		for(ViewType type: STANDARD_MODELS){
			Model model = modelFactory.createModel(type);
			modelMap.put(type,model);
		}
	}
	
	private void initViews(){
		ViewFactory viewFactory = new ViewFactory();
		for(ViewType type: STANDARD_VIEWS){
			View view = viewFactory.createView(type);
			if(type==ViewType.VARIABLES){
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
	
	private int[] getViewCoords(ViewType type){
		int[] coords = new int[2];
		switch(type){
		case AGENT:
			break;
		case CONSOLE:
			coords = new int[]{COORD0,COORD1};
			break;
		case HISTORY:
			coords = new int[]{COORD1,COORD0};
			break;
		case METHODS:
			coords = new int[]{COORD2,COORD0};
			break;
		case VARIABLES:
			coords = new int[]{COORD2,COORD1};
			break;
		case PREFERENCES:
			coords = new int[]{COORD0,COORD2};
			break;
		case WINDOWPREFERENCES:
			coords = new int[]{COORD1,COORD1};
			break;
		}
		return coords;
	}
	
	private void initControllers(){
		ControllerFactory controllerFactory = new ControllerFactory(modelMap,viewMap);
		for(ViewType type: STANDARD_CONTROLLERS){
			Controller controller = controllerFactory.createController(type);
			controllerMap.put(type, controller);
		}
	}
	
	public Map<ViewType, Controller> getControllerMap() { 
		return controllerMap;
	}

	@Override
	public void update(Observable o, Object arg) {
		updateView((View)o);
	}

	private void updateView(View view) {
		group.getChildren().remove(view.getView());
		int[] coords = getViewCoords(view.getType());
		Pane viewGroup = view.getView();
		viewGroup.setTranslateX(coords[0]);
		viewGroup.setTranslateY(coords[1]);
		group.getChildren().add(viewGroup);
		
	}


}