package view;
import java.util.*;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class VariablesView extends View{
	private static final String LABEL_PATH = "windowProperties";
	private ResourceBundle myResources = ResourceBundle.getBundle(LABEL_PATH);
	private VBox savedVars = new VBox();
	Label instructions = new Label(myResources.getString("VARIABLEINSNS"));
	
	public VariablesView(String id) {
		super(id);
		savedVars.getChildren().add(instructions);
	}
	
	public void update(ArrayList<VariableElem> variables){
		savedVars.getChildren().clear();
		savedVars.getChildren().add(instructions);
		for(VariableElem var: variables){
			savedVars.getChildren().add(var.getVariableV());
		}
		setChanged();
		notifyObservers();
	}


	@Override
	public Pane getView() {
		Group group = new Group();
		ScrollPane sp = new ScrollPane(savedVars);
		sp.setPrefSize(NARROW_WIDTH, NARROW_WIDTH);
		group.getChildren().add(sp);
		Pane pane = new Pane(group);
		setStyleClass(pane);
		return pane;
	}
	

}