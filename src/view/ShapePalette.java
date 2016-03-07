package view;



public class ShapePalette extends Palette{
	private static final String[] shapeList = {"SQUARE","TRIANGLE","HEXAGON"};
	private ShapeFactory shapeFactory;
	private int paletteObjectSize;
	public ShapePalette(String id,int size) {
		super(id);
		shapeFactory = new ShapeFactory();
		paletteObjectSize = size;
		for (int index = 0; index < shapeList.length; index++){
			addToPalette(shapeFactory.createNewShape(shapeList[index], paletteObjectSize),index);
		}
	}








}
