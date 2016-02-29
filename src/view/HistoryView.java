package view;

import java.util.HashMap;
import java.util.Observable;

import java.util.Observable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class HistoryView extends View {
	String id;
	VBox vb = new VBox(2);
	
	public HistoryView(String id) {
		super(id);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg=="NEWHISTORY"){
			vb.getChildren().add(((HistoryElem) o).getTextBox());
		}
		if(arg=="ERROR"){
			
		}

	}
	
	
	
	@Override
	public Group getView() {
		Group group = new Group();
		vb.setPrefSize(View.NARROW_WIDTH,View.TALL_HEIGHT);
		ScrollPane sp = new ScrollPane(vb);
		group.getChildren().add(sp);
		return group;
	}

}

