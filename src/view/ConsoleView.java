package view;
import java.util.Observable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Interpreter;

public class ConsoleView extends View {
	private HistoryView historyView;
	private Interpreter interpreter;
	
	public ConsoleView(String id, HistoryView history) {
		super(id);
		historyView = history;
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}
	
	public void setInterpreter(Interpreter ip) { 
		interpreter = ip; 
	}

	@Override
	public Group getView() {
		Group group = new Group();
		VBox vb = new VBox();
		TextArea console = new TextArea();
		console.getStyleClass().add("code");
		Button runBtn = new Button("Run");
		runBtn.setOnMouseClicked(e->{
			HistoryElem hist = new HistoryElem(console.getText(), historyView);
			interpreter.run(console.getText());
		});
		Button clearBtn = new Button("Clear");
		clearBtn.setOnMouseClicked(e->{
			console.clear();
		});
		HBox buttons = new HBox();
		buttons.getStyleClass().add("hbox");
		buttons.getChildren().addAll(runBtn,clearBtn);
		vb.getChildren().addAll(console,buttons);
		vb.setPrefSize(View.WIDE_WIDTH, View.NARROW_WIDTH);
		group.getChildren().add(vb);
		return group;
	}

}
