package view;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

public class VariableElem extends Observable {
	private Label name;
	private StringProperty value = new SimpleStringProperty();
	private TextField textBox = new TextField();

	public VariableElem(String name, String value,Observer view) {
		addObserver(view);
		this.name = new Label(name);
		textBox.setEditable(true);
		textBox.textProperty().bind(this.value);
		this.value.set(value);
		textBox.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER){
				setChanged();
				notifyObservers("FIELDCHANGED");	
			}
		});
		
	}
	
	public String getName(){
		return name.getText();
	}
	
	public String getValue(){
		return textBox.getText();
	}
	
	public void setValue(String value){
		System.out.println("setting value to: "+value);
		this.value.set(value);
	}
	
	public HBox getVariableV(){
		return new HBox(name,textBox);
	}

}