package view;

import java.util.ResourceBundle;

public class ErrorElem extends TextBox {
	private static final String ERROR = "ERROR";
	private final ResourceBundle myResources = ResourceBundle.getBundle("CSSClasses");

	public ErrorElem(String errorMessage) {
		super(ERROR+": "+errorMessage);
		addStyleClass(myResources.getString(ERROR));
		getTextBox().getStyleClass().add(myResources.getString(ERROR));
	}
}
