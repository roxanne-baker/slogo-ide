package view;

import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Model;

public abstract class Palette extends Model {
	
	protected String paletteName;
	private ListProperty<Object> paletteObjectListProperty;
	private Group paletteGroup;
	private static final ResourceBundle PALETTE_RESOURCES = ResourceBundle.getBundle("Palettes");
	private static final ResourceBundle DIALOG_RESOURCES = ResourceBundle.getBundle("DialogBox");


	public Palette(ObservableList<Object> objectList){
		paletteObjectListProperty = new SimpleListProperty<Object>(objectList);
		paletteGroup = new Group();
	}
	public String getPaletteName(){
		return paletteName;
	}
	public ResourceBundle getResourceBundle(){
		return PALETTE_RESOURCES;
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
		System.out.println("here");
		if (index >= paletteObjectListProperty.size()){ //add new object at next available spot
			paletteObjectListProperty.add(obj);
			
		}else if (index <0){
			new DialogBox(AlertType.ERROR,DIALOG_RESOURCES.getString("PALETTE"), DIALOG_RESOURCES.getString("PALETTEINFO"));		}
		else{ //replace already existing object
			paletteObjectListProperty.set(index, obj);

		}
		updateViewGroup();
	}
	public void removeFromPalette(int index){
		paletteObjectListProperty.remove(index);
		updateViewGroup();
	}
	public Object getPaletteObject(int index){
		return paletteObjectListProperty.get(index);
	}


	public int getPaletteSize() {
		return paletteObjectListProperty.size();
	}


	public ListProperty<Object> getPaletteListProperty() {
		return paletteObjectListProperty;
	}

}
