package factory;

import view.ConsoleView;
import view.HistoryView;
import view.MethodsView;
import view.VariablesView;
import view.View;
import view.ViewAgents;
import view.ViewAgentPreferences;
import view.ViewWindowPreferences;

public class ViewFactory {
	private HistoryView historyView; 
	private VariablesView variableView; 
	private ConsoleView consoleView; 
	private MethodsView methodView; 
	private ViewAgents agentsView; 
	private ViewAgentPreferences preferencesView;
	private ViewWindowPreferences windowPreferencesView;
	
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
			preferencesView = new ViewAgentPreferences(ID);
			return preferencesView;
		case "WindowPreferences":
			windowPreferencesView = new ViewWindowPreferences(ID);
			return windowPreferencesView;
		default:
			return null;
		}
	}

}
