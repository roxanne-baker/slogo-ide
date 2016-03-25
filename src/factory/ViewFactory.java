// This entire file is part of my masterpiece
// Colette Torres
/**
 * The purpose of this code is to create different subclasses of the View class according to a specified type.
 * 
 * I think this is well designed because it shows off what I have learned of the value in design patterns-- 
 * specifically, the Factory Design Pattern.  This design pattern takes advantage of information hiding because whatever
 * is creating the views -- in our case, the Workspace -- need not concern itself with the details of how these view
 * objects are created, just that the right types of views are created. 
 * Additionally, it shows off what I've learned of reflection.  By the end of this project, I had refactored it significantly 
 * from what it used to be because at first I had relied on conditional logic and had switch cases which was really inflexible.  
 * I think this design shows that I've learned a lot about the importance in keeping parts of the program closed and instead
 * making them flexible enough so as to be extended for new features.  Thus, with the continual addition of different views in 
 * this project, it was important to not have to continually just add switch cases to the factory method.  Rather, by using reflection,
 * I allow for new view subclasses with different names to be added without having to modify the factory code.  As long as the naming
 * convention and constructor format of the newly added view class is consistent, I am able to construct it just by its name, which
 * makes this class very flexible for additions.
 */
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
			return null;
		}
	}

}
