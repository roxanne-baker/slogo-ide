package view;

import java.util.ResourceBundle;

public class ResultElem extends TextBox {
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	private ResourceBundle myResources = ResourceBundle.getBundle(CSS_CLASSES_PATH);

	public ResultElem(String resultMessage) {
		super(resultMessage);
		addStyleClass(myResources.getString("RESULT"));
	}
}
