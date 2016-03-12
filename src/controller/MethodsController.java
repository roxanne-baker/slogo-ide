package controller;

import view.MethodElem;
import view.ViewMethods;
import java.util.*;

import commands.CreatedMethod;
import model.ModelMethods;


public class MethodsController extends Controller implements Observer {
	private ModelMethods model;
	private ViewMethods view;
	
	public MethodsController(ModelMethods model, ViewMethods view) {
		this.model = model;
		this.view = view;
	}
	
	public void addMethod(String name, CreatedMethod method){
		model.addMethod(name,method);
		Map<String,CreatedMethod> methodMap = model.getMethods();
		ArrayList<MethodElem> methodList = new ArrayList<MethodElem>();
		for(String key: methodMap.keySet()){
			methodList.add(new MethodElem(key,methodMap.get(key).getMethodCommands(),this));
		}
		view.update(methodList);
	}
	
	public Map<String, CreatedMethod> getMethodsModel() { 
		return model.getMethods();
	}
	
	@Override
	public void update(Observable o, Object arg) {
	}
}