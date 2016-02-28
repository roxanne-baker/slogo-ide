package view;
<<<<<<< HEAD:src/view/VariableView.java

=======
>>>>>>> 004c8185657be13ae90847bec1687b6b9ae77e5f:src/view/VariableView.java
import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class VariableView extends View{
	private int width;
	private int height;
	private HashMap<String,VariableElem> varMap = new HashMap<String,VariableElem>();
	private VBox savedVars = new VBox();
	
	public VariableView(String id) {
		super(id);
//		this.width = width;
//		this.height = height;
//		savedVars.setPrefSize(width,height);
	}
	
	
	public void addVariableView(String name, String value, VariableElem varView){
		if(varMap.containsKey(name)){
			varMap.get(name).setValue(value);
		}
		else{
			savedVars.getChildren().add(varView.getVariableV());
		}
		varMap.put(name, varView);
	}


	@Override
	public Group getView() {
		Group group = new Group();
		ScrollPane sp = new ScrollPane(savedVars);
		group.getChildren().add(sp);
		return group;
	}
	

}
