import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import controller.Controller;
import controller.TurtleController;
import controller.BackgroundController;
import factory.ControllerFactory;
import factory.ModelFactory;
import factory.ViewFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
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
	private static final String FILECHOOSER_FILTER = "SLOGO";
	private static final List<String> FILTERLIST = Arrays.asList("*.logo");
	
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
	private Interpreter myInterpreter;
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
	
	//TO DO: refactor
	private void initWindowMenu(){
		MenuBar menu = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem newWorkspace = new MenuItem("New Workspace");
		newWorkspace.setOnAction(e->openWorkspace());
		MenuItem loadPreferences = new MenuItem("Load Preferences");
		loadPreferences.setOnAction(e->loadPreferences());
		MenuItem savePreferences = new MenuItem("Save Preferences");
		savePreferences.setOnAction(e->savePreferences());

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(FILECHOOSER_FILTER, FILTERLIST);
		fileChooser.getExtensionFilters().add(extFilter);
		MenuItem loadCommands = new MenuItem(windowResources.getString("COMMANDSLOADERBUTTON"));
		loadCommands.setOnAction(evt -> {
			File file = fileChooser.showOpenDialog(myStage);
			myInterpreter.run(readText(file));
		});
		MenuItem saveCommands = new MenuItem(windowResources.getString("COMMANDSSAVERBUTTON"));
		saveCommands.setOnAction(e->{
			//blah
		});
		
		menuFile.getItems().addAll(newWorkspace,loadPreferences,savePreferences,loadCommands,saveCommands);
		
		Menu menuView = new Menu("View");
		for(ViewType type: views){
			CheckMenuItem view = new CheckMenuItem(type.toString());
			menuView.getItems().add(view);
			view.setSelected(true);
			view.selectedProperty().addListener(new ChangeListener<Boolean>() {
		        public void changed(ObservableValue ov,
		                Boolean old_val, Boolean new_val) {
		                    viewMap.get(type).getView().setVisible(new_val);
		                }
		            });
		}
		menu.getMenus().addAll(menuFile,menuView);
		group.getChildren().add(menu);
	}
	

	private static String readText (File file) {
		StringBuilder sb = new StringBuilder();
		try { 
		    Scanner scan = new Scanner(file);
		    while(scan.hasNextLine()){
		        String line = scan.nextLine();
		        sb.append(line);
		        sb.append("\n");
		    }
		} catch (FileNotFoundException e) { 
			System.out.println("couldn't find the file");
		}
		return sb.toString().trim();
	}
	
	
	private void loadPreferences(){
		Stage newStage = new Stage();
		XMLReader reader = new XMLReader(newStage,false);
		newStage.setScene(new Workspace(newStage,new Preferences(reader.getPreferences())).init());
		myStage.close();
		newStage.show();
	}
	private void savePreferences(){
		PreferencesSaver saver = new PreferencesSaver(myStage,myPreferences);
	}
	private void initInterpreters() {
		Interpreter ip = new Interpreter(controllerMap);
		myInterpreter = ip;
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