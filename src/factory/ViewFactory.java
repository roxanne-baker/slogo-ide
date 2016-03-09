package factory;

import view.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ViewFactory {
	private Map<String,List<Object>> savedPreferences;
	private ResourceBundle classResources = ResourceBundle.getBundle("viewclasses");
	
	public ViewFactory(Map<String,List<Object>> savedPreferences){
		this.savedPreferences = savedPreferences;
	}
	

	public View createView(ViewType ID){
		String className = "view."+classResources.getString(ID.toString());
		try {
			Class cls = Class.forName(className);
			Constructor viewConstructor = cls.getDeclaredConstructor(new Class[]{ViewType.class, Map.class});
			return (View)viewConstructor.newInstance(ID,savedPreferences);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
