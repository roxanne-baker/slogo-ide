package view;

import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TextBox extends Observable {
	private Text text;
	private HBox textBox;
	
	public TextBox(String text){
		this.text = new Text(text);
		this.text.getStyleClass().add("code");
		textBox = new HBox(this.text);
		textBox.getStyleClass().add("textBox");
	}
	
	public void setClickableAction(EventHandler<? super MouseEvent> onMouseClick){
		textBox.setOnMouseClicked(onMouseClick);
	}
	
	public String getString(){
		return text.getText();
	}
	
	public HBox getTextBox(){
		return textBox;
	}
	
	public void addStyleClass(String styleClass){
		text.getStyleClass().add(styleClass);
	}
	
	@Override
	public boolean equals(Object o){
		return text.getText()==((TextBox)o).getString();
	}

}
