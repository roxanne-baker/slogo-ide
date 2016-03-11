package GUI;

import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.beans.property.ListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GuiObjectDropDown extends GuiObject {	private static final double WIDTH = 150;
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	
	BiConsumer<Observable,Integer> myFunction;
	private String defaultValue;
	private ComboBox<String> dropDownObject;
	private VBox dropDownBox;
	public GuiObjectDropDown(String name, String resourceBundle, Observable obs, String startValue, ListProperty list, BiConsumer<Observable,Integer> function) {
		super(name, resourceBundle, obs);
		myFunction = function;
		defaultValue = startValue;
		dropDownObject = new ComboBox<String>();
		dropDownObject.setMaxWidth(WIDTH);
		dropDownObject.itemsProperty().bind(list);		
		dropDownBox = new VBox();
		dropDownBox.getStyleClass().add(cssResources.getString("VBOX"));

	}

	@Override
	public Object createObjectAndReturnObject() {

		dropDownObject.setValue(defaultValue);
		dropDownObject.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String oldValue, String newValue) {
            	int index = dropDownObject.getItems().indexOf(newValue);
            	myFunction.accept(getObservable(),index);

				
			}
		});
		Label dropDownLabel = new Label(getResourceBundle().getString(getObjectName()+"LABEL"));
		dropDownBox.getChildren().addAll(dropDownLabel,dropDownObject);
		return dropDownBox;
	}



}
