package factory;

import java.util.*;

import controller.Controller;
import controller.MethodController;
import controller.TurtleController;
import controller.VariableController;
import javafx.scene.paint.Color;
import model.Interpreter;
import model.VariableModel;
import view.ConsoleView;
import view.HistoryView;
import view.MethodView;
import view.VariableView;
import view.View;
import view.ViewAgents;
import view.ViewPreferences;

public class ControllerFactory {
	private static HashMap<String,View> allViews;
	
	public ControllerFactory(HashMap<String,View> views){
		allViews = views;
	}
	
	public Controller createController(String ID){
		View view = allViews.get(ID);
		switch(ID){
		case "Variables":
			return new VariableController((VariableView)view);
		case "Methods":
			return new MethodController((MethodView)view);
		case "Agent":
			return new TurtleController((ViewPreferences)allViews.get("Preferences"),(ViewAgents)view);
		}
		return null;
	}

}
