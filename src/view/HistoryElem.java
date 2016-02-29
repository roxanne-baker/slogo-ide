package view;

public class HistoryElem extends ClickableText {

	public HistoryElem(String text,View view) {
		super(text);
		addObserver(view);
		setChanged();
		notifyObservers("NEWHISTORY");
	}

	@Override
	public void onMouseClick() {
		System.out.println("will execute history");
	}

}
