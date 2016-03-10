package controller;
import view.MethodElem;
import view.ViewMethods;
import view.VariableElem;
import java.util.*;

import commands.CreatedMethod;
import model.MethodModel;


public class MethodsController extends Controller implements Observer {
	private MethodModel model;
	private ViewMethods view;
	
	public MethodsController(MethodModel model, ViewMethods view) {
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
	
	@Override
	public void update(Observable o, Object arg) {
	}
	
//	public Object getVariable(String name) { 
//		return model.getVariable(name) == null? null: model.getVariable(name);
//	}

}