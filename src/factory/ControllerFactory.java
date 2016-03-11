package factory;

import java.util.*;

import controller.BackgroundController;
import controller.Controller;
import controller.MethodsController;
import controller.TurtleController;
import controller.VariablesController;
import model.Interpreter;
import model.MethodModel;
import model.Model;
import model.VariableModel;
import view.ViewMethods;
import view.ViewVariables;
import view.View;
import view.ViewAgents;
import view.ViewType;
import view.ViewAgentPreferences;

public class ControllerFactory {
	private static HashMap<ViewType,Model> allModels;
	private static HashMap<ViewType,View> allViews;
	
	public ControllerFactory(HashMap<ViewType,Model> models,HashMap<ViewType,View> views){
		allModels = models;
		System.out.println(models);
		allViews = views;
	}
	
	public Controller createController(ViewType ID){
		Model model = allModels.get(ID);
		View view = allViews.get(ID);
		switch(ID){
		case VARIABLES:
			return new VariablesController((VariableModel)model,(ViewVariables)view);
		case METHODS:
			return new MethodsController((MethodModel)model,(ViewMethods)view);
		case PALETTES:
			return new BackgroundController((ViewAgentPreferences)allViews.get(ViewType.PREFERENCES), (ViewAgents)allViews.get(ViewType.AGENT));
		case AGENT:
			return new TurtleController((ViewAgentPreferences)allViews.get(ViewType.PREFERENCES),(ViewAgents)view);
		}
		return null;
	}

}