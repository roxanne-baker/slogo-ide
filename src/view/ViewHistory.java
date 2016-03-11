package view;

import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Interpreter;

public class ViewHistory extends View implements Observer{
	private static final int CONSOLEX =NARROW_WIDTH+WIDE_WIDTH;
	private static final int CONSOLEY = MENU_OFFSET;
	private VBox vb = new VBox(2);
	private Interpreter interpreter;

	public ViewHistory(ViewType ID, Preferences savedPreferences){
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);
		init();
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
		if (arg=="RESULT") { 
			Interpreter ip = (Interpreter) o;
			ResultElem resultText = new ResultElem(ip.getReturnResult());
			vb.getChildren().add(resultText.getTextBox());
		}

	}
	
	public void setInterpreter(Interpreter ip) { 
		interpreter = ip; 
		ip.addObserver(this);
	}
	

	private void init() {
		vb.setPrefSize(View.NARROW_WIDTH,View.WIDE_WIDTH);
		ScrollPane sp = new ScrollPane(vb);
		setPane(sp);
	}
}