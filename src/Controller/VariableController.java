<<<<<<< HEAD
package Controller;

public class VariableController {

}
=======
package Controller;

import java.util.Observable;
import java.util.Observer;
import View.VariableModel;
import View.VariableView;
import View.VariableElem;

public class VariableController implements Observer {
	private VariableModel model;
	private VariableView view;
	
	public VariableController(VariableModel model, VariableView view) {
		this.model = model;
		this.view = view;
	}
	
	public void addVariable(String name, String value){
		model.addVariable(name,value);
		view.addVariableView(name, value, new VariableElem(name,value,this));
	}
	
	@Override
	public void update(Observable savedObj, Object arg) {
		if(arg=="FIELDCHANGED"){
			addVariable(((VariableElem)savedObj).getName(),((VariableElem)savedObj).getValue());
		}
	}

}
>>>>>>> a4efb0f6d78711515c4792da5b08d02b6b9d0349
