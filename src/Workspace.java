import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import controller.Controller;
import controller.ControllerTurtle;
import controller.ControllerBackground;
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
import model.Model;
import Parsing.Interpreter;
import Parsing.Parser;
import view.CustomColorPalette;
import view.CustomImagePalette;
import view.Preferences;
import view.View;
import view.ViewPalettes;
import view.ViewType;
import view.ViewConsole;
import view.ViewHistory;
import view.ViewInterpretable;
import view.ViewVariables;
import view.ViewWindowPreferences;

public class Workspace implements Observer {
	private static final String STYLE_SHEET = "resources/style/style.css";
	private static final int WORKSPACE_INIT_WIDTH = View.NARROW_WIDTH*3+View.WIDE_WIDTH;
	private static final String FILECHOOSER_FILTER = "SLOGO";
	private static final List<String> FILTERLIST = Arrays.asList("*.logo");
	private static final List<String> MENU_OPTIONS = Arrays.asList("NEWWORKSPACE","SAVEPREFERENCES","LOADPREFERENCES","SAVECOMMANDS","LOADCOMMANDS");
	
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
	private Parser parser = new Parser();
	private Interpreter myInterpreter;
	private ResourceBundle windowResources = ResourceBundle.getBundle("windowProperties");
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
		initControllers();
		initInterpreters();
		initPalettes();
		initTurtles();
		initWindowMenu();	
		myScene = new Scene(pane);
		myScene.getStylesheets().add(STYLE_SHEET);
		return myScene;
	}
	
	private void initTurtles(){
		int numTurtles = Integer.parseInt(myPreferences.getPreference("turtles").toString());
		for(int i=0; i<numTurtles; i++){
			((ControllerTurtle)controllerMap.get(ViewType.AGENT)).addAgent(i+1);
		}
	}
	
	private void initPalettes() {
		((ControllerTurtle)controllerMap.get(ViewType.AGENT)).setColorPalette(customColorPalette);
		((ControllerBackground)controllerMap.get(ViewType.PALETTES)).setColorPalette(customColorPalette);	
		((ControllerTurtle)controllerMap.get(ViewType.AGENT)).setImagePalette(customImagePalette);
		((ViewPalettes) viewMap.get(ViewType.PALETTES)).setPaletteList(Arrays.asList(customColorPalette,customImagePalette));
	}
	
	private void initWindowMenu(){
		MenuBar menu = new MenuBar();
		Menu menuFile = initFileMenu();
		Menu menuView = initViewMenu();
		menu.getMenus().addAll(menuFile,menuView);
		menu.setPrefWidth(WORKSPACE_INIT_WIDTH);
		group.getChildren().add(menu);
	}

	private Menu initViewMenu() {
		Menu menuView = new Menu(windowResources.getString("MENUVIEW"));
		for(ViewType type: views){
			if(type!=ViewType.AGENT){
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
		}
		return menuView;
	}

	private Menu initFileMenu() {
		Menu menuFile = new Menu(windowResources.getString("MENUFILE"));
		for(String option: MENU_OPTIONS){
			String[] resourceString = windowResources.getString(option).split(",");
			MenuItem item = new MenuItem(resourceString[0]);
			item.setOnAction(e->{
				try {
					getClass().getDeclaredMethod(resourceString[1].trim()).invoke(this);
				} catch (Exception e1) {
				}
			});
			menuFile.getItems().add(item);
		}
		return menuFile;
	}
	
	private void saveCommands() { 
		LogoFileSaver logoWriter = new LogoFileSaver(myStage, controllerMap, parser);
		String filePath = logoWriter.chooseFile();
		logoWriter.saveFile(filePath);
	}
	
	private void loadCommands() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(FILECHOOSER_FILTER, FILTERLIST);
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(myStage);
		myInterpreter.run(readText(file));
	}
	

	private static String readText (File file) {
		StringBuilder sb = new StringBuilder();
		try { 
		    Scanner scan = new Scanner(file);
		    while(scan.hasNextLine()){
		        String line = scan.nextLine();
		        if (!line.contains("#")) {
			        sb.append(line);
		        }
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
		PreferencesSaver prefSaver = new PreferencesSaver(myStage,myPreferences);
		String filePath = prefSaver.chooseFile();
		prefSaver.saveFile(filePath);
	}
	
	private void initInterpreters() {
		Interpreter ip = new Interpreter(controllerMap, parser);
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