package View;
import java.util.Observable;

import javafx.scene.Group;


public class ViewAgents extends View{
	Drawer drawer;
	Group viewGroup;
	public ViewAgents(String id, int height, int width) {
		super(id, height, width);
		viewGroup = new Group();
		drawer = new Drawer(viewGroup);
	}

	@Override
	public void update(Observable agent, Object obj) {
		System.out.println(obj);
		if (obj == "STAMP"){
			drawer.stampImage(((Agent) agent).getImageCopy(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition(), ((Agent) agent).getSize());
		}else if (obj == "MOVE"){
			drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			if(!((Agent) agent).isPenUp()){
				drawer.drawLine(((Agent) agent).getOldXPosition(), ((Agent) agent).getOldYPosition(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			}
		}else if (obj == "INITIAL"){
			drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
		}
	}

	@Override
	public Group getView() {
		// TODO Auto-generated method stub
		return viewGroup;
	}

	
}
