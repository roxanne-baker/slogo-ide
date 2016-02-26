package View;
import java.util.Observable;

import javafx.scene.Group;
import javafx.scene.layout.Pane;


public class ViewAgents extends View{
	Drawer drawer;
	Group viewGroup;
	public ViewAgents(String id) {
		super(id);
		viewGroup = new Group();
		drawer = new Drawer(viewGroup);
	}

	@Override
	public void update(Observable agent, Object obj) {
		System.out.println(obj);
		if(((Agent) agent).isVisible()){
			if (obj == "STAMP"){
				drawer.stampImage(((Agent) agent).getImageCopy(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition(), ((Agent) agent).getSize());
			}else if (obj == "MOVE"){
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
				if(!((Agent) agent).isPenUp()){
					drawer.drawLine(((Agent) agent).getOldXPosition(), ((Agent) agent).getOldYPosition(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
				}
			}else if (obj == "INITIAL"){
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			}else if (obj == "IMAGEVIEW"){
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());

			}
		}else{
			if(obj == "VISIBLE"){
				drawer.removeImage(((Agent) agent).getImageView());
			}
		}
		}

	@Override

	public Group getView() {

		return viewGroup;
	}

	
}
