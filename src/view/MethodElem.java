package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

public class MethodElem extends Observable {
	private Label name;
	private String value;
	private TextField textBox = new TextField();
	
	
	public MethodElem(String name, String value, Observer view) {
		addObserver(view);
		this.name = new Label(name);
		this.value = value;
		textBox.setEditable(false);
		textBox.setText(value);
//		textBox.setOnKeyPressed(e->changeField(e.getCode()));
//		super(text);
//		setClickableAction(e-> onMouseClick());
//		addObserver(view);
//		setChanged();
//		notifyObservers("NEWMETHOD");
	}

	public void onMouseClick() {
		setChanged();
		notifyObservers("CLICKED");
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
	
	public HBox getMethodV(){
		return new HBox(name,textBox);
	}

}

