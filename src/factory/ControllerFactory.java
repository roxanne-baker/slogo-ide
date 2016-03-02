package factory;

import java.util.*;

import controller.Controller;
import controller.MethodController;
import controller.TurtleController;
import controller.VariableController;
import javafx.scene.paint.Color;
import model.Interpreter;
import model.MethodModel;
import model.Model;
import model.VariableModel;
import view.ConsoleView;
import view.HistoryView;
import view.MethodView;
import view.VariableView;
import view.View;
import view.ViewAgents;
import view.ViewAgentPreferences;

public class ControllerFactory {
	private static HashMap<String,Model> allModels;
	private static HashMap<String,View> allViews;
	
	public ControllerFactory(HashMap<String,Model> models,HashMap<String,View> views){
		allModels = models;
		allViews = views;
	}
	
	public Controller createController(String ID){
		Model model = allModels.get(ID);
		View view = allViews.get(ID);
		switch(ID){
		case "Variables":
			return new VariableController((VariableModel)model,(VariableView)view);
		case "Methods":
			return new MethodController((MethodModel)model,(MethodView)view);
		case "Agent":
			return new TurtleController((ViewAgentPreferences)allViews.get("Preferences"),(ViewAgents)view);
		}
		return null;
	}

}
