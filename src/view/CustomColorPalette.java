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
	}
	public void replaceCustomColor(int index, Color color){
		customColorList.set(index, color);
	}

	public Color getCustomColor(int index){
		return customColorList.get(index);
	}
	
}
