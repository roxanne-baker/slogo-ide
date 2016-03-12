package view;

import java.util.ResourceBundle;

public class ErrorElem extends TextBox {
	private final ResourceBundle myResources = ResourceBundle.getBundle("CSSClasses");

	public ErrorElem(String errorMessage) {
		super("ERROR: "+errorMessage);
		addStyleClass(myResources.getString("ERROR"));
	}
}
