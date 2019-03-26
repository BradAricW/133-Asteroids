package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class QuitCommand extends Command {
	
	private GameWorld gw;
	
	public QuitCommand(GameWorld gw) {
		super("Quit");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.quit();
	}

}
