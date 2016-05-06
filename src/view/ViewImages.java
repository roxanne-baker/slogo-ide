package view;

import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Agent;

public class ViewImages extends View {
	private static int HBOX_SPACING = 5;
	private static int VBOX_SPACING = 2;
	private static int CHOOSER_WIDTH = 150;
	private static int IMAGE_HEIGHT = 50;
	private static int CHOOSER_INDEX = 1;
	private HBox hb;
	private ObservableMap<Integer, Agent> agentMap;
	private static final String IMAGES_DIR = "images/";
	private static final ResourceBundle TURTLE_RESOURCES = ResourceBundle.getBundle("TurtleProperties");
	private Agent currentAgent;
	private ComboBoxBase<String> imageDropDown;
	
	public ViewImages(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		imageDropDown = new ComboBox<String>();
		agentMap = FXCollections.observableMap(new HashMap<Integer,Agent>());
		agentMap.addListener(new MapChangeListener(){

			@Override
			public void onChanged(Change change) {
				updateView();	
			}
			
		});
		initImagePicker();
		init();
	}
	
	private void initImagePicker(){
		ObservableList<String> imgNames = FXCollections.observableArrayList();
		File imageDir = new File(IMAGES_DIR);
		for (File imageFile : imageDir.listFiles()) {
			imgNames.add(imageFile.getName());
		}
		imageDropDown = new ComboBox<String>(imgNames);
		imageDropDown.setPrefWidth(CHOOSER_WIDTH);
		imageDropDown.setOnAction(e->{
			if(currentAgent!=null){
				currentAgent.setImagePath(imageDropDown.getValue());
				updateView();
				((VBox)imageDropDown.getParent()).getChildren().remove(imageDropDown);
			}
		});
	}
	
	private void init(){
		VBox pane = new VBox(VBOX_SPACING);
		Label instructions = new Label(TURTLE_RESOURCES.getString("IMAGELABEL"));
		hb = new HBox(HBOX_SPACING);
		hb.setPrefSize(WIDE_WIDTH, NARROW_WIDTH);
		pane.getChildren().addAll(instructions,hb);
		setPane(pane);
	}
	
	private void updateView(){	
		hb.getChildren().clear();
		for(Agent a: agentMap.values()){
			VBox vb = new VBox(VBOX_SPACING);
			Label label = new Label(Integer.toString(a.getName()));
			ImageView imgView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(a.getImagePath())));
			imgView.setPreserveRatio(true);
			imgView.setFitHeight(IMAGE_HEIGHT);
			imgView.setOnMouseClicked(e->selectImage(a,vb));
			vb.getChildren().addAll(imgView,label);
			hb.getChildren().add(vb);
		}
	}
	
	private void selectImage(Agent agent, VBox vb){
		currentAgent = agent;
		vb.getChildren().add(CHOOSER_INDEX,imageDropDown);
	}
	
	public void updateAgentMap(HashMap<Integer,Agent> agentMap){
		this.agentMap.putAll(agentMap);
	}
	
	@Override
	public int getX() {
		return COORD10[0];
	}

	@Override
	public int getY() {
		return COORD10[1]+View.NARROW_WIDTH;
	}

}
