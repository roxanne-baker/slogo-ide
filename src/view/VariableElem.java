package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class VariableElem extends Observable {
	private Label name;
	private TextField value;

	public VariableElem(String name, String value,Observer view) {
		addObserver(view);
		this.name = new Label(name);
		this.value = new TextField(value);
		this.value.setOnMouseClicked(e->{
			setChanged();
			notifyObservers("FIELDCHANGED");
		});
		
	}
	
	public String getName(){
		return name.getText();
	}
	
	public String getValue(){
		return name.getText();
	}
	
	public void setValue(String value){
		this.value.setText(value);
	}
	
	public HBox getVariableV(){
		return new HBox(name,value);
	}

}