package GUI;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.Agent;


public class GuiObjectColorPicker extends GuiObject{
	
	private static final double PADDING = 10;
	private ColorPicker colorPicker;
	private BiConsumer<Observable,Color> setValueFunction;
	private Color initialColor;
	private Label colorPickerLabel;
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	
	public GuiObjectColorPicker(String name, String resourceBundle,
			Agent agent, Color color, BiConsumer<Observable,Color> myFunction) {
		super(name, resourceBundle, agent);
		setValueFunction = myFunction;
		initialColor = color;
	}

	@Override
	public Object createObjectAndReturnObject() {
		colorPicker = new ColorPicker(initialColor);
		colorPicker.getStyleClass().add("combo-box");
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                setValueFunction.accept(getObservable(), colorPicker.getValue());    
            }
        });
        colorPickerLabel = new Label(getResourceBundle().getString(getObjectName()+"LABEL"));
		VBox vbox = new VBox();
		vbox.getStyleClass().add(cssResources.getString("VBOX"));
		vbox.getChildren().addAll(colorPickerLabel,colorPicker);
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
