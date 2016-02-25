import java.util.HashMap;
import java.util.Observable;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class SavedVariableV extends View{
	private int width;
	private int height;
	private HashMap<String,VariableV> varMap = new HashMap<String,VariableV>();
	private VBox savedVars = new VBox();
	
	public SavedVariableV(String id, int height, int width,HashMap<String,View> viewCollection) {
		super(id, height, width,viewCollection);
		this.width = width;
		this.height = height;
	}
	
	public void addVariableView(String name, String value, VariableV varView){
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
		savedVars.setPrefSize(width,height);
		ScrollPane sp = new ScrollPane(savedVars);
		group.getChildren().add(sp);
		return group;
	}
	

}
