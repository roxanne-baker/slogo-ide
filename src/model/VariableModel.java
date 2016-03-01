package model;
import java.util.HashMap;
import java.util.Observable;
//import java.util.Observer;

public class VariableModel extends Model {
	private HashMap<String,Object> variableMap = new HashMap<String,Object>();
	
	public VariableModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void addVariable(String name, Object value){
		variableMap.put(name,value);
	}
	
	public Object getVariable(String name) { 
		if (variableMap.containsKey(name)) { 
			return variableMap.get(name);
		}
		else{
			return null; 
		}
	}

}