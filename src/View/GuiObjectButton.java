
package View;


import java.util.Observable;

import javafx.scene.control.Button;

public class GuiObjectButton extends GuiObject{
	Button button;
	boolean onAction;
	boolean curBool;
	private String buttonName;
	
	public GuiObjectButton(String name,  String bName, String resourceBundle, boolean initialBool, boolean setOnAction, Observable obs) {
		super(name, resourceBundle, obs);
		onAction = setOnAction;
		curBool = initialBool;
		buttonName = bName;
		
	}

	@Override
	public Object createObjectAndReturnObject() {
		button = new Button(getResourceString().getString(buttonName));
		button.setOnAction(evt -> curBool = onAction);
		return button;
	}

	
	public void setNewValue(boolean newValue) {
		curBool = newValue;
		
	}
	
	public boolean getValue(){
		return curBool;
		
	}

	@Override
	public boolean isNewSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsNewSelection(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
