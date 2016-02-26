package View;
 
import java.util.Observable;

import javafx.scene.text.Text;

public class ClickableText extends Observable {
	private Text text;
	
	public ClickableText(String text){
		this.text = new Text(text);
		this.text.setOnMouseClicked(e->onMouseClick());
		this.text.getStyleClass().add("code");
	}
	
	public void onMouseClick(){
		//execute text;
		System.out.println("clicked");
	}
	
	public Text getText(){
		return text;
	}

}
