import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class SavedVariableM extends Observable {
	private HashMap<String,String> variableMap = new HashMap<String,String>();
	
	public SavedVariableM() {
		// TODO Auto-generated constructor stub
	}
	
	public void addVariable(String name,String value){
		variableMap.put(name,value);
	}
	

}
