package view;

import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;

import model.Agent;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewImageChanger extends View{
	private VBox imageBox = new VBox();
	private final ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	private final ResourceBundle viewProperties = ResourceBundle.getBundle("imageChooser");
	private HashMap<Integer, Agent> agentMap = new HashMap<Integer,Agent>();

	public ViewImageChanger(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		init();

	}
	private void init() {
		imageBox.setPrefSize(View.NARROW_WIDTH,View.WIDE_WIDTH);
		imageBox.getStyleClass().addAll(cssResources.getString("VBOX"),cssResources.getString("DISPLAYVIEW"));
		ScrollPane sp = new ScrollPane(imageBox);
		setPane(sp);
		updateView();
	}
	public void updateAgentMap(HashMap<Integer,Agent> newAgentMap){
		agentMap = (HashMap<Integer, Agent>) newAgentMap;
		updateView();
	}


	public void updateView() {
		deleteAgentImages();
		for (Integer agentID: agentMap.keySet()){
			Agent thisAgent = agentMap.get(agentID);
			Text nameText =  new Text(agentID.toString());
			bindProperty(nameText.textProperty(),thisAgent.getIDProperty());
			Button imageChooser = new Button(viewProperties.getString("ImageButton"));
			imageChooser.setOnAction(e-> changeImage(agentID));
			ImageView image = thisAgent.getAgentView().getImageCopy();
			imageBox.getChildren().addAll(nameText,image,imageChooser);
			
		}
		
	}
	private void changeImage(Integer agentID) {
		File imageFile = FileUtilities.promptAndGetFile(FileUtilities.getImageFilters(), viewProperties.getString("FilePrompt"), viewProperties.getString("FileType"));
		if (imageFile!=null){
		agentMap.get(agentID).setImagePath(imageFile.getPath());
		updateView();
		}
	}
	
	private void bindProperty(StringProperty stringProperty, Property idProperty) {
		stringProperty.bind(Bindings.convert(idProperty));
	}
	
	private void deleteAgentImages() {
		imageBox.getChildren().clear();
	}
	@Override
	public int getX() {
		return COORD03[0];
	}

	@Override
	public int getY() {
		return COORD03[1];
	}

}
