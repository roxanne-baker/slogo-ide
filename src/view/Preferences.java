package view;
import java.util.*;
public class Preferences {
	private Map<String,Object> preferences;
	
	public Preferences(Map<String,Object> preferences) {
		this.preferences = preferences;
	}
	
	public void setPreference(String prefName, Object prefValue){
		preferences.put(prefName, prefValue);
	}
	
	public Object getPreference(String prefName){
		return preferences.get(prefName);
	}
	
	

}
