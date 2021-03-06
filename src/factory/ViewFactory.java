package factory;

import view.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ViewFactory {
	private ResourceBundle classResources = ResourceBundle.getBundle("viewclasses");
	

	public View createView(ViewType ID, Preferences preferences){
		String className = "view.View"+classResources.getString(ID.toString());
		try {
			Class cls = Class.forName(className);
			Constructor viewConstructor = cls.getDeclaredConstructor(new Class[]{ViewType.class, Preferences.class});
			return (View)viewConstructor.newInstance(ID,preferences);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("no such view found");
		}
		return null;
	}

}
