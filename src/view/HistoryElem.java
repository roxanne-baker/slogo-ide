package view;

public class HistoryElem extends ClickableTextBox {

	public HistoryElem(String text,View view) {
		super(text);
		setClickableAction(e-> onMouseClick());
		addObserver(view);
		setChanged();
		notifyObservers("NEWHISTORY");
	}

	public void onMouseClick() {
		setChanged();
		notifyObservers("CLICKED");
	}

}

