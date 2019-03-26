package com.mycompany.A3Prj;

//import statements
import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;

public class PointsView extends Container implements Observer {
	
	//create labels
	private Label score;
	private Label missiles;
	private Label time;
	private Label sound;
	private Label life;
	
	public PointsView() {
		
		//initialize labels
		score = new Label("Score: XXX ");
		missiles = new Label("Missiles: XXX ");
		time = new Label("Elapsed Time: XXX ");
		sound = new Label("Sound: XXX ");
		life = new Label("Lives: XXX");
		
		//stylize labels
		score.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		missiles.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		time.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		sound.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		life.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		Container gridContainer = new Container(new GridLayout(2,1));
		Container flowContainer = new Container(new FlowLayout(Component.CENTER));
		
		//add labels to container
		flowContainer.add(score);
		flowContainer.add(missiles);
		flowContainer.add(time);
		flowContainer.add(sound);
		flowContainer.add(life);
		gridContainer.add(flowContainer);
		
		this.add(gridContainer);
	}

	public void update (Observable o, Object arg) {

		// code here to update labels from data in the Observable (a GameWorldPROXY)
		GameWorld gw = (GameWorld)o;
		int points = ((GameWorld)o).getScore();
		score.setText("Score: " + points + "  ");
		
		int miss = ((GameWorld)o).getMissiles();
		missiles.setText("Missiles: " + miss + "  ");
		
		int clock = ((GameWorld)o).getTime();
		time.setText("Elapsed Time: " + clock + "  ");
		
		if (gw.getSound() == true) {
			sound.setText("Sound:  ON");
		}
		else {
			sound.setText("Sound:  OFF");
		}
		
		int lives = ((GameWorld)o).getLives();
		life.setText("Lives: " + lives + "  ");
		
	}
}

