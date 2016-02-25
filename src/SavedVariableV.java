import java.util.HashMap;
import javafx.scene.layout.VBox;

public class SavedVariableV {
	private HashMap<String,VariableV> varMap = new HashMap<String,VariableV>();
	private VBox savedVars = new VBox();
	
	public SavedVariableV() {
		
	}
	
	public void addVariableView(String name, String value){
		VariableV varView = new VariableV(name,value);
		if(varMap.containsKey(name)){
			editVariableView(name,value);
		}
		else{
			savedVars.getChildren().add(varView.getVariableV());
		}
		varMap.put(name, varView);
	}
	
	public void editVariableView(String name, String newValue){
		varMap.get(name).setValue(newValue);
	}
	
	public VBox getSavedVars(){
		return savedVars;
	}
	

}
