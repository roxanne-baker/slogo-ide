package view;

import java.util.Arrays;
import java.util.List;

public class CustomColorPalette {
	private List<CustomColor> customColorList = Arrays.asList(new CustomColor(50,100,100),new CustomColor(30,100,30));
	public CustomColorPalette(){
	
	}
	public List<CustomColor> getCustomColorList(){
		return customColorList;
	}
	public void addCustomColor(CustomColor color){
		customColorList.add(color);
	}
	public void replaceColor(int index, CustomColor color){
		if (index > customColorList.size() || index < 0){
			//don't add color; maybe through error?
		}else{
			customColorList.set(index, color);
		}
	}
	
}
