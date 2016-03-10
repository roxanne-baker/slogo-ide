package view;

import java.util.Observer;
import javafx.scene.layout.HBox;

public class MethodElem extends TextBox {
	
	
	public MethodElem(String name, String value, Observer view) {
		super(name+"\n   "+value);
	}
	
	public HBox getMethodV(){
		return super.getTextBox();
	}

}

