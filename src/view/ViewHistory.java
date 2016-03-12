package view;

import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Interpreter;

public class ViewHistory extends ViewInterpretable implements Observer{
	private VBox vb = new VBox();
	private final ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");

	public ViewHistory(ViewType ID, Preferences savedPreferences){
		super(ID, savedPreferences);
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
			getInterpreter().run(((HistoryElem) o).getString());
		}
		if (arg=="RESULT") { 
			Interpreter ip = (Interpreter) o;
			ResultElem resultText = new ResultElem(ip.getReturnResult());
			vb.getChildren().add(resultText.getTextBox());
		}

	}
	
	@Override
	public void setInterpreter(Interpreter ip) { 
		super.setInterpreter(ip);
		ip.addObserver(this);
	}
	

	private void init() {
		vb.setPrefSize(View.NARROW_WIDTH,View.WIDE_WIDTH);
		vb.getStyleClass().add(cssResources.getString("VBOX"));
		ScrollPane sp = new ScrollPane(vb);
		setPane(sp);
	}

	@Override
	public int getX() {
		return COORD02[0];
	}

	@Override
	public int getY() {
		return COORD02[1];
	}
}