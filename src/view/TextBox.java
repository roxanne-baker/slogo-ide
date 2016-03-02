package view;

import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TextBox extends Observable {
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	private Text text;
	private HBox textBox;
	private ResourceBundle myResources = ResourceBundle.getBundle(CSS_CLASSES_PATH);
	
	public TextBox(String text){
		this.text = new Text(text);
		this.text.getStyleClass().add(myResources.getString("CODE"));
		textBox = new HBox(this.text);
		textBox.getStyleClass().add(myResources.getString("TEXTBOX"));
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
