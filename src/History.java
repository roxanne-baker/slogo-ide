
public class History extends ClickableText {

	public History(String text,View view) {
		super(text,"NEWHISTORY",view);
	}

	@Override
	public void onMouseClick() {
		System.out.println("will execute history");
	}

}
