package controller;

import java.util.List;
import javafx.scene.paint.Color;
import view.ViewAgentPreferences;
import view.ViewAgents;

public class BackgroundController extends Controller {

	ViewAgentPreferences myPenModel;
	ViewAgents myBackgroundModel;
	Color colorToAdd;
	
	public BackgroundController(ViewAgentPreferences penModel, ViewAgents backgroundModel) {
		myPenModel = penModel;
		myBackgroundModel = backgroundModel;
	}
	
	public void addColor(int index, int redVal, int greenVal, int blueVal) {
		int numCustomColors = myPenModel.getColorPalette().getCustomColorList().size();
		Color nextColor = Color.rgb(redVal, greenVal, blueVal);
		if (numCustomColors == index) {
			myPenModel.getColorPalette().addCustomColor(nextColor);
		}
		else {
			myPenModel.getColorPalette().replaceCustomColor(index, nextColor);
		}
		myPenModel.setUpCustomColors();		
	}
	
	public Color getNextColor(int index) {
		List<Color> customColors = myPenModel.getColorPalette().getCustomColorList();
		return customColors.get(index);	
	}
	
	public void clearScreen() {
		myBackgroundModel.clearScreen();
	}
	
	public double clearStamps() {
		return (double) myBackgroundModel.clearStamps();
	}
	
	public void setColorForBackgroundView(int index) {
		myBackgroundModel.setColor(getNextColor(index));
	}
	
	public int getNumColors() {
		return myPenModel.getColorPalette().getCustomColorList().size();
	}
}
