package view;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.paint.Color;


public class CustomColorPalette extends Palette{
	private static final int SIZE = 20;

	public CustomColorPalette(List<Object> colorList) {
		super(colorList);
		super.paletteName = getResourceBundle().getString("CUSTOMCOLORS");
	}
	
	@Override
	public Object getPaletteObject(int index){
		Color color = Color.web(paletteObjectList.get(index).toString());
		return new CustomColor((int)(color.getRed()*255),(int)(color.getGreen()*255),(int)(color.getBlue()*255));
	}

	@Override
	public Node getPaletteObjectView(int index) {
		CustomColorElem colorView = new CustomColorElem((CustomColor) getPaletteObject(index),SIZE);
		
		return colorView.getView();
	}



	
}
