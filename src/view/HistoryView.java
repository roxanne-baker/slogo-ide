package view;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Interpreter;

public class HistoryView extends View implements Observer{
	String id;
	VBox vb = new VBox(2);
	private Interpreter interpreter;
	
	public HistoryView(String id) {
		super(id);
	}

	
	@Override
	public void update(Observable o, Object arg) {
		if(arg=="NEWHISTORY"){
			vb.getChildren().add(((HistoryElem) o).getTextBox());
		}
		if(arg=="ERROR"){
			Interpreter ip = (Interpreter) o;
			ErrorElem errorText = new ErrorElem(ip.getErrorMessage());
			vb.getChildren().add(errorText.getTextBox());
		}
		if(arg=="CLICKED"){
			interpreter.run(((HistoryElem) o).getString());
		}

	}
	
	public void setInterpreter(Interpreter ip) { 
		interpreter = ip; 
		ip.addObserver(this);
	}
	
	
	@Override
	public Pane getView() {
		Group group = new Group();
		vb.setPrefSize(View.NARROW_WIDTH,View.WIDE_WIDTH);
		ScrollPane sp = new ScrollPane(vb);
		group.getChildren().add(sp);
		Pane pane = new Pane(group);
		setStyleClass(pane);
		return pane;
	}

}
