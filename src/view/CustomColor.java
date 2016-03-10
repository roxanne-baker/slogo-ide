package view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CustomColor {
	private ObjectProperty<Color> colorProperty;
	public CustomColor(int r, int g, int b){
		colorProperty = new SimpleObjectProperty<Color>(Color.rgb(r, g, b));
	}

	public int getRed(){
		return (int) colorProperty.getValue().getRed();
	}
	public int getGreen(){
		return (int) colorProperty.getValue().getGreen();
	}
	public int getBlue(){
		return (int) colorProperty.getValue().getBlue();
	}
	public void setRed(int r){
		colorProperty.setValue(Color.rgb(r, getGreen(),getBlue()));
	}
	public void setGreen(int g){
		colorProperty.setValue(Color.rgb(getRed(),g,getBlue()));
	}
	public void setBlue(int b){
		colorProperty.setValue(Color.rgb(getRed(), getGreen(), b));
	}

	public ObservableValue<? extends Paint> getColorProperty() {
		return colorProperty;
	}
	public Color getColor(){
		return colorProperty.getValue();
	}

}
