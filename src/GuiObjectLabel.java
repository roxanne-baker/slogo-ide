import java.util.Observable;
import javafx.scene.control.Label;


public class GuiObjectLabel extends GuiObject{
	private Label myLabel;
	public GuiObjectLabel(String name, String resourceBundle, Observable obs) {
		super(name, resourceBundle, obs);
	}

	@Override
	public Object createObjectAndReturnObject() {
		myLabel = new Label(getResourceString().getString(getObjectName()+ "LABEL"));
		
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
