package model;
import java.util.HashMap;
import java.util.Map;
import commands.CreatedMethod;

public class MethodModel extends Model {
	private Map<String, CreatedMethod> savedMethods;
	
	public MethodModel() {
		savedMethods = new HashMap<String, CreatedMethod>();
	}
	
	public void addMethod(String methodNameParams, CreatedMethod method){
		savedMethods.put(methodNameParams, method);
	}
	
	public Map<String, CreatedMethod> getMethods() {
		return savedMethods;
	}

}