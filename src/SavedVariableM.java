import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class SavedVariableM extends Observable {
	private HashMap<String,String> variableMap = new HashMap<String,String>();
	
	public SavedVariableM() {
		
	}
	
	public void addVariable(Variable variable){
		variableMap.put(variable.getName(),variable.getValue());
	}
	
	public void modifyVariable(String name,String newValue){
		variableMap.put(name, newValue);
	}
	

}
