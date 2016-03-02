package view;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Interpreter;

public class ConsoleView extends View {
	private static final String BUTTON_LABEL_PATH = "windowProperties";
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	private HistoryView historyView;
	private Interpreter interpreter;
	private ResourceBundle buttonResources;
	private ResourceBundle cssResources;
	
	public ConsoleView(String id, HistoryView history) {
		super(id);
		historyView = history;
		buttonResources = ResourceBundle.getBundle(BUTTON_LABEL_PATH);
		cssResources = ResourceBundle.getBundle(CSS_CLASSES_PATH);
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}
	
	public void setInterpreter(Interpreter ip) { 
		interpreter = ip; 
	}

	@Override
	public Pane getView() {
		Group group = new Group();
		VBox vb = new VBox();
		TextArea console = new TextArea();
		console.getStyleClass().add(cssResources.getString("CODE"));
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
		vb.getChildren().addAll(console,buttons);
		vb.setPrefSize(View.WIDE_WIDTH, View.NARROW_WIDTH);
		group.getChildren().add(vb);
		Pane pane = new Pane(group);
		setStyleClass(pane);
		return pane;
	}

}
