package com.mycompany.A3Prj;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
//import com.codename1.ui.CheckBox;
//import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
	
	private GameWorld gw = new GameWorld(); // create “Observable”
	private MapView mv; // new in A2
	private PointsView pv; // new in A2
	boolean pause = false;
	private UITimer timer;
	
	
	private PlayPauseCommand myPlayPause = new PlayPauseCommand(gw, this);
	private AddAsteroidCommand myAddAsteroid = new AddAsteroidCommand(gw);
	private AddShipCommand myAddShip = new AddShipCommand(gw);
	private AddStationCommand myAddStation = new AddStationCommand(gw);
	private FireMissileCommand myFire = new FireMissileCommand(gw);
	private JumpCommand myJump = new JumpCommand(gw);
	private QuitCommand myQuit = new QuitCommand(gw);
	private RefuelCommand myrefuel = new RefuelCommand(gw);
	private SpeedDownCommand mySpeedDown = new SpeedDownCommand(gw);
	private SpeedUpCommand mySpeedUp = new SpeedUpCommand(gw);
	private TurnLeftCommand myLeft = new TurnLeftCommand(gw);
	private TurnRightCommand myRight = new TurnRightCommand(gw);
	private MapCommand myMap = new MapCommand(gw);
	private NewCommand myNew = new NewCommand(gw, this);
	private SaveCommand mySave = new SaveCommand(gw);
	private UndoCommand myUndo = new UndoCommand(gw);
	private SoundCommand mySound = new SoundCommand(gw);
	private AboutCommand myAbout = new AboutCommand(gw);
	private LoadCommand myLoad = new LoadCommand(gw);
	
	//create buttons
	CreateButton playPause = new CreateButton("Play >/Pause ||");
	CreateButton addAsteroid = new CreateButton("Add Asteroid");
	CreateButton addStation = new CreateButton("Add Station");
	CreateButton addShip = new CreateButton("Add Ship");
	CreateButton speedUp = new CreateButton("Speed Up");
	CreateButton slowDown = new CreateButton("Slow Down");
	CreateButton left = new CreateButton("Left");
	CreateButton right = new CreateButton("Right");
	CreateButton fire = new CreateButton("Fire");
	CreateButton jump = new CreateButton("Jump");
	CreateButton refuel = new CreateButton("Refuel Missiles");
	CreateButton quit = new CreateButton("Quit");
	
	
	public Game() {
		
		gw.init(this); //initialize all variables and create collections
		mv = new MapView(); // create an “Observer” for the map
		pv = new PointsView(); // create an “Observer” for the points
		gw.addObserver(mv); // register the map Observer
		gw.addObserver(pv); // register the points observer
		
		addAsteroid.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));
		addStation.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));
		addShip.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));
		speedUp.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));
		slowDown.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));
		left.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));
		right.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));
		fire.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));
		jump.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));
		refuel.getDisabledStyle().setFgColor(ColorUtil.rgb(150, 150, 150));

		// code here to create menus, create Command objects for each command,
		// add commands to Command menu, create a control panel for the buttons,
		// add buttons to the control panel, add commands to the buttons, and
		// add control panel, MapView panel, and PointsView panel to the form

		setLayout(new BorderLayout());
		Container flowContainer = new Container(new FlowLayout(Component.CENTER));
		flowContainer.add(pv);
		flowContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.CYAN));
		add(BorderLayout.NORTH, flowContainer);
		add(BorderLayout.CENTER, mv);
		timer = new UITimer(this);
		timer.schedule(20, true, this);
		
		Container leftContainer = new Container(new GridLayout(16,1));
		
		//create key listeners
		addKeyListener('t', myPlayPause);
		addKeyListener('z', myAddAsteroid);
		addKeyListener('x', myAddStation);
		addKeyListener('c', myAddShip);
		addKeyListener('w', mySpeedUp);
		addKeyListener('s', mySpeedDown);
		addKeyListener('a', myLeft);
		addKeyListener('d', myRight);
		addKeyListener('f', myFire);
		addKeyListener('j', myJump);
		addKeyListener('e', myrefuel);
		removeKeyListener('e', myrefuel);
		addKeyListener('g', myQuit);
		addKeyListener('m', myMap);
		
		
		//link buttons to commands
		playPause.setCommand(myPlayPause);
		addAsteroid.setCommand(myAddAsteroid);
		addStation.setCommand(myAddStation);
		addShip.setCommand(myAddShip);
		speedUp.setCommand(mySpeedUp);
		slowDown.setCommand(mySpeedDown);
		left.setCommand(myLeft);
		right.setCommand(myRight);
		fire.setCommand(myFire);
		jump.setCommand(myJump);
		refuel.setCommand(myrefuel);
		quit.setCommand(myQuit);
		
		//add buttons to container
		leftContainer.add(new Label("Commands"));
		leftContainer.add(playPause);
		leftContainer.add(addAsteroid);
		leftContainer.add(addStation);
		leftContainer.add(addShip);
		leftContainer.add(speedUp);
		leftContainer.add(slowDown);
		leftContainer.add(left);
		leftContainer.add(right);
		leftContainer.add(fire);
		leftContainer.add(jump);
		leftContainer.add(refuel);
		leftContainer.add(quit);
		
		add(BorderLayout.WEST, leftContainer);
		
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		TextField myTF = new TextField("Asteroids");
		myToolbar.setTitleComponent(myTF);
		
		myToolbar.addCommandToSideMenu(myPlayPause);
		myToolbar.addCommandToSideMenu(myAddAsteroid);
		myToolbar.addCommandToSideMenu(myAddStation);
		myToolbar.addCommandToSideMenu(myAddShip);
		myToolbar.addCommandToSideMenu(mySpeedUp);
		myToolbar.addCommandToSideMenu(mySpeedDown);
		myToolbar.addCommandToSideMenu(myLeft);
		myToolbar.addCommandToSideMenu(myRight);
		myToolbar.addCommandToSideMenu(myJump);
		myToolbar.addCommandToSideMenu(myrefuel);
		myToolbar.addCommandToSideMenu(myMap);
		myToolbar.addCommandToSideMenu(myQuit);
		
		//CheckBox myCheck = new CheckBox("Sound ");
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		/* Code for a form which has a CheckBox as a side menu item*/
		//add a check box to side menu (which does not perform any operation yet..)
		/*Command sideMenuItemCheck = mySound;
		CheckBox checkSideMenuComp = new CheckBox("Sound ");
		//set the style of the check box
		checkSideMenuComp.getAllStyles().setBgTransparency(255);
		checkSideMenuComp.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		//set "SideComponent" property of the command object to the check box
		sideMenuItemCheck.putClientProperty("SideComponent", checkSideMenuComp);
		//add the command to the side menu, this places its side component (check box) in the side menu
		myToolbar.addCommandToSideMenu(sideMenuItemCheck);
		
		NOT WORKING :(
		*/
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		myToolbar.addCommandToOverflowMenu(myNew);
		myToolbar.addCommandToOverflowMenu(mySave);
		myToolbar.addCommandToOverflowMenu(myLoad);
		myToolbar.addCommandToOverflowMenu(myUndo);

		myToolbar.addCommandToOverflowMenu(mySound);
		myToolbar.addCommandToOverflowMenu(myAbout);
		myToolbar.addCommandToOverflowMenu(myQuit);
		
		refuel.setEnabled(false);
		
		this.show();
	}
	public void run() {
		gw.time();
	}
	
	public void playPause() {
		pause = !pause;
		if(pause) {
			timer.cancel();
			if(gw.sound) {
				gw.bgSound.pause();
			}
			addAsteroid.setEnabled(false);
			addShip.setEnabled(false);
			addStation.setEnabled(false);
			fire.setEnabled(false);
			jump.setEnabled(false);
			refuel.setEnabled(true);
			slowDown.setEnabled(false);
			speedUp.setEnabled(false);
			left.setEnabled(false);
			right.setEnabled(false);
			
			removeKeyListener('p', myPlayPause);
			removeKeyListener('z', myAddAsteroid);
			removeKeyListener('x', myAddStation);
			removeKeyListener('c', myAddShip);
			removeKeyListener('w', mySpeedUp);
			removeKeyListener('s', mySpeedDown);
			removeKeyListener('a', myLeft);
			removeKeyListener('d', myRight);
			removeKeyListener('f', myFire);
			removeKeyListener('j', myJump);
			addKeyListener('e', myrefuel);


		}
		else {
			timer.schedule(20, true, this);
			if(gw.sound) {
				gw.bgSound.play();
			}
			addAsteroid.setEnabled(true);
			addShip.setEnabled(true);
			addStation.setEnabled(true);
			fire.setEnabled(true);
			jump.setEnabled(true);
			refuel.setEnabled(false);
			slowDown.setEnabled(true);
			speedUp.setEnabled(true);
			left.setEnabled(true);
			right.setEnabled(true);
			
			addKeyListener('p', myPlayPause);
			addKeyListener('z', myAddAsteroid);
			addKeyListener('x', myAddStation);
			addKeyListener('c', myAddShip);
			addKeyListener('w', mySpeedUp);
			addKeyListener('s', mySpeedDown);
			addKeyListener('a', myLeft);
			addKeyListener('d', myRight);
			addKeyListener('f', myFire);
			addKeyListener('j', myJump);
			removeKeyListener('e', myrefuel);
		}
	}
	
	public void gameOver(GameWorld gw) {
		playPause();
		if(gw.sound) {
			gw.lose.play();
		}
		Label score = new Label("Score: " + gw.score + "\n   Time: " + gw.clock);
		Dialog.show("Game Over", score, myNew);
	}
}

