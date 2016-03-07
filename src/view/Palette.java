package view;

import java.util.List;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public abstract class Palette extends Controller{
	
	private String paletteName;
	private List<Object> paletteObjectList;
	protected Group paletteGroup;
	
	public Palette(String id){
		paletteName = id;
		paletteGroup = new Group();
	}
	public String getPaletteName(){
		return paletteName;
	}
	public List<Object> getPaletteList(){
		return paletteObjectList;
	}
	public Group getPaletteViewGroup(){
		HBox hbox = new HBox();
		for (int index = 0; index < paletteObjectList.size(); index++){
			hbox.getChildren().add((Node) getPaletteObject(index));
		}
		paletteGroup.getChildren().add(hbox);
		return paletteGroup;
	}
	
	public void addToPalette(Object obj, int index){
		paletteObjectList.set(index, obj);
	}
	public void removeFromPalette(int index){
		paletteObjectList.remove(index);
	}
	public Object getPaletteObject(int index){
		return paletteObjectList.get(index);
	}
	

}
