package controller;

import java.util.List;
import javafx.scene.paint.Color;
import view.CustomColor;
import view.CustomColorPalette;
import view.ViewAgentPreferences;
import view.ViewAgents;

public class BackgroundController extends Controller {

	ViewAgentPreferences myPenModel;
	ViewAgents myBackgroundModel;
	CustomColorPalette myPalette;
	
	public BackgroundController(ViewAgentPreferences penModel, ViewAgents backgroundModel) {
		myPenModel = penModel;
		myBackgroundModel = backgroundModel;
	}
	
	public void addColor(int index, int redVal, int greenVal, int blueVal) {
		int numCustomColors = myPalette.getPaletteSize();
		Color nextColor = Color.rgb(redVal, greenVal, blueVal);
		myPalette.addToPalette(nextColor, index);	
	}
	
	public Color getNextColor(int index) {
		
		Color customColor = myPalette.getPaletteObject(index).getColor();
		return customColor;	
	}
	
	public void clearScreen() {
		myBackgroundModel.clearScreen();
	}
	
	public double clearStamps() {
		return (double) myBackgroundModel.clearStamps();
	}
	
	public void setColorForBackgroundView(int index) {
		myBackgroundModel.setBackgroundColor(getNextColor(index));
	}
	
	public void setColorPalette(CustomColorPalette palette) {
		myPalette = palette;
	}
	
	public int getPaletteSize() {
		return myPalette.getPaletteSize();
	}
}
