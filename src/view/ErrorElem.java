package view;


public class ErrorElem extends TextBox {

	public ErrorElem(String errorMessage) {
		super(errorMessage);
		addStyleClass("error");
	}


}