package view;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Interpreter;

public class ViewConsole extends View {
	private static final int CONSOLEX = NARROW_WIDTH;
	private static final int CONSOLEY = WIDE_WIDTH+MENU_OFFSET;
	private static final String BUTTON_LABEL_PATH = "windowProperties";
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	private ViewHistory historyView;
	private Interpreter interpreter;
	private ResourceBundle buttonResources;
	private ResourceBundle cssResources;
	Pane pane;
	
	public ViewConsole(ViewType ID, Map<String,List<Object>> savedPreferences) {
		super(ID, savedPreferences);
		buttonResources = ResourceBundle.getBundle(BUTTON_LABEL_PATH);
		cssResources = ResourceBundle.getBundle(CSS_CLASSES_PATH);
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
	
	public void setInterpreter(Interpreter ip) { 
		interpreter = ip; 
	}

	private void init() {
		VBox vb = new VBox();
		TextArea console = new TextArea();
		console.getStyleClass().add(cssResources.getString("CODE"));
		HBox buttons = initButtons(console);
		vb.getChildren().addAll(console,buttons);
		vb.setPrefSize(View.WIDE_WIDTH, View.NARROW_WIDTH);
		setPane(vb);
	}

	private HBox initButtons(TextArea console) {
		Button runBtn = new Button(buttonResources.getString("RUNBUTTON"));
		runBtn.setOnMouseClicked(e->{
			HistoryElem hist = new HistoryElem(console.getText(), historyView);
			interpreter.run(console.getText());
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
