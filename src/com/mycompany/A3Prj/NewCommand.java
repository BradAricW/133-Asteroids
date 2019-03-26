package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NewCommand extends Command {
	
	private GameWorld gw;
	private Game g;
	
	public NewCommand(GameWorld gw, Game g) {
		super("New");
		this.g = g;
		this.gw = gw;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.newGame();
		g.playPause();
	}

}
