package view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
/**
 * This is a palette object for all the custom colors available for the user.
 * @author Melissa Zhang
 *
 */

public class CustomColorPalette extends Palette{

	private static final int SIZE = 20;

	public CustomColorPalette(ObservableList<Object> colorList) {
		super(colorList);
		super.paletteName = getResourceBundle().getString("CUSTOMCOLORS");
	}
	
	@Override
	public Object getPaletteObject(int index){
		Color color = Color.web(getPaletteList().get(index).toString());
		return new CustomColor((int)(color.getRed()*255),(int)(color.getGreen()*255),(int)(color.getBlue()*255));
	}

	@Override
	public Node getPaletteObjectView(int index) {
		CustomColorElem colorView = new CustomColorElem((CustomColor) getPaletteObject(index),SIZE);
		
		return colorView.getView();
	}



	
}
