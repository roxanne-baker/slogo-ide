package view;
import java.util.*;

public class ViewFactory {
	private static HashMap<String,View> allViews = new HashMap<String,View>();
	private View history;
	
	public View createView(String ID){
		View view;
		switch(ID){
		case "Console":
			view = new ConsoleView(ID,history);
			break;
		case "History":
			view = new HistoryView(ID);
			history = view;
			break;
		case "SavedVar":
			view = new VariableView(ID);
			break;
		case "SavedMethod":
			view = new MethodView(ID);
			break;
		case "Agents":
			view = new ViewAgents(ID);
//		case "Preferences":
//			view = new ViewPreferences(ID,agentList);
		default:
			return null;
		}
		allViews.put(ID, view);
		return view;
	}

}
