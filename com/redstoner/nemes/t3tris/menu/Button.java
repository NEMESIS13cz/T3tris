package com.redstoner.nemes.t3tris.menu;

import com.redstoner.nemes.t3tris.util.IDrawable;
import com.redstoner.nemes.t3tris.util.ITickable;

public class Button implements IDrawable, ITickable {

	protected float x, y, x2, y2;
	protected IMenu menu;
	
	public Button(float x, float y, float x2, float y2, IMenu m) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		menu = m;
	}

	@Override
	public void update(int x, int y, int w, int h) {
		
	}

	@Override
	public void render(int width, int height) {
		
	}
}
