package factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import model.ModelMethods;
import model.Model;
import model.ModelVariables;
import view.Preferences;
import view.View;
import view.ViewType;

public class ModelFactory {
	private ResourceBundle classResources = ResourceBundle.getBundle("viewclasses");
	
	public Model createModel(ViewType ID){
		String className = "model.Model"+classResources.getString(ID.toString());
		try {
			Class cls = Class.forName(className);
			Constructor modelConstructor = cls.getDeclaredConstructor();
			return (Model)modelConstructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}