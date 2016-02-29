package view;

import java.util.*;
import controller.TurtleController;
import controller.VariableController;
import model.Interpreter;
import model.VariableModel;

public class ViewFactory {
//	private static HashMap<String,View> allViews = new HashMap<String,View>();
	private HistoryView historyView; 
	private VariableView variableView; 
	private ConsoleView consoleView; 
	private MethodView methodView; 
	private ViewAgents agentsView; 
	private ViewPreferences preferencesView;
	
	// 
	TurtleController tc = new TurtleController(); 
	VariableModel vm = new VariableModel();
	//VariableView varView = new VariableView("0");
	VariableController vc = new VariableController(vm);
	Interpreter ip = new Interpreter(tc, vc);
	
	public View createView(String ID){
		switch(ID){
		case "Console":
			consoleView = new ConsoleView(ID,historyView);
			consoleView.setInterpreter(ip);
			return consoleView;
		case "History":
			historyView = new HistoryView(ID);
			return historyView;
		case "SavedVar":
			variableView = new VariableView(ID);
			vc.setVariableView(variableView);
			return variableView;
		case "SavedMethod":

			methodView = new MethodView(ID);
			return methodView;
		case "Agent":
			agentsView = new ViewAgents(ID);
			tc.setView(agentsView);
			tc.addAgent("Melissa");
			tc.setCurrentAgent("Melissa");
			return agentsView;
		case "Preferences":
			preferencesView = new ViewPreferences(ID);

		default:
			return null;
		}
		//allViews.put(ID, view);
		//return view;
	}

}
