import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.layout.VBox;

public class SavedVariableV extends Observable {
	private ArrayList<VariableV> varList = new ArrayList<VariableV>();
	private VBox savedVars = new VBox();
	
	public SavedVariableV() {
		
	}
	
	public void addVariableView(Variable variable){
		savedVars.getChildren().add(new VariableV(variable).getVariableV());
	}
	
	public void editVariableView(String name, String newValue){
		for(VariableV var: varList){
			if(var.getName()==name){
				var.setValue(newValue);
			}
		}
	}
	
	public VBox getSavedVars(){
		return savedVars;
	}
	

}
