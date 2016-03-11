package view;

//<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
//=======
//>>>>>>> refs/remotes/origin/master
import java.util.List;

//<<<<<<< HEAD
import javafx.scene.paint.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Node;
import javafx.scene.paint.Color;


public class CustomColorPalette extends Palette{

	//private static final ObservableList<Object> DEFAULT_COLORS = FXCollections.observableArrayList((Object)new CustomColor(50,80,200),(Object)new CustomColor(50,60,70));
	private static final int SIZE = 20;

	public CustomColorPalette(ObservableList<Object> colorList) {
		super(colorList);
		super.paletteName = getResourceBundle().getString("CUSTOMCOLORS");
	}
	
	@Override
	public CustomColor getPaletteObject(int index){
		Color color = Color.web(getPaletteList().get(index).toString());
		return new CustomColor((int)(color.getRed()*255),(int)(color.getGreen()*255),(int)(color.getBlue()*255));
	}

	@Override
	public Node getPaletteObjectView(int index) {
		CustomColorElem colorView = new CustomColorElem((CustomColor) getPaletteObject(index),SIZE);
		
		return colorView.getView();
	}
	
//	public void addCustomColor(Color color){
//		colorList.add(color);
//	}
//	public void replaceCustomColor(int index, Color color){
//		customColorList.set(index, color);
//	}
//
//	public Color getCustomColor(int index){
//		return customColorList.get(index);
//	}

	
}
