package factory;

import model.MethodModel;
import model.Model;
import model.VariableModel;
import view.ViewType;

public class ModelFactory {
	
	public ModelFactory(){
	}
	
	public Model createModel(ViewType ID){
		switch(ID){
		case VARIABLES:
			return new VariableModel();
		case METHODS:
			return new MethodModel();
		}
		return null;
	}

}