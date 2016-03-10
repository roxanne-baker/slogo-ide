package view;

import java.util.ResourceBundle;

public class ErrorElem extends TextBox {
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	private ResourceBundle myResources = ResourceBundle.getBundle(CSS_CLASSES_PATH);

	public ErrorElem(String errorMessage) {
		super(errorMessage);
		addStyleClass(myResources.getString("ERROR"));
	}


}
