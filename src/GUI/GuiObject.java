package GUI;

import java.util.Observable;
import java.util.ResourceBundle;

public abstract class GuiObject {
	private String objectName;
	private Boolean objectVisibility;
	private ResourceBundle myResources;
	private Observable observable;
	public GuiObject(String name, String resourceBundle,Observable obs){
		this.objectName = name;
		this.myResources = ResourceBundle.getBundle(resourceBundle);
		this.observable = obs;

	}
	
	public String getObjectName(){
		return objectName;
	}
	
	public Object isVisible(){
		return objectVisibility;
	}

	public ResourceBundle getResourceBundle(){
		return myResources;
	}

	public Observable getObservable(){
		return observable;
	}
	
	public abstract Object createObjectAndReturnObject();
		

	
}