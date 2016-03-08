package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class CustomColorPalette {
	private List<Color> customColorList;
	public CustomColorPalette(){
		customColorList = new ArrayList<>();
		customColorList.add(Color.rgb(0, 0, 0));
		customColorList.add(Color.rgb(255, 255, 255));
		customColorList.add(Color.rgb(255, 0, 0));
		customColorList.add(Color.rgb(0, 0, 255));
		customColorList.add(Color.rgb(0, 128, 0));
		customColorList.add(Color.rgb(255, 255, 0));
		customColorList.add(Color.rgb(0, 255, 255));
		customColorList.add(Color.rgb(255, 0, 255));
		customColorList.add(Color.rgb(128, 128, 128));
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
