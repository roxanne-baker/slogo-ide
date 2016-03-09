package factory;

import view.*;
import java.util.*;

public class ViewFactory {
	private HistoryView historyView; 
	private VariablesView variableView; 
	private ConsoleView consoleView; 
	private MethodsView methodView; 
	private ViewAgents agentsView; 
	private ViewAgentPreferences preferencesView;
	private ViewWindowPreferences windowPreferencesView;
	private ViewPalettes palettesView;
	private Map<String,List<Object>> savedPreferences;
	
	public ViewFactory(Map<String,List<Object>> savedPreferences){
		this.savedPreferences = savedPreferences;
	}
	
	public View createView(ViewType ID){
		switch(ID){
		case CONSOLE:
			consoleView = new ConsoleView(ID,historyView,savedPreferences);
			return consoleView;
		case HISTORY:
			historyView = new HistoryView(ID);
			return historyView;
		case VARIABLES:
			variableView = new VariablesView(ID);
			return variableView;
		case METHODS:
			methodView = new MethodsView(ID);
			return methodView;
		case AGENT:
			agentsView = new ViewAgents(ID);
			return agentsView;
		case PREFERENCES:
			preferencesView = new ViewAgentPreferences(ID);
			return preferencesView;
		case WINDOWPREFERENCES:
			windowPreferencesView = new ViewWindowPreferences(ID);
			return windowPreferencesView;
		case PALETTES:
			palettesView = new ViewPalettes(ID);
			return palettesView;
		default:
			return null;
		}
	}

}
