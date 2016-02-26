package View;

import java.util.Observable;
import java.util.function.BiConsumer;

import javafx.scene.control.Label;


public class GuiObjectLabel extends GuiObject {
	private Label myLabel;
	private BiConsumer<Observable, String> setValue;
	public GuiObjectLabel(String name, String resourceBundle, Observable obs, BiConsumer<Observable,String> myFunction) {
		super(name, resourceBundle, obs);
		setValue = myFunction;
	}

	@Override
	public Object createObjectAndReturnObject() {
		myLabel = new Label(getResourceString().getString(getObjectName()+ "BigLabel"));
		
		return myLabel;
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
