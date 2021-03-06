package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnRightCommand extends Command {
	
	private GameWorld gw;
	
	public TurnRightCommand(GameWorld gw) {
		super("Turn Right");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.turnRight();
	}

}
