import java.util.*;

import controller.Controller;
import factory.ControllerFactory;
import factory.ModelFactory;
import factory.ViewFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import model.Interpreter;
import model.Model;
import view.View;
import view.ConsoleView;
import view.HistoryView;
import view.VariablesView;
import view.ViewWindowPreferences;

public class Workspace implements Observer {
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
		//initGridConstraints();
		initModels();
		initViews();
		initControllers();
		Interpreter ip = new Interpreter(controllerMap);
		((ConsoleView) viewMap.get("Console")).setInterpreter(ip);
		((HistoryView) viewMap.get("History")).setInterpreter(ip);
		((ViewWindowPreferences) viewMap.get("WindowPreferences")).setInterpreter(ip);
		return new Scene(pane);
	}
	
	private void initGridConstraints(){
		ColumnConstraints col = new ColumnConstraints(View.WIDE_WIDTH,View.WIDE_WIDTH,View.WIDE_WIDTH);
		col.setHgrow(Priority.ALWAYS);
		RowConstraints row = new RowConstraints(View.WIDE_WIDTH,View.WIDE_WIDTH,View.WIDE_WIDTH);
		row.setVgrow(Priority.ALWAYS);
		root.getColumnConstraints().add(col);
		root.getRowConstraints().add(row);
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
			//root.add(view.getView(), coords[0], coords[1]);
			System.out.println(type);
			Pane viewGroup = view.getView();//
			viewGroup.setTranslateX(coords[1]);
			viewGroup.setTranslateY(coords[0]);
			group.getChildren().add(viewGroup);
		}
	}
	
	private int[] getViewCoords(String type){
		int[] coords = new int[2];
		switch(type){
		case "Agent":
			break;
		case "Console":
			//coords = new int[]{0,1};
			coords = new int[]{View.WIDE_WIDTH,0};
			break;
		case "History":
			//coords = new int[]{1,0};
			coords = new int[]{0,View.WIDE_WIDTH};
			break;
		case "Methods":
			//coords = new int[]{2,0};
			coords = new int[]{0,View.WIDE_WIDTH+View.NARROW_WIDTH};
			break;
		case "Variables":
			//coords = new int[]{2,1};
			coords = new int[]{View.WIDE_WIDTH,View.WIDE_WIDTH+View.NARROW_WIDTH};
			break;
		case "Preferences":
			//coords = new int[]{0,2};
			coords = new int[]{View.WIDE_WIDTH+View.NARROW_WIDTH,0};
			break;
		case "WindowPreferences":
			//coords = new int[]{1,1};
			coords = new int[]{View.WIDE_WIDTH,View.WIDE_WIDTH};
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
		viewGroup.setTranslateX(coords[1]);
		viewGroup.setTranslateY(coords[0]);
		group.getChildren().add(viewGroup);
		
	}


}