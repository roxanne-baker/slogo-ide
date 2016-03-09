
package view;
import java.util.*;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.Label;

public class VariablesView extends View{
	private Pane pane;
	private static final String LABEL_PATH = "windowProperties";	//added
	private ResourceBundle myResources = ResourceBundle.getBundle(LABEL_PATH);	//added
	private VBox savedVars = new VBox();
	private Label instructions = new Label(myResources.getString("VARIABLEINSNS"));	//added
	
	public VariablesView(ViewType ID, Map<String,List<Object>> savedPreferences) {
		super(ID, savedPreferences);
		savedVars.getChildren().add(instructions); // added
		init();
	}
	
	public void update(ArrayList<VariableElem> variables){
		savedVars.getChildren().clear();
		savedVars.getChildren().add(instructions);	// added
		for(VariableElem var: variables){
			savedVars.getChildren().add(var.getVariableV());
		}
		setChanged();
		notifyObservers();
	}


	@Override
	public Pane getView() {
		return pane;
	}

	private Pane init() {
		Group group = new Group();
		savedVars.setPrefSize(View.NARROW_WIDTH,View.NARROW_WIDTH);
		ScrollPane sp = new ScrollPane(savedVars);
		group.getChildren().add(sp);
		pane = new Pane(group);
		setStyleClass(pane);
		return pane;
	}
}