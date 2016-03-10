package GUI;

import java.io.File;
import java.util.List;
import java.util.Observable;




import java.util.function.BiConsumer;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;import javafx.stage.Stage;


public class GuiObjectFileChooser extends GuiObject{

	private FileChooser fileChooser;
	private Button fileButton;
	private BiConsumer<Observable, String> myFunction;
	private Observable observable;

	public GuiObjectFileChooser(String name, String resourceBundle,Observable obs, BiConsumer<Observable, String> function) {
		super(name, resourceBundle, obs);
		fileChooser = new FileChooser();
		fileChooser.setTitle(getResourceBundle().getString(name+"LABEL"));
		fileButton = new Button(getResourceBundle().getString(name+"BUTTON"));
		myFunction = function;
		observable = obs;
	}

	@Override
	public Object createObjectAndReturnObject() {
		Stage stage = new Stage();

		fileButton.setOnAction(evt -> {
			List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
			for (File file: fileList){
				myFunction.accept(observable, file.getPath());
			}
		});
		return null;
	}

	@Override
	public boolean isNewSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsNewSelection(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
