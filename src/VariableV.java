import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class VariableV {
	private Label name;
	private TextField value;

	public VariableV(Variable variable) {
		name = new Label(variable.getName());
		value = new TextField(variable.getValue());
	}
	
	public String getName(){
		return name.getText();
	}
	
	public void setValue(String value){
		this.value.setText(value);
	}
	
	public HBox getVariableV(){
		return new HBox(name,value);
	}

}
