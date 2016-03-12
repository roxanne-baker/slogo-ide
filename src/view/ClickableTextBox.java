package view;

import java.util.ResourceBundle;

public abstract class ClickableTextBox extends TextBox {
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	
	public ClickableTextBox(String text) {
		super(text);
		getTextBox().getStyleClass().add(cssResources.getString("CLICKABLETEXT"));
	}
	
	public abstract void onMouseClick();
}
