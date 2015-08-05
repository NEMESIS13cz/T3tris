package com.redstoner.nemes.t3tris.menu;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.redstoner.nemes.t3tris.T3tris;
import com.redstoner.nemes.t3tris.gfx.FontManager;
import com.redstoner.nemes.t3tris.util.GameState;

public class ExitButton extends Button {

	
	public ExitButton(float x, float y, float x2, float y2, IMenu m) {
		super(x, y, x2, y2, m);
	}

	public void render(int w, int h) {
		float x_ = this.x * w;
		float x2_ = x2 * w;
		float y_ = this.y * h;
		float y2_ = y2 * h;
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4f(0.8f, 0.8f, 0.8f, 0.4f);
		GL11.glVertex2f(x_, y_);
		GL11.glVertex2f(x2_, y_);
		GL11.glVertex2f(x2_, y2_);
		GL11.glVertex2f(x_, y2_);
		GL11.glEnd();
		
		FontManager.draw("courier", "Quit", x_, y_);
	}
	
	boolean wasMouseButtonDown = false;
	
	public void update(int x, int y, int w, int h) {
		int x_ = (int) (this.x * w);
		int x2_ = (int) (x2 * w);
		int y_ = (int) (this.y * h);
		int y2_ = (int) (y2 * h);
		
		if (x > x_ && x < x2_ && y > y_ && y < y2_) {
			if (!Mouse.isButtonDown(0) && wasMouseButtonDown) {
				if (menu instanceof MainMenu) {
					T3tris.getInstance().setCurrentGameState(GameState.CLOSING);
				}else{
					T3tris.getInstance().currMenu = menu.getEscapeMenu();
				}
			}
		}
		wasMouseButtonDown = Mouse.isButtonDown(0);
	}
}
