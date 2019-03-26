package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TimeCommand extends Command {
	
	private GameWorld gw;
	
	public TimeCommand(GameWorld gw) {
		super("Time");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.time();
		System.out.println("Time.");
	}

}
