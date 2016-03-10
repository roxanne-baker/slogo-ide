import java.util.*;

import controller.Controller;
import controller.TurtleController;
import factory.ControllerFactory;
import factory.ModelFactory;
import factory.ViewFactory;
import factory.ViewFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Interpreter;
import model.Model;
import view.CustomColor;
import view.CustomColorPalette;
import view.CustomImagePalette;
import view.Preferences;
import view.View;
import view.ViewAgents;
import view.ViewPalettes;
import view.ViewType;
import view.ViewConsole;
import view.ViewHistory;
import view.ViewVariables;
import view.ViewWindowPreferences;

public class Workspace implements Observer {
	private static final int MENU_OFFSET = 30;
	private static final int WINDOW_PREF_OFFSET = 200;
	private static final int PADDING = 5;
	private static final int COORD0 = 0;
	private static final int COORD1 = View.WIDE_WIDTH+PADDING;
	private static final int COORD2 = View.WIDE_WIDTH+View.NARROW_WIDTH+PADDING;
	
	private ViewType[] models = {ViewType.VARIABLES,ViewType.METHODS};
	private ViewType[] views = {ViewType.PREFERENCES,ViewType.AGENT,ViewType.HISTORY,ViewType.CONSOLE,ViewType.VARIABLES,ViewType.METHODS, ViewType.WINDOWPREFERENCES, ViewType.PALETTES};
	private ViewType[] controllers = {ViewType.AGENT,ViewType.VARIABLES,ViewType.METHODS};

	private HashMap<ViewType,Model> modelMap = new HashMap<ViewType,Model>();
	private HashMap<ViewType,View> viewMap = new HashMap<ViewType,View>();
	private HashMap<ViewType,Controller> controllerMap = new HashMap<ViewType,Controller>();
	private CustomColorPalette customColorPalette = new CustomColorPalette();
	private CustomImagePalette customImagePalette;// = new CustomImagePalette();
	private Group group = new Group();
	private ScrollPane pane = new ScrollPane(group);
	private Scene myScene;
	private Stage myStage;
	private ResourceBundle myResources = ResourceBundle.getBundle("windowProperties");
	private Map<String,List<Object>> savedPreferences = new HashMap<String,List<Object>>();
	private Map<String,Object> info;
	private Preferences myPreferences;
	
	public Workspace(Stage stage, Preferences preferences){
		myStage = stage;
		myPreferences = preferences;
		customImagePalette = new CustomImagePalette((List<Object>) preferences.getPreference("images"));
	}
	
	public Scene init(){
		initModels();
		initViews();
		initWindowMenu();	
		initControllers();
		initPalettes();
		initTurtles();
		initInterpreters();

		myScene = new Scene(pane);
		myScene.getStylesheets().add("resources/style/style.css");
		return myScene;
	}
	
	private void initTurtles(){
		int numTurtles = Integer.parseInt(myPreferences.getPreference("turtles").toString());
		for(int i=0; i<numTurtles; i++){
			((TurtleController)controllerMap.get(ViewType.AGENT)).addAgent("TURTLE"+Integer.toString(i));
		}
	}
	
	private void initPalettes() {
		System.out.println("setting up palettes");
		((TurtleController)controllerMap.get(ViewType.AGENT)).setColorPalette(customColorPalette);
		((TurtleController)controllerMap.get(ViewType.AGENT)).setImagePalette(customImagePalette);
		((ViewPalettes) viewMap.get(ViewType.PALETTES)).setPaletteList(Arrays.asList(customColorPalette,customImagePalette));

		
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
		((ViewConsole) viewMap.get(ViewType.CONSOLE)).setInterpreter(ip);
		((ViewHistory) viewMap.get(ViewType.HISTORY)).setInterpreter(ip);
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
			System.out.println(type);
			View view = viewFactory.createView(type, myPreferences);
			if(type==ViewType.VARIABLES){
				((ViewVariables)view).addObserver(this);
			}
			if(type==ViewType.CONSOLE){
				((ViewConsole)view).setHistoryView((ViewHistory)viewMap.get(ViewType.HISTORY));
			}
//			if(type==ViewType.AGENT){
//				((ViewAgents)view).setBackgroundColor(Color.valueOf(info.get("background").toString()));
//			}
			int[] coords = new int[]{view.getX(),view.getY()};
			viewMap.put(type,view);
			Pane viewGroup = view.getView();
			viewGroup.setLayoutX(coords[0]);
			viewGroup.setLayoutY(coords[1]);
			group.getChildren().add(viewGroup);
		}
	}
	
	private void openWorkspace(){
		Stage newStage = new Stage();
		XMLReader reader = new XMLReader();
		newStage.setScene(new Workspace(newStage,new Preferences(reader.getPreferences())).init());
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
	
//	private int[] getViewCoords(ViewType type){
//		int[] coords = new int[2];
//		switch(type){
//		case AGENT:
//			coords = new int[]{COORD0,COORD0+MENU_OFFSET};
//			break;
//		case CONSOLE:
//			coords = new int[]{COORD0,COORD1+MENU_OFFSET};
//			break;
//		case HISTORY:
//			coords = new int[]{COORD1,COORD0+MENU_OFFSET};
//			break;
//		case METHODS:
//			coords = new int[]{COORD2,COORD0+MENU_OFFSET};
//			break;
//		case VARIABLES:
//			coords = new int[]{COORD2,COORD1+MENU_OFFSET};
//			break;
//		case PREFERENCES:
//			coords = new int[]{COORD0,COORD2+MENU_OFFSET};
//			break;
//		case WINDOWPREFERENCES:
//			coords = new int[]{COORD0,COORD0};
//			break;
//		case PALETTES:
//			coords = new int[]{COORD2+View.NARROW_WIDTH,COORD0+MENU_OFFSET};
//			break;
//		}
//		return coords;
//	}
	
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
			int[] coords = new int[]{view.getX(),view.getY()};//getViewCoords(view.getType());
			viewGroup.setLayoutX(coords[0]);
			viewGroup.setLayoutY(coords[1]);
			group.getChildren().add(viewGroup);
		}
	}


}