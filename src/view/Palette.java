package view;

import java.util.List;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Palette extends Controller{
	
	protected String paletteName;
	private ListProperty<Object> paletteObjectListProperty;
	private Group paletteGroup;
	private ResourceBundle myResources = ResourceBundle.getBundle("Palettes");
	
	public Palette(){
		paletteObjectListProperty = new SimpleListProperty<Object>();
		paletteGroup = new Group();
	}
	public String getPaletteName(){
		return paletteName;
	}
	public ResourceBundle getResourceBundle(){
		return myResources;
	}
	public List<Object> getPaletteList(){

		return paletteObjectListProperty.getValue();
	}
	public Group getPaletteViewGroup(){
		paletteGroup.getChildren().removeAll();
		updateViewGroup();
		return paletteGroup;
	}
	private void updateViewGroup() {
		HBox hbox = new HBox();
		for (int index = 0; index < getPaletteSize(); index++){
			VBox elemBox = new VBox();
			elemBox.setAlignment(Pos.CENTER);
			Node objectView = getPaletteObjectView(index);
			Label indexLabel = new Label(Integer.toString(index));
			elemBox.getChildren().addAll(objectView,indexLabel);
			hbox.getChildren().add(elemBox);
		}

		paletteGroup.getChildren().add(hbox);
	}
	
	public abstract Node getPaletteObjectView(int index);
	
	public void addToPalette(Object obj, int index){
		if (index >= paletteObjectListProperty.size()){ //add new object at next available spot
			paletteObjectListProperty.add(obj);
			
		}else if (index <0){
			//throw error
		}
		else{ //replace already existing object
			paletteObjectListProperty.set(index, obj);

		}
		updateViewGroup();
	}
	public void removeFromPalette(int index){
		paletteObjectListProperty.remove(index);
	}
	public Object getPaletteObject(int index){
		return paletteObjectListProperty.get(index);
	}
	public void setNewPaletteList(ObservableList<Object> customList){
			paletteObjectListProperty.setValue((ObservableList<Object>) customList);

			
		}
	public int getPaletteSize() {
		return paletteObjectListProperty.size();
	}


	public ListProperty<Object> getPaletteListProperty() {
		return paletteObjectListProperty;
	}

}
