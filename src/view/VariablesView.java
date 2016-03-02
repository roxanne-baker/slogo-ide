package view;
import java.util.*;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class VariablesView extends View{
	private VBox savedVars = new VBox();
	
	public VariablesView(String id) {
		super(id);
	}
	
	public void update(ArrayList<VariableElem> variables){
		savedVars.getChildren().clear();
		for(VariableElem var: variables){
			savedVars.getChildren().add(var.getVariableV());
		}
		setChanged();
		notifyObservers();
	}


	@Override
	public Group getView() {
		Group group = new Group();
		savedVars.setPrefSize(View.NARROW_WIDTH,View.NARROW_WIDTH);
		ScrollPane sp = new ScrollPane(savedVars);
		group.getChildren().add(sp);
		return group;
	}
	

}