package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ConfirmCommand extends Command {
	
	public ConfirmCommand() {
		super("Yes");
	}
	
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}

