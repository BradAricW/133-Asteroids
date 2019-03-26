package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {
	
	private GameWorld gw;
	
	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.setSound();
		System.out.println("Sound toggled.");
	}

}
