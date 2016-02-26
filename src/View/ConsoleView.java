package View;

import java.util.Observable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ConsoleView extends View {
	private int height;
	private int width;
	private View historyView;
	
	public ConsoleView(String id, View history) {
		super(id);
		historyView = history;
//		this.height = height;
//		this.width = width;
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public Group getView() {
		Group group = new Group();
		VBox vb = new VBox();
		TextArea console = new TextArea();
		console.getStyleClass().add("code");
//		console.setPrefSize(width, height);
		Button btn = new Button("Run");
		btn.setOnMouseClicked(e->{
			HistoryElem hist = new HistoryElem(console.getText(),historyView);
//			//if error, create error and add to history
//			
//			//else if variable, create variable and add to saved vars
//			if(console.getText().contains("make '")){
//				String[] textList = console.getText().split(" ");
//				controller.addVariable(textList[1].substring(1),textList[2]);
//			}//else if method, create method and add to saved methods
//			else if(console.getText().contains("save")){
//				String[] textList = console.getText().split(" ");
//				methodcontroller.addMethod(textList[1]);
//			}
//			console.clear();
		});
		vb.getChildren().addAll(console,btn);
		group.getChildren().add(vb);
		return group;
	}

}
