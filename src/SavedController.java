import java.util.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SavedController {
	private VBox model;
	private SavedView view;
	public SavedController(VBox model, SavedView sv) {
		this.model=model;
		this.view=sv;
	}
	
	public void addVar(HBox var){
		model.getChildren().add(var);
	}
	
}
