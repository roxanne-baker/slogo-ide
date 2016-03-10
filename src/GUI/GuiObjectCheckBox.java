package GUI;

import java.util.Observable;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import view.Agent;

public class GuiObjectCheckBox extends GuiObject{
	private boolean isChecked;
	private CheckBox checkBox;
	private BiConsumer<Observable, Boolean> setCheckedLambda;
	
	public GuiObjectCheckBox(String name, String resourceBundle, Agent agent,BiConsumer<Observable,Boolean> lambda) {
		super(name, resourceBundle, agent);
		setCheckedLambda = lambda;
	}

	@Override
	public Object createObjectAndReturnObject() {
		checkBox = new CheckBox(getResourceBundle().getString(getObjectName()+"LABEL"));
		checkBox.setSelected(isChecked);
	    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	        		isChecked = new_val;
	        		setCheckedLambda.accept(getObservable(), isChecked);

	        }
	    });
		return checkBox;
	}
	
	public boolean getValue(){
		return isChecked;
	}


}
