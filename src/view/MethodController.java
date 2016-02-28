package view;


public class MethodController {
	private MethodModel model;
	private MethodView view;
	
	public MethodController(MethodModel model,MethodView view) {
		this.model = model;
		this.view = view;
	}
	
	public void addMethod(String method){
		model.addMethod(method);
		view.addMethodView(method);
	}

}
