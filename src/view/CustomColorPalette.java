package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class CustomColorPalette {
	private List<Color> customColorList;
	public CustomColorPalette(){
		customColorList = new ArrayList<>();
		customColorList.add(Color.rgb(50, 100, 100));
		customColorList.add(Color.rgb(30, 100, 30));
	}
	public List<Color> getCustomColorList(){
		return customColorList;
	}
	public void addCustomColor(Color color){
		customColorList.add(color);
		System.out.println("COLOR LIST:");
		for (Color customColor : customColorList) {
			System.out.println(customColor.getRed()+" "+customColor.getGreen()+" "+customColor.getBlue());
		}
	}
	public void replaceColor(int index, Color color){
		customColorList.set(index, color);
	}
	
}
