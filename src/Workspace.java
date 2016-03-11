import java.util.*;

import controller.Controller;
import controller.TurtleController;
import controller.BackgroundController;
import factory.ControllerFactory;
import factory.ModelFactory;
import factory.ViewFactory;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Interpreter;
import model.Model;
import view.CustomColorPalette;
import view.CustomImagePalette;
import view.Preferences;
import view.View;
import view.ViewPalettes;
import view.ViewType;
import view.ViewConsole;
import view.ViewHistory;
import view.ViewVariables;
import view.ViewWindowPreferences;

public class Workspace implements Observer {
	
	private ViewType[] models = {ViewType.VARIABLES,ViewType.METHODS};
	private ViewType[] views = ViewType.values();
	private ViewType[] controllers = {ViewType.AGENT,ViewType.VARIABLES,ViewType.METHODS,ViewType.PALETTES};

	private HashMap<ViewType,Model> modelMap = new HashMap<ViewType,Model>();
	private HashMap<ViewType,View> viewMap = new HashMap<ViewType,View>();
	private HashMap<ViewType, Controller> controllerMap = new HashMap<ViewType,Controller>();
	private CustomColorPalette customColorPalette;
	private CustomImagePalette customImagePalette;
	private Group group = new Group();
	private ScrollPane pane = new ScrollPane(group);
	private Scene myScene;
	private Stage myStage;
	private ResourceBundle windowResources = ResourceBundle.getBundle("windowProperties");
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	private Preferences myPreferences;
	
	public Workspace(Stage stage, Preferences preferences){
		myStage = stage;
		myPreferences = preferences;
		customColorPalette = new CustomColorPalette((ObservableList<Object>) preferences.getPreference("colors"));
		customImagePalette = new CustomImagePalette((ObservableList<Object>) preferences.getPreference("images"));
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
			((TurtleController)controllerMap.get(ViewType.AGENT)).addAgent(i+1);
		}
	}
	
	private void initPalettes() {
		((TurtleController)controllerMap.get(ViewType.AGENT)).setColorPalette(customColorPalette);
		((BackgroundController)controllerMap.get(ViewType.PALETTES)).setColorPalette(customColorPalette);	
		((TurtleController)controllerMap.get(ViewType.AGENT)).setImagePalette(customImagePalette);
		((ViewPalettes) viewMap.get(ViewType.PALETTES)).setPaletteList(Arrays.asList(customColorPalette,customImagePalette));

		
	}

	private void initWindowMenu(){
		HBox viewMenu = new HBox();
		viewMenu.getStyleClass().add(cssResources.getString("WORKSPACEMENU"));
		
		Button newWorkspaceBtn = new Button(windowResources.getString("NEWWORKSPACEBUTTON"));
		newWorkspaceBtn.setOnMouseClicked(e->openWorkspace());
		
		Button savePrefBtn = new Button(windowResources.getString("SAVEPREFBUTTON"));
		savePrefBtn.setOnMouseClicked(e->savePreferences());
		
		Button loadPrefBtn = new Button(windowResources.getString("LOADPREFBUTTON"));
		loadPrefBtn.setOnMouseClicked(e->loadPreferences());
		
		viewMenu.getChildren().addAll(newWorkspaceBtn,savePrefBtn,loadPrefBtn);
		
		
		for(ViewType type: views){
			if(type!=ViewType.AGENT){
				CheckBox item = new CheckBox(type.name());
				item.setSelected(true);
				item.setOnAction(e-> toggleView(type,item.isSelected()));
				viewMenu.getChildren().add(item);	
			}
		}
		group.getChildren().addAll(viewMenu);
	}
	private void loadPreferences(){
		Stage newStage = new Stage();
		XMLReader reader = new XMLReader(newStage,false);
		newStage.setScene(new Workspace(newStage,new Preferences(reader.getPreferences())).init());
		myStage.close();
		newStage.show();
	}
	private void savePreferences(){
		XMLSaver saver = new XMLSaver(myStage,myPreferences);
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
			View view = viewFactory.createView(type, myPreferences);
			if(type==ViewType.VARIABLES){
				((ViewVariables)view).addObserver(this);
			}
			if(type==ViewType.CONSOLE){
				((ViewConsole)view).setHistoryView((ViewHistory)viewMap.get(ViewType.HISTORY));
			}
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
		XMLReader reader = new XMLReader(newStage,true);
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
			int[] coords = new int[]{view.getX(),view.getY()};
			viewGroup.setLayoutX(coords[0]);
			viewGroup.setLayoutY(coords[1]);
			group.getChildren().add(viewGroup);
		}
	}


}