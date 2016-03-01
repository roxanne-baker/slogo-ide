package model;
import java.util.HashSet;

public class MethodModel extends Model {
	private HashSet<String> savedMethods = new HashSet<String>();
	
	public MethodModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void addMethod(String method){
		savedMethods.add(method);
	}

}