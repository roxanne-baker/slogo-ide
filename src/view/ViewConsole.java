package view;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Interpreter;

public class ViewConsole extends ViewInterpretable {
	private static final int CONSOLEX = NARROW_WIDTH;
	private static final int CONSOLEY = WIDE_WIDTH+MENU_OFFSET;
	private ViewHistory historyView;
	private Interpreter interpreter;
	private ResourceBundle buttonResources = ResourceBundle.getBundle("windowProperties");
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	Pane pane;
	
	public ViewConsole(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);
		init();
	}
	
	public void setHistoryView(ViewHistory hv){
		historyView = hv;
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}
	
//	public void setInterpreter(Interpreter ip) { 
//		interpreter = ip; 
//	}

	private void init() {
		VBox vb = new VBox();
		TextArea console = new TextArea();
		console.getStyleClass().add(cssResources.getString("CODE"));
		console.setPrefSize(WIDE_WIDTH,NARROW_WIDTH);
		HBox buttons = initButtons(console);
		vb.getChildren().addAll(console,buttons);
		vb.setPrefSize(WIDE_WIDTH, NARROW_WIDTH);
		setPane(vb);
	}

	private HBox initButtons(TextArea console) {
		Button runBtn = new Button(buttonResources.getString("RUNBUTTON"));
		runBtn.setOnMouseClicked(e->{
			HistoryElem hist = new HistoryElem(console.getText(), historyView);
			getInterpreter().run(console.getText());
		});
		Button clearBtn = new Button(buttonResources.getString("CLEARBUTTON"));
		clearBtn.setOnMouseClicked(e->{
			console.clear();
		});
		HBox buttons = new HBox();
		buttons.getStyleClass().add(cssResources.getString("HBOX"));
		buttons.getChildren().addAll(runBtn,clearBtn);
		return buttons;
	}

}
