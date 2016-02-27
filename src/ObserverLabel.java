import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class ObserverLabel implements Observer{
	private String labelName;
	private ResourceBundle myResources;
	private Label observerLabel;
	private Object labelText;
	private Label myLabel;
	public ObserverLabel(String name, String resources, Object text){
		labelName = name;
		myResources = ResourceBundle.getBundle(resources);
		labelText = text;
	}
	
	public Object createAndReturnObserverLabel(){
		observerLabel = new Label((String) labelText);
		myLabel = new Label(myResources.getString(labelName +"LABEL")+": ");
		HBox hbox = new HBox();
		hbox.getChildren().addAll(myLabel,observerLabel);
		return hbox;
	}
	
	public Label getObserverLabel(){
		return observerLabel;
	}
	@Override
	public void update(Observable newName, Object agent) {
		
	}


}
