package com.redstoner.nemes.t3tris.menu;

import java.util.ArrayList;

import com.redstoner.nemes.t3tris.gfx.FontMap;
import com.redstoner.nemes.t3tris.util.Options;

public class OptionsMenu implements IMenu {

	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	public OptionsMenu() {
		buttons.add(new BackButton(0.25f, 0.8f, 0.75f, 0.95f, this));
		buttons.add(new FramerateSlider(0.2f, 0.2f, 0.65f, 0.35f, this));
		buttons.add(new ResetFramerateButton(0.2f, 0.36f, 0.65f, 0.40f, this));
	}
	
	public void render(int w, int h) {
		for (Button b : buttons) {
			b.render(w, h);
		}
		int fps = Options.framerateLimit;
		FontMap.drawString(0.70 * w, 0.25 * h, 0.1, fps == Integer.MAX_VALUE ? "Unlimited" : String.valueOf(fps), false);
	}
	
	public void update(int x, int y, int w, int h) {
		for (Button b : buttons) {
			b.update(x, y, w, h);
		}
	}

}
