import java.util.*;

public class ViewFactory {
	private static HashMap<String,View> allViews = new HashMap<String,View>();
	
	public static View createProduct(String viewType, String ID, int height, int width){
		View view;
		switch(viewType){
		case "Console":
			view = new ConsoleView(ID,height,width, allViews);
			break;
		case "History":
			view = new HistoryView(ID,height,width, allViews);
			break;
		case "Saved":
			view = new SavedView(ID,height,width, allViews);
			break;
		default:
			return null;
		}
		allViews.put(ID, view);
		return view;
	}

}
