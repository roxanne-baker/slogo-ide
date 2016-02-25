import java.util.Observable;

import javafx.scene.text.Text;

public abstract class ClickableText extends Observable {
	private Text text;
	
	public ClickableText(String text, String notifyMessage, View view){
		this.text = new Text(text);
		this.text.setOnMouseClicked(e->onMouseClick());
		this.text.getStyleClass().add("code");
		addObserver(view);
		setChanged();
		notifyObservers(notifyMessage);
	}
	
	public abstract void onMouseClick();
	
	public Text getText(){
		return text;
	}

}
