package GUI;

import java.util.List;
import java.util.Observable;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GuiObjectDropDown extends GuiObject {
	private static final double PADDING = 10;
	BiConsumer<Observable,Integer> myFunction;
	private String defaultValue;
	private List<Object> dropDownList;
	private ComboBox<String> dropDownObject;
	private VBox dropDownBox;
	public GuiObjectDropDown(String name, String resourceBundle, Observable obs, String startValue, List<Object> list, BiConsumer<Observable,Integer> function) {
		super(name, resourceBundle, obs);
		myFunction = function;
		defaultValue = startValue;
		dropDownList = list;
		dropDownObject = new ComboBox<String>();
		dropDownBox = new VBox();
		dropDownBox.setPadding(new Insets(0,PADDING,PADDING,PADDING));

	}

	@Override
	public Object createObjectAndReturnObject() {
		for (Object item: dropDownList){
			String itemString = item.toString();
			dropDownObject.getItems().add(itemString);
		}
		dropDownObject.setValue(defaultValue);
		dropDownObject.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String oldValue, String newValue) {
            	int index = getDropDownIndex(newValue);
    			System.out.println(index);

            	myFunction.accept(getObservable(),index);
            }

			private int getDropDownIndex(String newValue) {
				int index = 0 ;
            	
            	for (Object item: dropDownList){
            		if(item == newValue){
            			return index;
            		}index++;
            	}
            	
				return index;
			}
		});
		Label dropDownLabel = new Label(getResourceBundle().getString(getObjectName()+"LABEL"));
		dropDownBox.getChildren().addAll(dropDownLabel,dropDownObject);
		return dropDownBox;
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
