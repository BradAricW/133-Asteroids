package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SpeedDownCommand extends Command {
	
	private GameWorld gw;
	
	public SpeedDownCommand(GameWorld gw) {
		super("Speed Down");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.speedDown();
	}

}
