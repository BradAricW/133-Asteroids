package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class JumpCommand extends Command {
	
	private GameWorld gw;
	
	public JumpCommand(GameWorld gw) {
		super("Jump");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.jump();

	}

}
