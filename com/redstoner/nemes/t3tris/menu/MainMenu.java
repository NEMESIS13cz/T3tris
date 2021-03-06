package com.redstoner.nemes.t3tris.menu;

import java.util.ArrayList;

public class MainMenu implements IMenu {

	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	public MainMenu() {
		buttons.add(new PlayButton(0.25f, 0.45f, 0.75f, 0.6f, this));
		buttons.add(new OptionsButton(0.25f, 0.625f, 0.75f, 0.775f, this));
		buttons.add(new ExitButton(0.25f, 0.8f, 0.75f, 0.95f, this));
		
	}
	
	public void render(int w, int h) {
		for (Button b : buttons) {
			b.render(w, h);
		}
	}
	
	public void update(int x, int y, int w, int h) {
		for (Button b : buttons) {
			b.update(x, y, w, h);
		}
	}
}
