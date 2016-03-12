package factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import controller.ControllerBackground;
import controller.Controller;
import controller.MethodsController;
import controller.ControllerTurtle;
import controller.ControllerVariables;
import model.ModelMethods;
import model.Model;
import model.ModelVariables;
import view.ViewMethods;
import view.ViewVariables;
import view.View;
import view.ViewAgents;
import view.ViewType;
import view.ViewAgentPreferences;

public class ControllerFactory {
	//private ResourceBundle classResources = ResourceBundle.getBundle("viewclasses");
	private static HashMap<ViewType,Model> allModels;
	private static HashMap<ViewType,View> allViews;
	
	public ControllerFactory(HashMap<ViewType,Model> models,HashMap<ViewType,View> views){
		allModels = models;
		allViews = views;
	}
	
	public Controller createController(ViewType ID){
		Model model = allModels.get(ID);
		View view = allViews.get(ID);
		switch(ID){
		case VARIABLES:
			return new ControllerVariables((ModelVariables)model,(ViewVariables)view);
		case METHODS:
			return new MethodsController((ModelMethods)model,(ViewMethods)view);
		case PALETTES:
			return new ControllerBackground((ViewAgentPreferences)allViews.get(ViewType.PREFERENCES), (ViewAgents)allViews.get(ViewType.AGENT));
		case AGENT:
			return new ControllerTurtle((ViewAgentPreferences)allViews.get(ViewType.PREFERENCES),(ViewAgents)view);
		}
		return null;
	}
	
//	@SuppressWarnings("unchecked")
//	public Controller createController(ViewType ID){
//		Model model = allModels.get(ID);
//		View view = allViews.get(ID);
//		String className = "controller.Controller"+classResources.getString(ID.toString());
//		try {
//			Class cls = Class.forName(className);
//			Constructor controllerConstructor = cls.getDeclaredConstructor(new Class[]{Model.class,View.class});
//			System.out.println(null==controllerConstructor);
//			return (Controller)controllerConstructor.newInstance(model,view);
//		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

}