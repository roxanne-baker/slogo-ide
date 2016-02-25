
public class SavedMethodC {
	private SavedMethodM model;
	private SavedMethodV view;
	
	public SavedMethodC(SavedMethodM model,SavedMethodV view) {
		this.model = model;
		this.view = view;
	}
	
	public void addMethod(String method){
		model.addMethod(method);
		view.addMethodView(method);
	}

}
