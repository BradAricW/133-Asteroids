package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LoadCommand extends Command {
	
	private GameWorld gw;
	
	public LoadCommand(GameWorld gw) {
		super("Load");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.load();
		System.out.println("Game Loaded (But not really).");
	}

}