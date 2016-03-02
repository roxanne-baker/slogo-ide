package factory;

import java.util.*;

import controller.Controller;
import controller.MethodsController;
import controller.TurtleController;
import controller.VariablesController;
import javafx.scene.paint.Color;
import model.Interpreter;
import model.MethodModel;
import model.Model;
import model.VariableModel;
import view.ConsoleView;
import view.HistoryView;
import view.MethodsView;
import view.VariablesView;
import view.View;
import view.ViewAgents;
import view.ViewPreferences;

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
			return new VariablesController((VariableModel)model,(VariablesView)view);
		case "Methods":
			return new MethodsController((MethodModel)model,(MethodsView)view);
		case "Agent":
			return new TurtleController((ViewPreferences)allViews.get("Preferences"),(ViewAgents)view);
		}
		return null;
	}

}
