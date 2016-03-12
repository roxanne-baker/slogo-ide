package view;

import model.Interpreter;

public class ViewInterpretable extends View {
	private Interpreter myInterpreter;
	
	public ViewInterpretable(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		
	}
	
	public void setInterpreter(Interpreter ip){
		myInterpreter = ip;
		addObserver(this);
	}
	
	public Interpreter getInterpreter(){
		return myInterpreter;
	}

}
