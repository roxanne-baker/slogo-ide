
package view;
import java.util.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.Label;

public class ViewVariables extends View{
	private Pane pane;
	private final ResourceBundle windowResources = ResourceBundle.getBundle("windowProperties");	//added
	private final ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	private VBox savedVars = new VBox();
	private Label instructions = new Label(windowResources.getString("VARIABLEINSNS"));	//added
	
	public ViewVariables(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		savedVars.getChildren().add(instructions); // added
		savedVars.getStyleClass().add(cssResources.getString("DISPLAYVIEW"));
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

	@Override
	public int getX() {
		return COORD13[0];
	}

	@Override
	public int getY() {
		return COORD13[1];
	}
}