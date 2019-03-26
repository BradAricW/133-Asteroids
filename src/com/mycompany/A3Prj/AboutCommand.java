package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	
	private GameWorld gw;
	
	public AboutCommand(GameWorld gw) {
		super("About");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.about();
	}

}

