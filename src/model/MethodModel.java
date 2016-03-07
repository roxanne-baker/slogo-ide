package model;
import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
import commands.CreatedMethod;

public class MethodModel extends Model {
	private Map<String, CreatedMethod> savedMethods;
	
	public MethodModel() {
		savedMethods = new HashMap<String, CreatedMethod>();
	}
	
	public void addMethod(String methodName, CreatedMethod method){
		savedMethods.put(methodName, method);
	}
	
	public Map<String, CreatedMethod> getMethods() {
=======
public class MethodModel extends Model {
	private Map<String, String> savedMethods;
	
	public MethodModel() {
		savedMethods = new HashMap<String, String>();
	}
	
	public void addMethod(String methodName, String methodText){
		savedMethods.put(methodName, methodText);
	}
	
	public Map<String, String> getMethods() {
>>>>>>> 19fae920878b586fe2408cfd07d7c5fa3aaf2e88
		return savedMethods;
	}

}