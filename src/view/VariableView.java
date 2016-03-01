package view;
import java.util.HashMap;
import java.util.HashSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VariableView extends View{
	private HashMap<String,VariableElem> varMap = new HashMap<String,VariableElem>();
	private VBox savedVars = new VBox();
	
	public VariableView(String id) {
		super(id);
	}
	
	
	public void addVariableView(VariableElem varView){
		String name = varView.getName();
		StringProperty value = new SimpleStringProperty(varView.getValue());
		if(varMap.containsKey(name)){
			System.out.println(" exists so modifying value to: "+value);
			varMap.get(name).setValue(varView.getValue());
		}
		else{
			savedVars.getChildren().add(varView.getVariableV());
		}
		varMap.put(name, varView);
	}


	@Override
	public Group getView() {
		Group group = new Group();
		savedVars.setPrefSize(View.NARROW_WIDTH,View.SHORT_HEIGHT);
		ScrollPane sp = new ScrollPane(savedVars);
		group.getChildren().add(sp);
		return group;
	}
	

}
