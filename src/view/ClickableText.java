package view;
import java.util.Observable;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ClickableText extends Observable {
	private Text text;
	
	public ClickableText(String text){
		this.text = new Text(text);
		this.text.getStyleClass().add("code");
	}
	
	public void onMouseClick(){
		//execute text;
		System.out.println("clicked");
	}
	
	public String getString(){
		return text.getText();
	}
	
	public HBox getTextBox(){
		HBox textBox = new HBox(text);
		textBox.setOnMouseClicked(e->onMouseClick());
		textBox.getStyleClass().add("textBox");
		return textBox;
	}
	
	@Override
	public boolean equals(Object o){
		return text.getText()==((ClickableText)o).getString();
	}

}
