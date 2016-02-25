
public class SavedVariableC {
	private SavedVariableM model;
	private SavedVariableV view;
	
	public SavedVariableC(SavedVariableM model, SavedVariableV view) {
		this.model = model;
		this.view = view;
	}
	
	public void addVariable(String name, String value){
		model.addVariable(name,value);
		view.addVariableView(name,value);
	}
	
	public void editVariable(String name, String newValue){
		model.modifyVariable(name, newValue);
		view.editVariableView(name, newValue);
	}
	
	

}
