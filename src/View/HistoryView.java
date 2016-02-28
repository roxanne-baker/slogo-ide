package View;
import java.util.HashMap;
import java.util.Observable;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.*;

public class HistoryView extends View {
	String id;
	int height;
	int width;
	VBox vb = new VBox();
	
	public HistoryView(String id) {
		super(id);
//		this.height=height;
//		this.width=width;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg=="NEWHISTORY"){
			vb.getChildren().add(((HistoryElem) o).getText());
		}
		if(arg=="ERROR"){
			
		}

	}
	
	
	
	@Override
	public Group getView() {
		Group group = new Group();
//		vb.setPrefWidth(width);
		ScrollPane sp = new ScrollPane(vb);
//		sp.setPrefSize(width,height);
		sp.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		group.getChildren().add(sp);
		return group;
	}

}
