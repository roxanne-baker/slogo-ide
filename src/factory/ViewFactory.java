package factory;

import java.util.*;
import controller.TurtleController;
import controller.VariablesController;
import javafx.scene.paint.Color;
import model.Interpreter;
import model.VariableModel;
import view.ConsoleView;
import view.HistoryView;
import view.MethodsView;
import view.VariablesView;
import view.View;
import view.ViewAgents;
import view.ViewPreferences;

public class ViewFactory {
//	private static HashMap<String,View> allViews = new HashMap<String,View>();
	private HistoryView historyView; 
	private VariablesView variableView; 
	private ConsoleView consoleView; 
	private MethodsView methodView; 
	private ViewAgents agentsView; 
	private ViewPreferences preferencesView;
	
	public View createView(String ID){
		switch(ID){
		case "Console":
			consoleView = new ConsoleView(ID,historyView);
			return consoleView;
		case "History":
			historyView = new HistoryView(ID);
			return historyView;
		case "Variables":
			variableView = new VariablesView(ID);
			return variableView;
		case "Methods":
			methodView = new MethodsView(ID);
			return methodView;
		case "Agent":
			agentsView = new ViewAgents(ID);
			return agentsView;
		case "Preferences":
			preferencesView = new ViewPreferences(ID);
			return preferencesView;
		default:
			return null;
		}
		//allViews.put(ID, view);
		//return view;
	}

}
