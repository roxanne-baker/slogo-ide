package view;
import java.util.Observable;
import java.util.function.BiConsumer;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class GuiObjectColorPicker extends GuiObject{
	
	private static final double PADDING = 10;
	private ColorPicker colorPicker;
	private BiConsumer<Observable,Color> setValueFunction;
	private Color initialColor;
	private Label colorPickerLabel;
	public GuiObjectColorPicker(String name, String resourceBundle,
			Agent agent, Color color, BiConsumer<Observable,Color> myFunction) {
		super(name, resourceBundle, agent);
		setValueFunction = myFunction;
		initialColor = color;
	}

	@Override
	public Object createObjectAndReturnObject() {
		colorPicker = new ColorPicker(initialColor);
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                setValueFunction.accept(getObservable(), colorPicker.getValue());    
            }
        });
        colorPickerLabel = new Label(getResourceString().getString(getObjectName()+"LABEL"));
		VBox vbox = new VBox();
		vbox.getChildren().addAll(colorPickerLabel,colorPicker);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(0,PADDING,PADDING,PADDING));
		return vbox;
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
