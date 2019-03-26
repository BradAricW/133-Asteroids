package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayPauseCommand extends Command {
	
	private GameWorld gw;
	private Game g;
	
	public PlayPauseCommand(GameWorld gw, Game g) {
		super("Play >/Pause ||");
		this.g = g;
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		g.playPause();
	}

}
