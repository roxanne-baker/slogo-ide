import java.util.Observable;
import java.util.Observer;

public class SavedVariableC implements Observer {
	private SavedVariableM model;
	private SavedVariableV view;
	
	public SavedVariableC(SavedVariableM model, SavedVariableV view) {
		this.model = model;
		this.view = view;
	}
	
	public void addVariable(String name, String value){
		model.addVariable(name,value);
		view.addVariableView(name, value, new VariableV(name,value,this));
	}
	
	@Override
	public void update(Observable savedObj, Object arg) {
		if(arg=="FIELDCHANGED"){
			addVariable(((VariableV)savedObj).getName(),((VariableV)savedObj).getValue());
		}
	}

}
