package view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Palette extends Controller{
	
	protected String paletteName;
	protected List<Object> paletteObjectList;
	protected Group paletteGroup;
	private ResourceBundle myResources = ResourceBundle.getBundle("Palettes");
	
	public Palette(){
		paletteGroup = new Group();
	}
	public String getPaletteName(){
		return paletteName;
	}
	public ResourceBundle getResourceBundle(){
		return myResources;
	}
	public List<Object> getPaletteList(){
		return paletteObjectList;
	}
	public Group getPaletteViewGroup(){
		HBox hbox = new HBox();
		for (int index = 0; index < getPaletteList().size(); index++){
			VBox elemBox = new VBox();
			elemBox.setAlignment(Pos.CENTER);
			Node objectView = getPaletteObjectView(index);
			Label indexLabel = new Label(Integer.toString(index));
			elemBox.getChildren().addAll(objectView,indexLabel);
			hbox.getChildren().add(elemBox);
		}
		paletteGroup.getChildren().add(hbox);
		return paletteGroup;
	}
	
	public abstract Node getPaletteObjectView(int index);
	
	public void addToPalette(Object obj, int index){
		paletteObjectList.set(index, obj);
	}
	public void removeFromPalette(int index){
		paletteObjectList.remove(index);
	}
	public Object getPaletteObject(int index){
		return paletteObjectList.get(index);
	}
	public void setNewPaletteList(List<Object> customList){
			paletteObjectList = new ArrayList<Object>();
			for (Object obj: customList){
				paletteObjectList.add(obj);
			}
			
		}


}
