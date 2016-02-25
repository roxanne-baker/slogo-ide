import java.util.*;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HistoryController {
	private VBox model;
	private HistoryView view;
	public HistoryController(VBox model, HistoryView view) {
		this.model=model;
		this.view=view;
	}
	
	public void addHistory(Text history){
		model.getChildren().add(history);
	}
	
}
