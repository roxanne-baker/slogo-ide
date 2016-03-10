import java.util.*;

import controller.Controller;
import factory.ControllerFactory;
import factory.ModelFactory;
import factory.ViewFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
	private static final int MENU_OFFSET = 30;
	private static final int WINDOW_PREF_OFFSET = 200;
	private static final int PADDING = 5;
	private static final int COORD0 = 0;
	private static final int COORD1 = View.WIDE_WIDTH+PADDING;
	private static final int COORD2 = View.WIDE_WIDTH+View.NARROW_WIDTH+PADDING;
	
//<<<<<<< HEAD
//	private String[] STANDARD_MODELS = {"Variables","Methods"};
//	private String[] STANDARD_VIEWS = {"Preferences","Agent","History","Console","Variables","Methods", "WindowPreferences"};
//	private String[] STANDARD_CONTROLLERS = {"Agent","Variables","Methods","ColorPicker"};
//	private HashMap<String,Model> modelMap = new HashMap<String,Model>();
//	private HashMap<String,View> viewMap = new HashMap<String,View>();
//	private HashMap<String,Controller> controllerMap = new HashMap<String,Controller>();
//	GridPane root = new GridPane();
//	Group group = new Group();
//	ScrollPane pane = new ScrollPane(group);
//=======
	private ViewType[] models = {ViewType.VARIABLES,ViewType.METHODS};
	private ViewType[] views = {ViewType.PREFERENCES,ViewType.AGENT,ViewType.HISTORY,ViewType.CONSOLE,ViewType.VARIABLES,ViewType.METHODS, ViewType.WINDOWPREFERENCES};
	private ViewType[] controllers = {ViewType.AGENT,ViewType.VARIABLES,ViewType.METHODS,ViewType.BACKGROUND};
	private HashMap<ViewType,Model> modelMap = new HashMap<ViewType,Model>();
	private HashMap<ViewType,View> viewMap = new HashMap<ViewType,View>();
	private HashMap<ViewType,Controller> controllerMap = new HashMap<ViewType,Controller>();
	private Group group = new Group();
	private ScrollPane pane = new ScrollPane(group);
	private Scene myScene;
	private Stage myStage;
	private ResourceBundle myResources = ResourceBundle.getBundle("windowProperties");
	
	public Workspace(Stage stage){
		myStage = stage;
		//pane.setLayoutY();
	}
//>>>>>>> refs/remotes/origin/master
	
	public Scene init(){
		initModels();
		initViews();
		initWindowMenu();
		initControllers();
		initInterpreters();
		myScene = new Scene(pane);
		myScene.getStylesheets().add("resources/style/style.css");
		return myScene;
	}
	
	private void initWindowMenu(){
		HBox viewMenu = new HBox();
		Button newWorkspaceBtn = new Button(myResources.getString("NEWWORKSPACEBUTTON"));
		newWorkspaceBtn.setOnMouseClicked(e->openWorkspace());
		viewMenu.getChildren().add(newWorkspaceBtn);
		for(ViewType type: views){
			if(type!=ViewType.AGENT){
				CheckBox item = new CheckBox(type.name());
				item.setSelected(true);
				item.setOnAction(e-> toggleView(type,item.isSelected()));
				viewMenu.getChildren().add(item);	
			}
		}
		group.getChildren().addAll(viewMenu);
		viewMenu.setLayoutX(WINDOW_PREF_OFFSET);
	}
	
	private void initInterpreters() {
		Interpreter ip = new Interpreter(controllerMap);
		((ConsoleView) viewMap.get(ViewType.CONSOLE)).setInterpreter(ip);
		((HistoryView) viewMap.get(ViewType.HISTORY)).setInterpreter(ip);
		((ViewWindowPreferences) viewMap.get(ViewType.WINDOWPREFERENCES)).setInterpreter(ip);
	}

	private void initModels(){
		ModelFactory modelFactory = new ModelFactory();
		for(ViewType type: models){
			Model model = modelFactory.createModel(type);
			modelMap.put(type,model);
		}
	}
	
	private void initViews(){
		ViewFactory viewFactory = new ViewFactory();
		for(ViewType type: views){
			View view = viewFactory.createView(type);
			if(type==ViewType.VARIABLES){
				((VariablesView)view).addObserver(this);
			}
			int[] coords = getViewCoords(type);
			viewMap.put(type,view);
			Pane viewGroup = view.getView();
			viewGroup.setLayoutX(coords[0]);
			viewGroup.setLayoutY(coords[1]);
			group.getChildren().add(viewGroup);
		}
	}
	
	private void openWorkspace(){
		Stage newStage = new Stage();
		newStage.setScene(new Workspace(newStage).init());
		newStage.show();
	}
	
	private void toggleView(ViewType view, boolean isSelected){
		if(isSelected){
			displayView(viewMap.get(view));
		}
		else{
			closeView(viewMap.get(view));
		}
	}
	
	private int[] getViewCoords(ViewType type){
		int[] coords = new int[2];
		switch(type){
		case AGENT:
			coords = new int[]{COORD0,COORD0+MENU_OFFSET};
			break;
//<<<<<<< HEAD
//		case "BackgroundPreferences":
//			break;
//		case "Console":
//			coords = new int[]{COORD0,COORD1};
//=======
		case CONSOLE:
			coords = new int[]{COORD0,COORD1+MENU_OFFSET};
//>>>>>>> refs/remotes/origin/master
			break;
		case HISTORY:
			coords = new int[]{COORD1,COORD0+MENU_OFFSET};
			break;
		case METHODS:
			coords = new int[]{COORD2,COORD0+MENU_OFFSET};
			break;
		case VARIABLES:
			coords = new int[]{COORD2,COORD1+MENU_OFFSET};
			break;
		case PREFERENCES:
			coords = new int[]{COORD0,COORD2+MENU_OFFSET};
			break;
		case WINDOWPREFERENCES:
			coords = new int[]{COORD0,COORD0};
			break;
		}
		return coords;
	}
	
	private void initControllers(){
		ControllerFactory controllerFactory = new ControllerFactory(modelMap,viewMap);
		for(ViewType type: controllers){
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
		closeView(view);
		displayView(view);
		
	}
	
	private void closeView(View view){
		Pane viewGroup = view.getView();
		if(group.getChildren().contains(viewGroup)){
			group.getChildren().remove(viewGroup);
		}
	}
	
	private void displayView(View view){
		Pane viewGroup = view.getView();
		if(!group.getChildren().contains(viewGroup)){
			int[] coords = getViewCoords(view.getType());
			viewGroup.setLayoutX(coords[0]);
			viewGroup.setLayoutY(coords[1]);
			group.getChildren().add(viewGroup);
		}
	}


}