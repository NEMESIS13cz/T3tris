package com.redstoner.nemes.t3tris.menu;

import org.lwjgl.opengl.GL11;

import com.redstoner.nemes.t3tris.Render;
import com.redstoner.nemes.t3tris.T3tris;
import com.redstoner.nemes.t3tris.gfx.FontMap;
import com.redstoner.nemes.t3tris.util.Controls;
import com.redstoner.nemes.t3tris.util.GameState;
import com.redstoner.nemes.t3tris.util.MouseHandler;

public class PlayButton extends Button {

	
	public PlayButton(float x, float y, float x2, float y2, IMenu m) {
		super(x, y, x2, y2, m);
	}

	public void render(int w, int h) {
		float x_ = this.x * w;
		float x2_ = x2 * w;
		float y_ = this.y * h;
		float y2_ = y2 * h;

		Render.disableTextures();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4f(0.8f, 0.8f, 0.8f, 0.4f);
		GL11.glVertex2f(x_, y2_);
		GL11.glVertex2f(x2_, y2_);
		GL11.glVertex2f(x2_, y_);
		GL11.glVertex2f(x_, y_);
		GL11.glEnd();
		Render.enableTextures();
		FontMap.drawString(0, 0, 10.0d, "ABCDEFGHIJKLM");
		FontMap.drawString(0, 100, 10.0d, "NOPQRSTUVWXYZ");
		FontMap.drawString(0, 200, 10.0d, "abcdefghijklm");
		FontMap.drawString(0, 300, 10.0d, "nopqrstuvwxyz");
		
		//FontManager.draw("courier", "Play", x_, y_);
	}
	
	public void update(int x, int y, int w, int h) {
		int x_ = (int) (this.x * w);
		int x2_ = (int) (x2 * w);
		int y_ = (int) (this.y * h);
		int y2_ = (int) (y2 * h);
		
		if (x > x_ && x < x2_ && y > y_ && y < y2_) {
			if (MouseHandler.released(Controls.getLeftMouseButton())) {
				T3tris.getInstance().setCurrentGameState(GameState.PLAYING);
			}
		}
	}
}
