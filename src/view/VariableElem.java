package view;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VariableElem extends Observable {
	private Label name;
	private String value;
	private TextField textBox = new TextField();

	public VariableElem(String name, String value,Observer view) {
		addObserver(view);
//		this.name = new Label(name);
		this.name = new Label(name+" = ");
		this.value = value;
		textBox.setEditable(true);
		textBox.setText(value);
		textBox.setOnKeyPressed(e->changeField(e.getCode()));
		
	}
	
	private void changeField(KeyCode key){
		if(key==KeyCode.ENTER){
			value = textBox.getText();
			setChanged();
			notifyObservers("FIELDCHANGED");	
		}
	}
	
	public String getName(){
		return name.getText();
	}
	
	public String getValue(){
		return value;
	}
	
	public void setValue(String value){
		textBox = new TextField(value);
		
	}
	
	public HBox getVariableV(){
		return new HBox(name,textBox);
	}
}