package view;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
/**
 * These are labels that are binded to a specific property of an agent. 
 * @author Melissa Zhang
 *
 */

public class ObserverLabel {
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
		observerLabel = new Label((String) ""+labelText);
		myLabel = new Label(myResources.getString(labelName +"LABEL")+": ");
		HBox hbox = new HBox();
		hbox.getChildren().addAll(myLabel,observerLabel);
		return hbox;
	}
	
	public Label getObserverLabel(){
		return observerLabel;
	}



}
