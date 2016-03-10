package GUI;

import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import view.Agent;

public class GuiObjectSlider extends GuiObject{
	private Slider slider;
	private double curValue;
	private Label textLabel;
	private Label numLabel;
	private double minValue;
	private double maxValue;
	private double numIncrement;
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");

	private BiConsumer<Observable, Double> setValueToXML;
	public GuiObjectSlider(String name, String resourceBundle, Agent agent, double min,double max, double initialValue, double increment, BiConsumer<Observable,Double> setFunction) {
		super(name, resourceBundle, agent);
		minValue = min;
		maxValue = max;
		numIncrement = increment;
		curValue = initialValue;
		setValueToXML = setFunction;
	}

	@Override
	public Object createObjectAndReturnObject() {
		slider = new Slider(minValue,maxValue,curValue); 
		slider.setShowTickMarks(true);
		slider.setBlockIncrement(numIncrement);
		textLabel = new Label(getResourceBundle().getString(getObjectName()+"LABEL"));
		numLabel = new Label(Double.toString(slider.getValue()));
		slider.valueProperty().addListener(new ChangeListener<Object>(){
			@Override
			public void changed(ObservableValue<?> ov,Object oldValue, Object NewValue){
				long s = Math.round(slider.getValue());
				curValue = s;
				numLabel.textProperty().setValue(Long.toString(s));
				if(setValueToXML!=null){
					setValueToXML.accept(getObservable(),curValue);
				}
			}
		}
		);

		VBox vbox = new VBox();
		vbox.getStyleClass().add(cssResources.getString("VBOX"));
		vbox.getChildren().addAll(textLabel,slider,numLabel);
		return vbox;
	}


	public double getValue(){
		return curValue;
	}


}
