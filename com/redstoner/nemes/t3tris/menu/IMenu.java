package com.redstoner.nemes.t3tris.menu;

import com.redstoner.nemes.t3tris.util.IDrawable;
import com.redstoner.nemes.t3tris.util.ITickable;

public interface IMenu extends IDrawable, ITickable	 {

	public default IMenu getEscapeMenu() {
		return new MainMenu();
	}
	
}
