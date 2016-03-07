package model;
import java.util.HashMap;
import java.util.Map;

public class MethodModel extends Model {
	private Map<String, String> savedMethods;
	
	public MethodModel() {
		savedMethods = new HashMap<String, String>();
	}
	
	public void addMethod(String methodName, String methodText){
		savedMethods.put(methodName, methodText);
	}
	
	public Map<String, String> getMethods() {
		return savedMethods;
	}

}