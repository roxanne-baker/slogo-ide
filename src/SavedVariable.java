import java.util.Observable;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.beans.property.*;

public class SavedVariable extends Observable {
	private String name;
	private StringProperty value = new SimpleStringProperty();
	
	public SavedVariable(String name,String value, View view) {
		this.name = name;
		this.value.set(value);
		addObserver(view);
		setChanged();
		notifyObservers("NEWVAR");
		//add listener to update backend variable value
	}
	
	public HBox getVar(){
		HBox hb = new HBox();
		Label lb = new Label(name+" = ");
		TextField tf = new TextField(value.get());
		tf.setEditable(false);
		tf.setOnMouseEntered(e->{
			tf.setEditable(true);
		});
		tf.setOnMouseExited(e->{
			tf.setEditable(false);
			value.set(tf.getText());
		});
		hb.getChildren().addAll(lb,tf);
		return hb;
	}
	

}
