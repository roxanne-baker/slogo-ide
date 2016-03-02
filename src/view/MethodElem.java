package view;

public class MethodElem extends TextBox implements Clickable{

	public MethodElem(String text,View view) {
		super(text);
		setClickableAction(e-> onMouseClick());
		addObserver(view);
		setChanged();
		notifyObservers("NEWMETHOD");
	}

	public void onMouseClick() {
		setChanged();
		notifyObservers("CLICKED");
	}

}

