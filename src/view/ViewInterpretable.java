package view;

import model.Interpreter;

public abstract class ViewInterpretable extends View {
	private Interpreter myInterpreter;
	
	public ViewInterpretable(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		
	}
	
	public void setInterpreter(Interpreter ip){
		myInterpreter = ip;
	}
	
	public Interpreter getInterpreter(){
		return myInterpreter;
	}

	@Override
	public abstract int getX();

	@Override
	public abstract int getY();

}
