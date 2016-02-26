package View;



import java.util.Observable;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class GuiObjectCheckBox extends GuiObject{
	private boolean isChecked;
	private CheckBox checkBox;
	private BiConsumer<Observable, Boolean> setCheckedLambda;
	private boolean isNewSelection;
	
	public GuiObjectCheckBox(String name, String resourceBundle,Agent agent,BiConsumer<Observable,Boolean> lambda) {
		super(name, resourceBundle, agent);
		setCheckedLambda = lambda;
	}

	@Override
	public Object createObjectAndReturnObject() {
		checkBox = new CheckBox(getResourceString().getString(getObjectName()));
		checkBox.setSelected(isChecked);
	    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	        		isChecked = new_val;
	        		isNewSelection = true;        		
	        		setCheckedLambda.accept(getObservable(), isChecked);

	        }
	    });
		return checkBox;
	}
	
	public boolean getValue(){
		return isChecked;
	}

	@Override
	public boolean isNewSelected() {
		
		return isNewSelection;
	}


	@Override
	public void setIsNewSelection(boolean b) {
		isNewSelection = b;
		
	}

}
