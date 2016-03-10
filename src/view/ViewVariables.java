
package view;
import java.util.*;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.Label;

public class ViewVariables extends View{
	private static final int CONSOLEX = NARROW_WIDTH+WIDE_WIDTH+NARROW_WIDTH;
	private static final int CONSOLEY = MENU_OFFSET+WIDE_WIDTH;
	private Pane pane;
	private static final String LABEL_PATH = "windowProperties";	//added
	private ResourceBundle myResources = ResourceBundle.getBundle(LABEL_PATH);	//added
	private VBox savedVars = new VBox();
	private Label instructions = new Label(myResources.getString("VARIABLEINSNS"));	//added
	
	public ViewVariables(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		savedVars.getChildren().add(instructions); // added
		setX(CONSOLEX);
		setY(CONSOLEY);
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

	private Pane init() {
		savedVars.setPrefSize(View.NARROW_WIDTH,View.NARROW_WIDTH);
		ScrollPane sp = new ScrollPane(savedVars);
		setPane(sp);
		return pane;
	}
}